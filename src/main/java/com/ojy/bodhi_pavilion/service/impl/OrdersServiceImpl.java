package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.OrdersDto;
import com.ojy.bodhi_pavilion.mapper.OrderDetailMapper;
import com.ojy.bodhi_pavilion.mapper.OrdersMapper;
import com.ojy.bodhi_pavilion.mapper.ShoppingCartMapper;
import com.ojy.bodhi_pavilion.pojo.OrderDetail;
import com.ojy.bodhi_pavilion.pojo.Orders;
import com.ojy.bodhi_pavilion.pojo.ShoppingCart;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.OrdersService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Transactional( rollbackFor = RuntimeException.class)
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ShoppingCartMapper scMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public boolean submitOrderFrom(Orders orders) {
        List<ShoppingCart> cartList = scMapper.selectShoppingCartByUserId(orders.getUserId());
        BigDecimal amount = new BigDecimal(0);
        for (ShoppingCart cart : cartList) {
            amount = amount.add(cart.getAmount().multiply(new BigDecimal(cart.getNumber())));
            OrderDetail od = new OrderDetail();
            od.setId(GetId.getId());
            od.setOrderId(orders.getId());
            od.setName(cart.getName());
            od.setNumber(cart.getNumber());
            od.setAmount(cart.getAmount());
            od.setImage(cart.getImage());
            od.setDishId(cart.getDishId());
            od.setDishFlavor(cart.getDishFlavor());
            od.setSetmealId(cart.getSetmealId());
            orderDetailMapper.insert(od);
        }
        orders.setAmount(amount);
        return ordersMapper.insert(orders) > 0 && scMapper.deleteByUserId(orders.getUserId()) > 0;
    }

    @Override
    public Map<String, Object> queryUserPage(Map<String, Object> map) {
        List<OrdersDto> list = ordersMapper.selectOrdersByUserId(map);
        int total = ordersMapper.selectTotal((String) map.get("userId"));
        int pages = total / (Integer) map.get("size");
        map.clear();
        map.put("records", list);
        map.put("pages", pages);
        return map;
    }

    @Override
    public Map<String, Object> queryOrdersList(Map<String, Object> map) {
        List<OrdersDto> list = ordersMapper.selectOrdersList(map);
        int total = ordersMapper.selectTotalByCondition(map);
        map.clear();
        map.put("records", list);
        map.put("total", total);
        return map;
    }
}
