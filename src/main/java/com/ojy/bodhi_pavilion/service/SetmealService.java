package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.dto.SetmealDto;
import com.ojy.bodhi_pavilion.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    Map<String, Object> getSetmealList(Map<String, Object> map);

    Setmeal getSetmeal(String id);

    boolean saveSetmeal(SetmealDto setmeal);

    boolean updateSetmeal(SetmealDto dto);

    boolean deleteSetmealByIds(String[] strings);

    boolean updateSetmealStatus(Map<String, Object> map);

    List<Setmeal> getList(String categoryId);
}
