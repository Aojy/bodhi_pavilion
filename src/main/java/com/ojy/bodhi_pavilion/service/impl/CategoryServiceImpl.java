package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.CategoryMapper;
import com.ojy.bodhi_pavilion.pojo.Category;
import com.ojy.bodhi_pavilion.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Map<String, Object> queryCategoryList(Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("start",(page - 1) * pageSize);
        map.put("size", pageSize);
        List<Category> data = categoryMapper.selectCategoryList(map);
        int total = categoryMapper.selectTotal();
        map.clear();
        map.put("records", data);
        map.put("total", total);
        return map;
    }

    @Override
    public boolean saveCategory(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    @Override
    public boolean updateCategory(Category emp) {
        return categoryMapper.updateByIdSelective(emp) > 0;
    }

    @Override
    public boolean deleteCategoryByIds(String ids) {
        return categoryMapper.deleteById(ids) > 0;
    }

    @Override
    public List<Category> queryCategoryByType(Integer type, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        if (page != null && pageSize != null) {
            map.put("start", (page - 1) * pageSize);
            map.put("size", pageSize);
        }
        return categoryMapper.selectByType(map);
    }
}
