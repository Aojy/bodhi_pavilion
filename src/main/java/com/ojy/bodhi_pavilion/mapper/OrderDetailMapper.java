package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    int deleteById(String id);

    int insert(OrderDetail orderDetail);

    OrderDetail selectById(String id);

    int updateByIdSelective(OrderDetail orderDetail);
}