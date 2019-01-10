package demo.wk.springboot4mybaits.service.impl;

import demo.wk.springboot4mybaits.dao.TradeRecordDomainMapper;
import demo.wk.springboot4mybaits.domain.TradeRecordDomain;
import demo.wk.springboot4mybaits.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @ClassName TradeRecordServiceImpl
 * @Description trade_record表的服务实现类
 * @Author wangkai60
 * @Date 2019/1/4 14:31
 * @Version 1.0
 **/
@Service
public class TradeRecordServiceImpl implements TradeRecordService {

    @Autowired
    private TradeRecordDomainMapper tradeRecordDomainMapper;

    @Override
    public List<TradeRecordDomain> getList(int start, int length) {
        try {
            List<TradeRecordDomain> recordEntities = tradeRecordDomainMapper.getAll(start,length);
            return recordEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void randomInsert() {
        TradeRecordDomain tradeRecordDomain = new TradeRecordDomain();
        Random random = new Random();
        tradeRecordDomain.setMerchantCode("testmerchant");
        tradeRecordDomain.setMerchantOrderId(random.nextInt(1000000000));
        tradeRecordDomain.setTradeId(random.nextInt(1000000000));
        tradeRecordDomain.setRemark("this is test record");
        try {
            int result = tradeRecordDomainMapper.insert(tradeRecordDomain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TradeRecordDomain get(int id) {
        TradeRecordDomain tradeRecordDomain = tradeRecordDomainMapper.selectByPrimaryKey(id);
        return tradeRecordDomain;
    }
}
