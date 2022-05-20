package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    int deleteById(Long id);

    int insert(SetmealDish setmealDish);

    SetmealDish selectById(Long id);

    int updateByIdSelective(SetmealDish setmealDish);

    int updateById(SetmealDish setmealDish);

    SetmealDish selectSetmealDishesBySetmealId(String SetmealId);

    void insertSetmealDishs(List<SetmealDish> setmealDishes);

    void deleteByDishIds(String[] ids);
}