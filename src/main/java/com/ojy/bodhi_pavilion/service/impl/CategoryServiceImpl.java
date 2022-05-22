package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.CategoryMapper;
import com.ojy.bodhi_pavilion.pojo.Category;
import com.ojy.bodhi_pavilion.service.CategoryService;
import com.ojy.bodhi_pavilion.service.DishService;
import com.ojy.bodhi_pavilion.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 分页查询category所有数据
     * @param page
     * @param pageSize
     * @return
     */
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

    /**
     * 保存新增的category数据
     * @param category
     * @return
     */
    @Override
    public boolean saveCategory(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    /**
     * 修改对应category数据
     * @param emp
     * @return
     */
    @Override
    public boolean updateCategory(Category emp) {
        return categoryMapper.updateByIdSelective(emp) > 0;
    }

    /**
     * 删除对应id的category数据
     * @param ids
     * @return
     */
    @Override
    public boolean deleteCategoryByIds(String ids) {
        String[] arr = new String[]{ids};
        if (!dishService.deleteDishByIds(arr)) {
            setmealService.deleteSetmealByIds(arr);
        }
        return categoryMapper.deleteById(ids) > 0;
    }

    /**
     * 根据type分页查询category数据
     * @param map
     * @return
     */
    @Override
    public List<Category> queryCategoryByType(Map<String, Object> map) {
        return categoryMapper.selectByType(map);
    }
}
