package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.UserMapper;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User isUser(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insert(user) > 0;
    }
}
