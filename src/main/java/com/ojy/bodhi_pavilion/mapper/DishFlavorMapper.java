package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    int insertFlavors(List<DishFlavor> flavors);

    int deleteByDishIds(String[] ids);
}