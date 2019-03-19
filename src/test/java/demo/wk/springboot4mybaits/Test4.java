package demo.wk.springboot4mybaits;


import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 模拟登陆知乎
 */
public class Test4 {

    public static void main(String[] args) {
        String  cookie= "";

        String expected = "{\"status\":200,\"message\":\"ok\"}";
        String sso = "http://test.ssa.jd.com/sso/login";
        String testurl = "http://cjg-api.jd.com/api/component/config/dic/add";
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {

            try {
                List<String> cookieStr = new ArrayList();

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
                CloseableHttpResponse res1 = null;


                // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair("username", "wangqian65"));
                valuePairs.add(new BasicNameValuePair("password", "xinxibu456"));

                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
                entity.setContentType("application/x-www-form-urlencoded");

                // 创建一个post请求

                HttpPost post = new HttpPost(sso);
                HttpPost psst1 = new HttpPost(testurl);
                // 注入post数据
                post.setEntity(entity);
                psst1.setEntity(entity);

                res = httpClient.execute(post, context);
                res1 = httpClient.execute(psst1,context);
                // 打印响应信息，查看是否登陆是否成功
                String content = EntityUtils.toString(res.getEntity());
                System.out.println(content);
                res.close();
                res1.close();

                //放在post请求之后,获取的是响应头的Set-Cookie
                for (Cookie c : context.getCookieStore().getCookies()) {
                    cookieStr.add(c.getName() + "=" + c.getValue());
                }
//        String str1=org.apache.commons.lang3.StringUtils.join(cookieStr, ",");
                String str1 = "";
                for(String a : cookieStr){
                    str1 = str1 + ";"+a;
                }

                System.out.println("str1 is "+ str1);
                cookie = new String(str1);

                List<Integer> componentIds;


                // 构造被测接口的post数据
                List<NameValuePair> testvaluePairs = new LinkedList<NameValuePair>();
                testvaluePairs.add(new BasicNameValuePair("cfgCode", "test1"));
                testvaluePairs.add(new BasicNameValuePair("cfgName", "test字典名称"));
                testvaluePairs.add(new BasicNameValuePair("code", "a1"));
                testvaluePairs.add(new BasicNameValuePair("name", "a1"));
                testvaluePairs.add(new BasicNameValuePair("site", "China"));
                testvaluePairs.add(new BasicNameValuePair("value", "1"));
                testvaluePairs.add(new BasicNameValuePair("componentIds", "10883"));


                UrlEncodedFormEntity testentity = new UrlEncodedFormEntity(testvaluePairs, Consts.UTF_8);
                testentity.setContentType("application/json; charset=utf-8");

                HttpPost testhttpPost = new HttpPost(testurl);
                testhttpPost.setEntity(testentity);


                testhttpPost.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                testhttpPost.addHeader("Accept-Encoding","gzip, deflate");
                testhttpPost.addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");
                testhttpPost.addHeader("Cache-Control","max-age=0");
                testhttpPost.addHeader("Connection","keep-alive");
                testhttpPost.addHeader("Upgrade-Insecure-Requests","1");
                testhttpPost.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
                testhttpPost.addHeader("Cookie",str1);




                res = httpClient.execute(testhttpPost, context);
                String str2 = str1;
                testhttpPost.addHeader("Cookie",str2);
                res = httpClient.execute(testhttpPost, context);
                content = EntityUtils.toString(res.getEntity());
                System.out.println("++++++++++++++++++++"+content);



            } finally {

            }
        }catch (Exception e) {
            e.printStackTrace();
        }





    }
}
