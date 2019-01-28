package demo.wk.springboot4mybaits.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MyHttpClient
 * @Description httpclient工具类
 * @Author wangkai60
 * @Date 2019/1/16 9:16
 * @Version 1.0
 **/
public class MyHttpClient {
    public static String doGet(String url, Map<String, String> headers) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        ByteArrayOutputStream buf = null;
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }
        }
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpGet);

            if (closeableHttpResponse.getStatusLine().getStatusCode() != 200) {
                System.out.println("status:" + closeableHttpResponse.getStatusLine());

            }
            inputStream = closeableHttpResponse.getEntity().getContent();

            bis = new BufferedInputStream(inputStream);
            buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String str = buf.toString();
            return str;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                buf.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    public static void main(String[] args) {

    }



}
