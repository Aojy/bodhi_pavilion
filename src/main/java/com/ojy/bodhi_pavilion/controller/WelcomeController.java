package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @RequestMapping("/admin")
    public String welcome(HttpSession session) {
        Employee employee= (Employee) session.getAttribute("emp");
        if (employee != null) {
            return "redirect:/backend/index.html";
        }
        return "backend/page/login/login";
    }

    @RequestMapping("/user")
    public String front(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/front/index.html";
        }
        return "redirect:/front/page/login.html";
    }

}
