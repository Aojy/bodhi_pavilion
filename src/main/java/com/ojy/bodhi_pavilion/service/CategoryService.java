package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Map<String, Object> queryCategoryList(Integer page, Integer pageSize);
    boolean saveCategory(Category category);

    boolean updateCategory(Category emp);

    boolean deleteCategoryByIds(String ids);

    List<Category> queryCategoryByType(Integer type, Integer page, Integer pageSize);
}
