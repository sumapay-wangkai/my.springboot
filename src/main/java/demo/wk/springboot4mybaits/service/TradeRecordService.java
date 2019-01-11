package demo.wk.springboot4mybaits.service;


import demo.wk.springboot4mybaits.domain.TradeRecordDomain;

import java.util.List;

/**
 * @ClassName TradeRecordService
 * @Description trade_record表的服务
 * @Author wangkai60
 * @Date 2019/1/4 14:30
 * @Version 1.0
 **/
public interface TradeRecordService {
    List<TradeRecordDomain> getList(int start, int length);

    void randomInsert();

    TradeRecordDomain get(int id);

    void testTransactionManager();

    void myTransactionManager(TradeRecordDomain tradeRecordDomain,String remark,boolean flag)throws Exception ;
}
