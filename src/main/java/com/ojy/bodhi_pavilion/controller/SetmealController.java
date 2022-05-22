package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.dto.SetmealDto;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.SetmealService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 分页查询所有套餐数据
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result getSetmeal(Integer page, Integer pageSize, String name) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (page - 1) * pageSize);
            map.put("size", pageSize);
            map.put("name", name);
            return Result.success(setmealService.getSetmealList(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 查询对应id的套餐数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private Result getSetmeal(@PathVariable String id) {
        try {
            return Result.success(setmealService.getSetmeal(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 保存新增的套餐数据信息
     * @param setmeal
     * @param session
     * @return
     */
    @PostMapping
    public Result saveSetmeal(@RequestBody SetmealDto setmeal, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            Date date = new Date();
            setmeal.setId(GetId.getId());
            setmeal.setCreateUser(emp.getId());
            setmeal.setCreateTime(date);
            setmeal.setUpdateUser(emp.getId());
            setmeal.setUpdateTime(date);
            setmeal.setStatus(1);
            setmeal.setIsDeleted(0);

            return Result.success(setmealService.saveSetmeal(setmeal));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 删除对应id的套餐数据
     * @param ids
     * @return
     */
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

    /**
     * 修改对应id套餐的status状态
     * @param code
     * @param ids
     * @return
     */
    @PostMapping("/status/{code}")
    public Result updateSetmealStatus(@PathVariable Integer code, String ids) {
        try {
            String[] strings = ids.split(",");
            Map<String, Object> map = new HashMap<>();
            map.put("ids", strings);
            map.put("code", code);
            return Result.success(setmealService.updateSetmealStatus(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 修改对应的套餐数据
     * @param dto
     * @param session
     * @return
     */
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

    /**
     * 查询categoryId对应类型的套餐数据
     * @param categoryId
     * @return
     */
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
