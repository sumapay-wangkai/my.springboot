package demo.wk.springboot4mybaits.util;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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

        if (headers != null) {
            for (String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }
        }
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            InputStream inputStream = closeableHttpResponse.getEntity().getContent();

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String str = buf.toString();
            return str;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }





    public static void main(String[] args) {
        String url = "http://www.baidu.com";
        String result = doGet(url, new HashMap<>());
        System.out.println("result" + result);
    }


}
