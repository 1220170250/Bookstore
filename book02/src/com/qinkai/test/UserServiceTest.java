package com.qinkai.test;

import com.qinkai.pojo.User;
import com.qinkai.service.UserService;
import com.qinkai.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"zhangsan","456123","zs@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login("admin","admin"));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("lisi")){
            System.out.println("当前用户名已存在");
        }else{
            System.out.println("当前用户名可用");
        }
    }
}