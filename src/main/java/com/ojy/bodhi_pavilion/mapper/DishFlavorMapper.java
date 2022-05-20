package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    int deleteById(Long id);

    int insert(DishFlavor flavor);

    int insertSelective(DishFlavor flavor);

    DishFlavor selectById(Long id);

    int updateByIdSelective(DishFlavor flavor);

    int updateById(DishFlavor flavor);

    int insertFlavors(List<DishFlavor> flavors);

    int updateByIdsSelective(List<DishFlavor> flavors);

    void deleteByDishIds(String[] ids);
}