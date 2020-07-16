package com.ocean.mvc.service;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Created by zhuwenjun on 2018/3/8.
 */

@Component
public class HttpConnectionService {

    private static HttpConnectionService httpConnectionService;

    PoolingHttpClientConnectionManager cm = null;
    RequestConfig requestConfig = null;

    @PostConstruct
    private void init() {
        System.out.println("HttpConnectionService init");
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", sslsf)
            .register("http", new PlainConnectionSocketFactory())
            .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        requestConfig = RequestConfig.custom()
            // 请求超时时间
            .setConnectTimeout(3 * 1000)
            // 等待数据超时时间
            .setSocketTimeout(60 * 1000)
            // 连接超时时间
            .setConnectionRequestTimeout(2000)
            .build();

        httpConnectionService = this;
    }

    public static HttpConnectionService getInstance() {
        System.out.println("getInstance");
        if (httpConnectionService == null) {
            httpConnectionService = new HttpConnectionService();
            httpConnectionService.init();
        }
        return httpConnectionService;
    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(cm)
            .setDefaultRequestConfig(requestConfig)
            .build();
        /*CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }

    public String get(String path) {
        return get(path, null);
    }

    public String get(String path, Map<String, String> headers) {

        CloseableHttpClient httpClient = getHttpClient();
        HttpGet httpget = new HttpGet(path);
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
        httpget.setHeader("User-Agent", userAgent);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.entrySet().forEach(h -> {
                httpget.addHeader(h.getKey(), h.getValue());
            });
        }
        CloseableHttpResponse response = null;
        try {

            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String postForm(String path) {
        return postForm(path, null, null);
    }

    public String postForm(String path, Map<String, String> params) {
        return postForm(path, params, null);
    }

    public String postForm(String path, Map<String, String> params, Map<String, String> headers) {

        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(path);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        if (headers != null) {
            headers.entrySet().forEach(k -> httpPost.addHeader(k.getKey(), k.getValue()));
        }
        CloseableHttpResponse response = null;

        if (params != null) {
            List<NameValuePair> list = new LinkedList<>();
            for (Entry<String, String> stringStringEntry : params.entrySet()) {
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue());
                list.add(basicNameValuePair);
            }
            UrlEncodedFormEntity entityParam = null;
            try {
                entityParam = new UrlEncodedFormEntity(list, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entityParam);
        }

        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String postBody(String path, Object object) {
        return postBody(path, object, null);
    }

    public String postBody(String path, Object object, Map<String, String> headers) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(path);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        httpPost.addHeader("Content-Type", " application/json");
        if (headers != null) {
            headers.entrySet().forEach(k -> httpPost.addHeader(k.getKey(), k.getValue()));
        }
        CloseableHttpResponse response = null;
        try {

            //在body中以json格式上传
            String jsonstr = JSONObject.toJSONString(object);
            //System.out.println(jsonstr);
            StringEntity stringEntity = new StringEntity(jsonstr);
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(stringEntity);

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;


    }

}
