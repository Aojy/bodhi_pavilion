package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Category;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.CategoryService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询所有分类数据
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result getCategoryList(Integer page, Integer pageSize) {
        try {
            return Result.success(categoryService.queryCategoryList(page, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 删除对应分类数据
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deleteCategory(String ids) {
        try {
            return Result.success(categoryService.deleteCategoryByIds(ids));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 保存新增分类数据
     * @param category
     * @param session
     * @return
     */
    @PostMapping
    public Result saveCategory(@RequestBody Category category, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            if (category != null && emp != null) {
                category.setId(GetId.getId());
                category.setCreateTime(new Date());
                category.setCreateUser(emp.getId());
                category.setUpdateTime(new Date());
                category.setUpdateUser(emp.getId());
                return Result.success(categoryService.saveCategory(category));
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 修改对应分类信息
     * @param emp
     * @return
     */
    @PutMapping
    public Result updateCategory(@RequestBody Category emp) {
        try {
            return Result.success(categoryService.updateCategory(emp));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 根据type分页查询分类数据
     * @param type
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result list(Integer type, Integer page, Integer pageSize) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("type", type);
            if (page != null && pageSize != null) {
                map.put("start", (page - 1) * pageSize);
                map.put("size", pageSize);
            }
            return Result.success(categoryService.queryCategoryByType(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
