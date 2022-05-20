package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.User;

public interface UserService {
    User isUser(String phone);

    boolean saveUser(User user);
}
