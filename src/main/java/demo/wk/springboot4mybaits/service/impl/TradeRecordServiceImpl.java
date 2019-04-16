package demo.wk.springboot4mybaits.service.impl;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.alibaba.fastjson.JSON;
import demo.wk.springboot4mybaits.dao.TradeRecordDomainMapper;
import demo.wk.springboot4mybaits.domain.TradeRecordDomain;
import demo.wk.springboot4mybaits.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

/**
 * @ClassName TradeRecordServiceImpl
 * @Description trade_record表的服务实现类
 * @Author wangkai60
 * @Date 2019/1/4 14:31
 * @Version 1.0
 **/
@Service
@Transactional
public class TradeRecordServiceImpl implements TradeRecordService {

    @Autowired
    private TradeRecordDomainMapper tradeRecordDomainMapper;

    Map<Integer,Long> spendMap = new HashMap<>();

    @Override
    public List<TradeRecordDomain> getList(int start, int length) {
        try {
            List<TradeRecordDomain> recordEntities = tradeRecordDomainMapper.getAll(start, length);
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

    @Override
    public int getSeq(String seqName) {
        return tradeRecordDomainMapper.getSeq();
    }

    @Override
    @Transactional
    public void testTransactionManager() {
        for (int i = 0; i < 10; i++) {
            System.out.println("start");
            TradeRecordDomain tradeRecordDomain = tradeRecordDomainMapper.getLast();
            boolean flag = i % 2 == 0;
            try {
                myTransactionManager(tradeRecordDomain, String.valueOf(i), flag);
            } catch (Exception e) {
                System.out.println("************************");
            }
            TradeRecordDomain tradeRecordDomain2 = tradeRecordDomainMapper.getLast();
            System.out.println("事务结束" + JSON.toJSONString(tradeRecordDomain2));
            System.out.println("end");
        }
    }

    @Transactional
    public void myTransactionManager(TradeRecordDomain tradeRecordDomain, String remark, boolean flag) throws Exception {

        System.out.println("事务启动" + JSON.toJSONString(tradeRecordDomain));
        tradeRecordDomain.setRemark(remark);
        tradeRecordDomainMapper.updateByPrimaryKey(tradeRecordDomain);
        System.out.println("after update" + JSON.toJSONString(tradeRecordDomain));
        if (flag) {
            throw new Exception("");
        }
    }

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1000, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));


    public String testSeq(String seqName,int concurrent,int count) {
        CountDownLatch countDownLatch = new CountDownLatch(concurrent);
        System.out.println("*******************************************开始执行*******************************************");
        spendMap.clear();
        long start = System.currentTimeMillis();
        for (int i = 0; i < concurrent; i++) {
            GetSeq getSeq = new GetSeq(i,count,countDownLatch);
            executor.submit(getSeq);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long  end = System.currentTimeMillis();
        long spend = end - start;
        System.out.println("*******************************************全部执行完成*******************************************");
        System.out.println("并发"+concurrent+"，每个线程获取"+count+"个seq，共耗时："+spend+"毫秒");
        System.out.println("共获取到的seq数量："+spendMap.size());
        Long all = 0L;
        for(Long l : spendMap.values()){
            all = all + l;
        }
        Long avg = all/spendMap.size();
        System.out.println("平均每个seq获取时间为："+avg+"毫秒");
        return "";
    }

    Object lockObject = new Object();

    class GetSeq extends Thread {
        private int count;
        private int seq;
        CountDownLatch countDownLatch;

        public GetSeq(int seq,int count, CountDownLatch countDownLatch) {
            this.seq = seq;
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Transactional
        public void run() {
            Map<Integer,Long> map = new HashMap<>();
            try {
                System.out.println("第"+seq+"个线程开始生成");
                for (int i = 0; i < count; i++) {
                    long start = System.currentTimeMillis();
                    int seq = tradeRecordDomainMapper.getSeq();
                    long end = System.currentTimeMillis();
                    long spend = end - start;
                    if(map.containsKey(seq)){
                        System.out.println("*******************************seq 重复"+seq);
                    }else{
                        map.put(seq,spend);
                    }
                }
                System.out.println("第"+seq+"个线程全部生成");
                synchronized (lockObject){
                    System.out.println("*******************************开始合并");
                    System.out.println("spendMap 合并前 size = "+spendMap.size());
                    System.out.println("map 合并前 size = "+map.size());
                    spendMap.putAll(map);
                    map.clear();
                    System.out.println("spendMap 合并后 size = "+spendMap.size());
                    System.out.println("*******************************完成合并");
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                countDownLatch.countDown();
            }
        }
    }


}
