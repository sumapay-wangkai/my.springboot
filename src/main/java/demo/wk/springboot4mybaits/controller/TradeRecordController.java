package demo.wk.springboot4mybaits.controller;

import com.alibaba.fastjson.JSONObject;
import demo.wk.springboot4mybaits.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TradeRecordController
 * @Description tradeRecord服务
 * @Author wangkai60
 * @Date 2019/1/4 14:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tradeRecord")
public class TradeRecordController {

    @Autowired
    TradeRecordService tradeRecordService;

    @RequestMapping("/get")
    public String get(@RequestParam(value = "id", required = true) int id) {
        String result = JSONObject.toJSONString(tradeRecordService.get(id));
        return result;
    }

    @RequestMapping("/getList")
    public String getList(@RequestParam(value = "start", defaultValue = "0") int start,
                          @RequestParam(value = "end", defaultValue = "10") int end) {
        String result = JSONObject.toJSONString(tradeRecordService.getList(start, end));
        return result;
    }

    @RequestMapping("/randomInsert")
    public int randomInsert(@RequestParam(value = "count", defaultValue = "1") int count) {
        int completeCount = 0;
        for (int i = 0; i < count; i++) {
            System.out.println("i=" + i);
            tradeRecordService.randomInsert();
            completeCount++;
        }
        return completeCount;
    }


    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
