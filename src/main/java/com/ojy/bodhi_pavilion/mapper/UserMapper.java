package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteById(String user);

    int insert(User user);

    User selectById(String user);

    int updateByIdSelective(User user);

    User selectUserByPhone(String phone);
}