package dahai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.security.jca.JCAUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述
 *
 * @author chy
 * @date 2019/8/22 0022
 */
public class TestHttpRequest {

    @Test
    public void sendRequestByHttpclientGet() throws RestClientException, URISyntaxException, InterruptedException, IOException {
        String url = "http://api.sms.bambika.co.ke:8555/?target=BANGCASINO&msisdn=254713580938&text=This+is+a+test+message&login=bangbet&pass=ea62ee1413b5ae1e79f9a844e711bba9";
        //创建CloseableHttpClient
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();
        //执行
        HttpUriRequest httpGet = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String entityStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(entityStr);
        }
        System.out.println(response.toString());
    }


    @Test
    public void sendRequestByHttpclientPost() throws RestClientException, URISyntaxException, InterruptedException, IOException {
        String url = "http://www.xinghengedu.com/autotele/simpleLogin.htm";
        //创建CloseableHttpClient
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();
        //执行
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("username", "13121939122");
        BasicNameValuePair param2 = new BasicNameValuePair("password", "123456");
        list.add(param1);
        list.add(param2);

        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");

        httpPost.setEntity(entityParam);
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String entityStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(entityStr);
        }
        System.out.println(response.toString());
    }


    @Test
    public void sendRequestByRestTemplateGet() throws RestClientException, URISyntaxException {
        String url = "http://www.xinghengedu.com/autotele/simpleLogin.htm?username=13121939122&password=123456";
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        // get请求
        String resString = rest.getForObject(new URI(url), String.class);
        System.out.println(resString);
    }

    @Test
    public void sendRequestByRestTemplatePost() throws RestClientException, URISyntaxException {
        String url = "http://www.xinghengedu.com/autotele/simpleLogin.htm";
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        // post请求
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        org.springframework.http.HttpEntity<MultiValueMap> requestEntity = new org.springframework.http.HttpEntity<MultiValueMap>(body, headers);

        body.add("username", "13121939122");
        body.add("password", "123456");

        String postForObject = rest.postForObject(new URI(url), requestEntity, String.class);
        System.out.println(postForObject);
    }


    @Test
    public void sendRequestByOkHttpGet() throws RestClientException, URISyntaxException, InterruptedException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://www.xinghengedu.com/autotele/simpleLogin.htm?username=13121939122&password=123456";
        final Request request = new Request.Builder()
            .url(url)
            .get()//默认就是GET请求，可以不写
            .build();
        Call call = okHttpClient.newCall(request);
        System.out.println(call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void sendRequestByOkHttpPost() throws RestClientException, URISyntaxException, InterruptedException, IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://www.xinghengedu.com/autotele/simpleLogin.htm";

        RequestBody body = new FormBody.Builder()
            .add("username", "13121939122")
            .add("password", "123456").build();

        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
//        System.out.println(call.execute().cacheResponse().body());
        TimeUnit.SECONDS.sleep(1);
    }


}
