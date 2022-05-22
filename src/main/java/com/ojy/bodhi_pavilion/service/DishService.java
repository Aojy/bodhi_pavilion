package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.pojo.Dish;
import com.ojy.bodhi_pavilion.pojo.DishFlavor;

import java.util.List;
import java.util.Map;

public interface DishService {
    boolean saveDish(DishDto dto);

    Map<String, Object> getDishList(Map<String, Object> map);

    DishDto getDish(String id);

    boolean deleteDishByIds(String[] ids);

    boolean updateDishStatus(Map<String, Object> map);

    boolean updateDish(DishDto dto);

    List<Dish> queryDishByCategoryId(String categoryId, String name);
}
