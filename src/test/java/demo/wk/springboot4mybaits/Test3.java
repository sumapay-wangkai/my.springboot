package demo.wk.springboot4mybaits;


import com.alibaba.fastjson.JSON;
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

import java.util.*;

/**
 * 模拟登陆知乎
 */
public class Test3 {

    public static void main(String[] args) {
        List<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(2);
        aa.add(1);
        for(Integer i : aa){
            if(i ==1){
                aa.remove(i);
            }
        }
        System.out.println(JSON.toJSONString(aa));

    }
}
