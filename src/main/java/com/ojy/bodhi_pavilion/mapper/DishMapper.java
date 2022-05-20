package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
@Transactional
public interface DishMapper {
    int insert(Dish dish);

    DishDto selectById(Long id);

    int updateByIdSelective(Dish dish);

    int updateById(Dish dish);

    List<DishDto> selectDishList(Map<String, Object> map);

    int selectTotalByCondition(String name);

    int deleteByIds(String[] ids);

    int updateDishes(Map<String, Object> map);

    List<Dish> selectDishByCategoryId(@Param("cId") String categoryId, @Param("name") String name);
}