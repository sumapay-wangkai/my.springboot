package demo.wk.springboot4mybaits.dao;

import demo.wk.springboot4mybaits.domain.TradeRecordDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeRecordDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecordDomain record);

    int insertSelective(TradeRecordDomain record);

    TradeRecordDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRecordDomain record);

    int updateByPrimaryKey(TradeRecordDomain record);

    List<TradeRecordDomain> getAll(@Param("start") int start,@Param("length")int length);

    TradeRecordDomain getLast();


}