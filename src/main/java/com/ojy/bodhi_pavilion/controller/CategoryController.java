package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Category;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.CategoryService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import com.ojy.bodhi_pavilion.uitl.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/page")
    public Result getCategoryList(Integer page, Integer pageSize) {
        try {
            return Result.success(categoryService.queryCategoryList(page, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    @DeleteMapping
    public Result deleteCategory(String ids) {
        try {
            return Result.success(categoryService.deleteCategoryByIds(ids));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

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

    @PutMapping
    public Result updateCategory(@RequestBody Category emp) {
        try {
            return Result.success(categoryService.updateCategory(emp));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }


    @GetMapping("/list")
    public Result list(Integer type, Integer page, Integer pageSize) {
        try {
            List<Category> list = categoryService.queryCategoryByType(type, page, pageSize);
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
