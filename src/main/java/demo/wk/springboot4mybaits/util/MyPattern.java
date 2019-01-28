package demo.wk.springboot4mybaits.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MyPattern
 * @Description 我的正则表达式
 * @Author wangkai60
 * @Date 2019/1/22 14:55
 * @Version 1.0
 **/
public class MyPattern {

    public static void main(String[] args) {
        String str = "aaa.jd6.Tcom";
        System.out.println(str + " : " + checkInterfaceName(str));


    }

    //此处效率有待提高，用完整的正则表达式没有达到预期，后续优化
    private static boolean checkInterfaceName(String interfaceName) {
        try {
            Pattern pattern = Pattern.compile("[A-Za-z0-9_\\.]+");
            Matcher matcher = pattern.matcher(interfaceName);
            if (matcher.matches()) {
                if (interfaceName.startsWith(".") || interfaceName.endsWith(".")) {
                    return false;
                }
                String[] arr = interfaceName.split("\\.");
                System.out.println("arr"+ JSON.toJSONString(arr));
                for (String s : arr) {
                    s = s.trim();
                    if ("".equals(s.trim())) {
                        //此处说明存在 .. ，存在连续多个英文点号相连
                        System.out.println();
                        return false;
                    }
                    Pattern pattern1 = Pattern.compile("^[0-9]+[A-Za-z0-9_\\.]+");
                    Matcher matcher1 = pattern1.matcher(s);
                    if (matcher1.matches()) {
                        System.out.println(s+"以数字开头");
                        //package每层不得以数字开头
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
