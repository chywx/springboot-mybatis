package com.ocean.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/1/4 0004
 */
public class KeyPairGenUtil {
    //指定加密算法为RSA
    private static final String ALGORITHM = "RSA";
    //密钥长度，用来初始化
    private static final int KEYSIZE = 1024;
    //指定公钥存放文件
    private static String PUBLIC_KEY_FILE = "c://test/pub.txt";
    //指定私钥存放文件
    private static String PRIVATE_KEY_FILE = "c://test/pri.txt";


    public static void main(String[] args) throws Exception {
        generateKeyPair();
        String encrypt = encrypt("root");
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);
    }

    /**
     * 生成密钥对,druid中的ConfigTools类中也有生成方法可以直接调用
     * @throws Exception
     */
    private static void generateKeyPair() throws Exception {

        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        keyPairGenerator.initialize(KEYSIZE);

        //生成密匙对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 得到公钥
        Key publicKey = keyPair.getPublic();

        // 得到私钥
        Key privateKey = keyPair.getPrivate();

        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;
        try {
            //用文件流将生成的密钥写入文件
            fos1 = new FileOutputStream(PUBLIC_KEY_FILE);
            fos2 = new FileOutputStream(PRIVATE_KEY_FILE);
            fos1.write(Base64.byteArrayToBase64(publicKey.getEncoded()).getBytes());
            fos2.write(Base64.byteArrayToBase64(privateKey.getEncoded()).getBytes());
        } catch (Exception e) {
            throw e;
        } finally {
            //清空缓存，关闭文件输出流
            fos1.close();
            fos2.close();
        }
    }

    private static String encrypt(String text) throws Exception {
        //读取私钥
        FileInputStream in2 = new FileInputStream(new File(PRIVATE_KEY_FILE));
        //如果改变key的长度这里需要适当调整
        byte[] buffer = new byte[2048];

        int count = in2.read(buffer);
        String privateKey = new String(buffer,0,count);

        //加密
        String code = ConfigTools.encrypt(privateKey,text);
        return code;
    }
    private static String decrypt(String text) throws Exception {
        //读取私钥
        FileInputStream in2 = new FileInputStream(new File(PUBLIC_KEY_FILE));
        //如果改变key的长度这里需要适当调整
        byte[] buffer = new byte[2048];

        int count = in2.read(buffer);
        String publicKey = new String(buffer,0,count);

        //加密
        String password = ConfigTools.decrypt(publicKey,text);
        return password;
    }
}
