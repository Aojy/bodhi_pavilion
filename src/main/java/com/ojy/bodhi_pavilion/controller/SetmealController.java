package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.dto.SetmealDto;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.SetmealService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import com.ojy.bodhi_pavilion.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @GetMapping("/page")
    public Result getSetmeal(Integer page, Integer pageSize, String name) {
        try {
            return Result.success(setmealService.getSetmealList(page, pageSize, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @GetMapping("/{id}")
    private Result getSetmeal(@PathVariable String id) {
        try {
            return Result.success(setmealService.getSetmeal(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping
    public Result saveSetmeal(@RequestBody SetmealDto setmeal, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            Date date = new Date();
            setmeal.setCreateUser(emp.getId());
            setmeal.setCreateTime(date);
            setmeal.setUpdateUser(emp.getId());
            setmeal.setUpdateTime(date);
            setmeal.setId(GetId.getId());
            setmeal.setStatus(1);
            setmeal.setIsDeleted(0);

            return Result.success(setmealService.saveSetmeal(setmeal));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @DeleteMapping
    public Result deleteSetmeal(String ids) {
        try {
            String[] strings = ids.split(",");
            return Result.success(setmealService.deleteSetmealByIds(strings));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping("/status/{code}")
    public Result updateSetmealStatus(@PathVariable Integer code, String ids) {
        try {
            String[] strings = ids.split(",");
            return Result.success(setmealService.updateSetmealStatus(strings,code));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PutMapping
    public Result updateSetmeal(@RequestBody SetmealDto dto, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            Date date = new Date();
            dto.setUpdateTime(date);
            dto.setUpdateUser(emp.getId());
            return Result.success(setmealService.updateSetmeal(dto));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }


    @GetMapping("/list")
    public Result getList(String categoryId) {
        try {
            return Result.success(setmealService.getList(categoryId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
