package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.dto.OrdersDto;
import com.ojy.bodhi_pavilion.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {
    int deleteById(String id);

    int insert(Orders orders);

    OrdersDto selectById(String id);

    int updateByIdSelective(Orders orders);

    List<OrdersDto> selectOrdersByUserId(Map<String, Object> map);

    int selectTotal(String userId);

    int selectTotalByCondition(Map<String, Object> map);

    List<OrdersDto> selectOrdersList(Map<String, Object> map);
}