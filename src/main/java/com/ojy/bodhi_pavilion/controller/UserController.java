package com.ojy.bodhi_pavilion.controller;


import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.UserService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import com.ojy.bodhi_pavilion.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        try {
            User flag = userService.isUser(user.getPhone());
            if (flag == null) {
                user.setId(GetId.getId());
                if (userService.saveUser(user)) {
                    return Result.success(userService.isUser(user.getPhone()));
                }
                throw new  RuntimeException();
            }
            session.setAttribute("user", flag);
            return Result.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }

    }


    @PostMapping("/loginout")
    public Result loginOut(HttpSession session) {
        try {
            session.removeAttribute("user");
            return Result.success("");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
