package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {
    int insert(Setmeal setmeal);

    Setmeal selectById(String id);

    int updateByIdSelective(Setmeal setmeal);

    List<Setmeal> selectSetmealList(Map<String, Object> map);

    int selectTotalByCondition(String name);

    int deleteByIds(String[] ids);

    int updateSetmeals(Map<String, Object> map);

    List<Setmeal> selectListByCategoryId(String categoryId);
}