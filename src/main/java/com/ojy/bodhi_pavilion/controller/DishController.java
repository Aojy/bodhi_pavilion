package com.ojy.bodhi_pavilion.controller;


import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.DishService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import com.ojy.bodhi_pavilion.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/dish")
public class DishController {


    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result getDishList(Integer page, Integer pageSize, String name) {
        try {
            return Result.success(dishService.getDishList(page, pageSize, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @GetMapping("/{id}")
    private Result getDish(@PathVariable String id) {
        try {
            return Result.success(dishService.getDish(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping
    public Result saveDish(@RequestBody DishDto dish, HttpSession session) {
        try {
            if (dish != null) {
                Employee emp = (Employee) session.getAttribute("emp");
                Date date = new Date();
                dish.setCreateUser(emp.getId());
                dish.setCreateTime(date);
                dish.setUpdateUser(emp.getId());
                dish.setUpdateTime(date);
                dish.setId(GetId.getId());
                dish.setStatus(1);
                dish.setSort(0);
                dish.setIsDeleted(0);


                return Result.success(dishService.saveDish(dish));
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @DeleteMapping
    public Result deleteDish(String ids) {
        try {
            String[] strings = ids.split(",");
            return Result.success(dishService.deleteDishByIds(strings));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PostMapping("/status/{code}")
    public Result updateDishStatus(@PathVariable Integer code, String ids) {
        try {
            String[] strings = ids.split(",");
            return Result.success(dishService.updateDishStatus(strings,code));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @PutMapping
    public Result updateDish(@RequestBody DishDto dto, HttpSession session) {
        try {
            if (dto != null) {
                Employee emp = (Employee) session.getAttribute("emp");
                Date date = new Date();
                dto.setUpdateTime(date);
                dto.setUpdateUser(emp.getId());
                return Result.success(dishService.updateDish(dto));
            }
            return Result.error("系统繁忙，请稍后重试...");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @GetMapping("/list")
    public Result getDishList(String categoryId, String name) {
        try {
            return Result.success(dishService.queryDishByCategoryId(categoryId, name));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }
}