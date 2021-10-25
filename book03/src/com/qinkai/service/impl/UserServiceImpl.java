package com.qinkai.service.impl;

import com.qinkai.dao.UserDao;
import com.qinkai.dao.UserDaoImpl;
import com.qinkai.pojo.User;
import com.qinkai.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryByUserNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryByUserName(username) == null) {
            return false;
        } else {
            return true;
        }
    }
}
