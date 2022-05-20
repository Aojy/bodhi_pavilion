package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Orders;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.OrdersService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import com.ojy.bodhi_pavilion.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @PostMapping("/submit")
    public Result submit(@RequestBody Orders orders, HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            Date date = new Date();
            orders.setId(GetId.getId());
            orders.setStatus(2);
            orders.setNumber(GetId.getId());
            orders.setUserId(user.getId());
            orders.setOrderTime(date);
            orders.setCheckoutTime(date);
            orders.setPayMethod(1);
            return Result.success(ordersService.submitOrderFrom(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @GetMapping("/userPage")
    public Result getUserPage(Integer page, Integer pageSize, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getId());
            map.put("start", (page - 1) * pageSize);
            map.put("size", pageSize);
            return Result.success(ordersService.queryUserPage(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }


    @GetMapping("/page")
    public Result getOrders(Integer page, Integer pageSize,
                            String number, String beginTime, String endTime) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (page - 1) * pageSize);
            map.put("size", pageSize);
            map.put("id", number);
            map.put("beginTime", beginTime);
            map.put("endTime", endTime);
            return Result.success(ordersService.queryOrdersList(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
