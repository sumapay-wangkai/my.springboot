package demo.wk.springboot4mybaits.service.impl;

import demo.wk.springboot4mybaits.service.DemoService;
import demo.wk.springboot4mybaits.util.GeneCnChar;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoServiceImpl
 * @Description demo接口实现类
 * @Author wangkai60
 * @Date 2019/1/4 10:11
 * @Version 1.0
 **/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String helloWorld() {
        char c = GeneCnChar.getRandomChar();
        return String.valueOf(c);
    }
}
