package com.baifenjy.service;

import com.baifenjy.vo.User;

public interface UserService
{

    void register(User user);

    boolean login(User user);

}
