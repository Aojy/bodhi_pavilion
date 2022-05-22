package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Orders;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.OrdersService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 提交订单
     * @param orders
     * @param session
     * @return
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody Orders orders, HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            Date date = new Date();
            orders.setId(GetId.getId());
            orders.setNumber(GetId.getId());
            orders.setUserId(user.getId());
            orders.setOrderTime(date);
            if (orders.getPayMethod() == 0 && orders.getPayMethod() == 1) {
                orders.setCheckoutTime(date);
            } else {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        orders.setPayMethod(400);
                        orders.setStatus(5);
                        ordersService.updateOrder(orders);
                    }
                }, new Date(orders.getOrderTime().getTime() + 5 * 60 * 1000));
            }
            return Result.success(ordersService.submitOrderFrom(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 分页查询对应用户的订单
     * @param page
     * @param pageSize
     * @param session
     * @return
     */
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


    /**
     * 查询所有订单数据
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
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

    /**
     * 修改订单状态
     * @param orders
     * @return
     */
    @PutMapping
    public Result updateOrder(@RequestBody Orders orders) {
        try {
            return Result.success(ordersService.updateOrder(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping("/again")
    public Result toAgain(@RequestBody Orders orders) {
        try {
            return Result.success(ordersService.submitOrderAgain(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
