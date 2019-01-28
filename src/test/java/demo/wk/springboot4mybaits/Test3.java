package demo.wk.springboot4mybaits;


import demo.wk.springboot4mybaits.util.HeaderUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 模拟登陆知乎
 */
public class Test3 {

    public static void main(String[] args) throws java.text.ParseException {
        Map<String, String> headers = new HashMap<String, String>();
        headers = HeaderUtil.getHeaderFromTxt("E:\\Tmp\\header.txt");

        String name = "bjwanglijun";
        String password = "xinxibu456";

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

        CloseableHttpResponse res = null;

        // 创建本地的HTTP内容
        try {
            try {
                // 创建一个get请求用来获取必要的Cookie，如_xsrf信息
                HttpGet get = new HttpGet("http://cjg.jd.com/admin/#!/myapp");

                res = httpClient.execute(get);
                // 获取常用Cookie,包括_xsrf信息,放在发送请求之后
                System.out.println("11111111111111111111111111111111");
                for (Cookie c : cookieStore.getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                res.close();

                // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair("username", "bjwanglijun"));
                valuePairs.add(new BasicNameValuePair("password", "xinxibu456"));

                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
                entity.setContentType("application/x-www-form-urlencoded");

                // 创建一个post请求
                HttpPost post = new HttpPost("http://test.ssa.jd.com/sso/login");
                // 注入post数据
                post.setEntity(entity);
                res = httpClient.execute(post);

                // 打印响应信息，查看是否登陆是否成功
                System.out.println("22222222222222222222222222");
                String content = EntityUtils.toString(res.getEntity());
                System.out.println(content);
                res.close();

                //放在post请求之后,获取的是响应头的Set-Cookie
                System.out.println("2222222222222222222222222222");
                for (Cookie c : context.getCookieStore().getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }

                // 构造一个新的get请求，用来测试登录是否成功
                HttpGet newGet = new HttpGet("http://cjg-api.jd.com/api/component/searchCurrentComponent?start=0&pageSize=10&areaId=&name=");
                if (headers != null) {
                    for (String key : headers.keySet()) {
                        newGet.addHeader(key, headers.get(key));
                    }
                }
                res = httpClient.execute(newGet, context);
                content = EntityUtils.toString(res.getEntity());
                System.out.println("3333333333333333333");
                System.out.println(content);
                res.close();

            } finally {
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
