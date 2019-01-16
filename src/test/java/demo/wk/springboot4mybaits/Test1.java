package demo.wk.springboot4mybaits;

import demo.wk.springboot4mybaits.util.MyHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Test1
 * @Description 测试页面响应时间
 * @Author wangkai60
 * @Date 2019/1/16 17:27
 * @Version 1.0
 **/
public class Test1 {

    Map<String, String> headers = new HashMap<String, String>();
    List<String> urls = new ArrayList<>();
    int count = 200;

    @Before
    public void init() {
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "__jdv=67462917|direct|-|none|-|1547082068071; shshshfpb=27021c7e0c05948c2811232a1bc1537f15bc53875b8570931f886b1a52; shshshfpa=2c133db0-fa50-d135-898f-cf247432c58d-1547114315; componentsid=4fd440fa-205d-4318-9044-fe1fb7e1f292; componentsid_pre=446c71b6-a44b-49a4-a989-5212e246e785; PCSYCityID=1; TrackID=1SWCcoakK3QPvpSKdeLPCmJlWrnIcDzkeXDf_vjcI7qMv1WhXZAh4-g7q35v6zbfyWFrgPrZJLMr0QwE8wIzrfGX_dbDeprzutUBl3v6GUR4; pinId=X4hj0ln7LRd3WdRUjvDbZg; pin=%E9%B9%B0%E6%89%AC%E5%BD%B1%E9%80%9D; unick=%E9%B9%B0%E6%89%AC%E5%BD%B1%E9%80%9D; ceshi3.com=000; _tp=bSD8HBlsqVPqfGfPjc%2FHUNwBUhM1bm2lLFugE7hxDZzUjVz6F%2Bp4C6Ce8a7xxSQw; _pst=%E9%B9%B0%E6%89%AC%E5%BD%B1%E9%80%9D; user-key=7579ab99-7b9e-4193-a998-2fdc86d18163; shshshfp=6ad2130e1e0494d286f149e1d014b4c9; ipLocation=%u5317%u4eac; ipLoc-djd=1-2800-2851-0.740058167; cn=0; __jdu=15470820680701341087814; thor=5116EBFD623D9F28E6831D0404C4283236C0117405806E5C9CE6F7F85189192FA87894B8F9D7F7E2CFBF63D2917D982FE4BC7A24166A5D110606579ADBDE84C1D241B1DA7329D1C76CC8D24FFB2330A80A7A2FC945A16062CD6E2E44F4B7D96FECFBE10EE727818F0AAF44D5A5DBBDB9DBF6B19769AF0BD2888831695BF3E8BC; 3AB9D23F7A4B3C9B=ONCBSDSZPL33HXQ2U76LMAU3PI7LNOJ2UZ54EAIHLC5P2PVGQ3VWOG3NPKYECVE4MUO2YE4VJ64RQVYCBXTM22Q4A4; __jdc=62267774; jd.erp.lang=zh_CN; erp1.jd.com=C1BD219BE621097E2705DFDED61637D3EE822FC32A6C70E9543B626B80FEDE592A6469E86E29B6BBDBE73E8EE2F8FECF09FF8D7C324DCE91021E5EEBA581B4FB1DA15ADA7946C8069F063A4EC562AA0D; sso.jd.com=83a98e7a4f9f46cc8e5900d2bb0e1edb; __jda=62267774.15470820680701341087814.1547082068.1547625357.1547628937.32; __jdb=62267774.21.15470820680701341087814|32.1547628937");
        headers.put("Host", "cjg-api.jd.com");
        headers.put("Origin", "http://cjg.jd.com");
        headers.put("Pragma", "no-cache");
        headers.put("Referer", "http://cjg.jd.com/web/");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        urls.add("http://cjg-api.jd.com/api/component/method/info?cid=11025");
        urls.add("http://cjg-api.jd.com/api/currentUser");
        urls.add("http://cjg-api.jd.com/api/component/atlas/get/componentReverseLink?cid=11025");
        urls.add("http://cjg-api.jd.com/api/component/method/info?cid=11025");
        urls.add("http://cjg-api.jd.com/api/component/interfacetype/get?componentId=10977&producted=1");
    }

    @Test
    public void test(){
        for(String url : urls){
            test1(url);
        }
    }


    public void test1(String url) {
        long max = 0;
        long sum = 0;
        long avg = 0;
        long c = 0 ;
        for (int i = 0; i < count; i++) {
            long start = System.currentTimeMillis();
            MyHttpClient.doGet(url,headers);
            long end = System.currentTimeMillis();

            long spend = end - start;
            sum = sum + spend;
            if (spend > max) {
                max = spend;
            }
            if(spend > 1000){
                c++;
            }
        }
        avg = sum/count;

        System.out.println("接口 " + url + " 共调用："+count+"次，平均耗时：" + avg + "毫秒，最大耗时："+max+"毫秒，其中大于1秒耗时的共"+c+"次");
    }


}
