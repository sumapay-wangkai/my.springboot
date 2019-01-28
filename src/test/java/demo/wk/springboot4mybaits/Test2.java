package demo.wk.springboot4mybaits;

import demo.wk.springboot4mybaits.util.HeaderUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Test1
 * @Description 获取测试页面
 * @Author wangkai60
 * @Date 2019/1/16 17:27
 * @Version 1.0
 **/
public class Test2 {

    Map<String, String> headers = new HashMap<String, String>();

    String ssoUrl = "http://test.ssa.jd.com/sso/login";
    String dataUrl = "http://cjg-api.jd.com/api/component/searchCurrentComponent?start=0&pageSize=10&areaId=&name=";
    List<NameValuePair> params = new ArrayList<>();

    @Before
    public void init() {
        headers = HeaderUtil.getHeaderFromTxt("E:\\Tmp\\header.txt");

        params.add(new BasicNameValuePair("username", "bjwanglijun"));
        params.add(new BasicNameValuePair("password", "xinxibu456"));

    }

    @Test
    public void test() {

        doLogin(ssoUrl, params);
    }

    public String doLogin(String url, List<NameValuePair> params) {
        // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore).build();

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse closeableHttpResponse = null;

        BufferedInputStream bis = null;
        InputStream inputStream = null;
        ByteArrayOutputStream buf = null;
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            closeableHttpResponse = httpClient.execute(httpPost, context);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == 302) {
                System.out.println(">>>>>>cookies:");
                context.getCookieStore().getCookies().forEach(System.out::println);
                doPost(httpClient, context, dataUrl, headers);
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
            System.out.println("--------------------" + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String doPost(CloseableHttpClient httpClient, HttpClientContext context, String url, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse closeableHttpResponse = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        ByteArrayOutputStream buf = null;
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpPost.addHeader(key, headers.get(key));
            }
        }
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            closeableHttpResponse = httpClient.execute(httpPost, context);

            if (closeableHttpResponse.getStatusLine().getStatusCode() != 200) {
                System.out.println("status=============" + closeableHttpResponse.getStatusLine().getStatusCode());
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
            System.out.println("--------------------" + str);
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


}
