package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.ShoppingCart;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.ShoppingCartService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 查询对应用户的购物车商品列表
     * @param session
     * @return
     */
    @GetMapping("/list")
    public Result list(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            return Result.success(shoppingCartService.queryShoppingCartByUserId(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试");
        }
    }

    /**
     * 添加对应用户的购物车商品
     * @param cart
     * @param session
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCart cart, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Date date = new Date();
            cart.setId(GetId.getId());
            cart.setCreateTime(date);
            cart.setUserId(user.getId());
            cart.setNumber(1);
            return Result.success(shoppingCartService.addCart(cart));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

    /**
     * 减少对应用户的购物车商品
     * @param cart
     * @param session
     * @return
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCart cart, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            cart.setUserId(user.getId());
            return Result.success(shoppingCartService.subCart(cart));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

    /**
     * 清空购物车
     * @param session
     * @return
     */
    @DeleteMapping("clean")
    public Result cleanCart(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            return Result.success(shoppingCartService.cleanCart(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

}
