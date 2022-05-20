package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
@Transactional
public interface CategoryMapper {
    int deleteById(String id);

    int insert(Category category);

    Category selectById(String id);

    int updateByIdSelective(Category category);

    int updateById(Category category);

    List<Category> selectCategoryList(Map<String, Object> map);

    int selectTotal();

    List<Category> selectByType(Map<String, Object> map);
}