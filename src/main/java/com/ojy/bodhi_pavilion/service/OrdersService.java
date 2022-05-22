package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.Orders;

import java.util.Map;

public interface OrdersService {
    boolean submitOrderFrom(Orders orders);

    Map<String, Object> queryUserPage(Map<String, Object> map);

    Map<String, Object> queryOrdersList(Map<String, Object> map);

    boolean updateOrder(Orders orders);

    boolean submitOrderAgain(Orders orders);
}
