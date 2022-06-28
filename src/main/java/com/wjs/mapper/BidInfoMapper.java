package com.wjs.mapper;

import com.wjs.entity.B_Bid_Info;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjs
 * @create 2022-06-28 17:04
 */
@Repository
public interface BidInfoMapper {
    //查询所有的投资信息
    List<B_Bid_Info> findAll();
}
