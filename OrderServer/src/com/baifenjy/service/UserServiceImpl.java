package com.baifenjy.service;

import com.baifenjy.dao.DaoFactory;
import com.baifenjy.dao.UserDao;
import com.baifenjy.vo.User;

public class UserServiceImpl implements UserService
{
    private static UserDao userDao = DaoFactory.getUserDao();

    @Override
    public boolean login(User user){
        return userDao.login(user);
    }
    
    @Override
    public void register(User user){
        userDao.register(user);
    }
}
