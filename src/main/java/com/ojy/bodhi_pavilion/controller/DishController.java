package com.ojy.bodhi_pavilion.controller;


import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.DishService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dish")
public class DishController {


    @Autowired
    private DishService dishService;

    /**
     * 分页查询对应菜品的所有数据
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result getDishList(Integer page, Integer pageSize, String name) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (page - 1) * pageSize);
            map.put("size", pageSize);
            map.put("name", name);
            return Result.success(dishService.getDishList(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 根据id查询对应的菜品数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private Result getDish(@PathVariable String id) {
        try {
            return Result.success(dishService.getDish(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 保存新增的菜品数据
     * @param dish
     * @param session
     * @return
     */
    @PostMapping
    public Result saveDish(@RequestBody DishDto dish, HttpSession session) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 删除对应的菜品数据
     * @param ids
     * @return
     */
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

    /**
     * 修改对应菜品数据的启售与停售
     * @param code
     * @param ids
     * @return
     */
    @PostMapping("/status/{code}")
    public Result updateDishStatus(@PathVariable Integer code, String ids) {
        try {
            String[] strings = ids.split(",");
            Map<String, Object> map = new HashMap<>();
            map.put("ids", strings);
            map.put("code", code);
            return Result.success(dishService.updateDishStatus(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 修改对应菜品数据
     * @param dto
     * @param session
     * @return
     */
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

    /**
     * 查询categoryId对应类型的菜品数据
     * @param categoryId
     * @param name
     * @return
     */
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