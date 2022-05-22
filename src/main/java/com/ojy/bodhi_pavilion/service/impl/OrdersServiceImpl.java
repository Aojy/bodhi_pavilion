package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.OrdersDto;
import com.ojy.bodhi_pavilion.mapper.OrderDetailMapper;
import com.ojy.bodhi_pavilion.mapper.OrdersMapper;
import com.ojy.bodhi_pavilion.mapper.ShoppingCartMapper;
import com.ojy.bodhi_pavilion.pojo.OrderDetail;
import com.ojy.bodhi_pavilion.pojo.Orders;
import com.ojy.bodhi_pavilion.pojo.ShoppingCart;
import com.ojy.bodhi_pavilion.service.OrdersService;
import com.ojy.bodhi_pavilion.util.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 提交订单
     * @param orders
     * @return
     */
    @Override
    public boolean submitOrderFrom(Orders orders) {
        List<ShoppingCart> cartList = scMapper.selectShoppingCartByUserId(orders.getUserId());
        BigDecimal amount = new BigDecimal(6);
        // 遍历购物车的商品
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

    /**
     * 分页查询对应用户的订单
     * @param map
     * @return
     */
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

    /**
     * 查询所有订单数据
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryOrdersList(Map<String, Object> map) {
        List<OrdersDto> list = ordersMapper.selectOrdersList(map);
        int total = ordersMapper.selectTotalByCondition(map);
        map.clear();
        map.put("records", list);
        map.put("total", total);
        return map;
    }

    /**
     * 修改订单状态
     * @param orders
     * @return
     */
    @Override
    public boolean updateOrder(Orders orders) {
        Date date = orders.getCheckoutTime();
        if (date != null) {
            orders.setCheckoutTime(new Date(date.getTime() + 1000 * 60 * 60 * 12));
        }
        return ordersMapper.updateByIdSelective(orders) > 0;
    }

    /**
     * 再来一单
     * @param orders
     * @return
     */
    @Override
    public boolean submitOrderAgain(Orders orders) {
        OrdersDto ordersDto = ordersMapper.selectById(orders.getId());
        if (ordersDto.getOrderDetails() == null){
            return false;
        }
        ordersDto.setId(GetId.getId());
        ordersDto.setNumber(GetId.getId());
        ordersDto.setOrderTime(new Date());
        ordersDto.setCheckoutTime(new Date());
        ordersDto.setStatus(2);
        for (OrderDetail od : ordersDto.getOrderDetails()) {
            od.setId(GetId.getId());
            od.setOrderId(ordersDto.getId());
            orderDetailMapper.insert(od);
        }
        return ordersMapper.insert(ordersDto) > 0;
    }
}
