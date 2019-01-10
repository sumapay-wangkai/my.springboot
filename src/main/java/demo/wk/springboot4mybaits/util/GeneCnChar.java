package demo.wk.springboot4mybaits.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @ClassName GeneCnChar
 * @Description 随机生成中文工具类
 * @Author wangkai60
 * @Date 2019/1/4 10:13
 * @Version 1.0
 **/
public class GeneCnChar {
    public static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str.charAt(0);
    }

}
