package demo.wk.springboot4mybaits.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HeaderUtil
 * @Description header工具类
 * @Author wangkai60
 * @Date 2019/1/28 10:16
 * @Version 1.0
 **/
public class HeaderUtil {

    public static Map<String, String> getHeaderFromTxt(String fileName) {
        Map<String, String> map = new HashMap<>();

        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                int index = lineTxt.indexOf(":");
                String key = lineTxt.substring(0, index);
                String value = lineTxt.substring(index + 1, lineTxt.length());
                map.put(key.trim(), value.trim());
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
        return  map;
    }

}
