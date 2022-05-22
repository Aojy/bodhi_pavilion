package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    void insertSetmealDishs(List<SetmealDish> setmealDishes);

    void deleteByDishIds(String[] ids);
}