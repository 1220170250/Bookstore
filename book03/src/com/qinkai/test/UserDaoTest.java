package com.qinkai.test;

import com.qinkai.dao.UserDao;
import com.qinkai.dao.UserDaoImpl;
import com.qinkai.pojo.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void queryByUserName() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryByUserName("com/qinkai")==null){
            System.out.println("当前用户名可用");
        }else{
            System.out.println("当前用户名已存在");
        }
    }

    @Test
    public void queryByUserNameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryByUserNameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误，登录失败");
        }else{
            System.out.println("登录成功");
        }

    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        User user =new User(null,"zj","123456","zj@qq.com");
        if(userDao.saveUser(user) == -1){
            System.out.println("注册失败");
        }else{
            System.out.println("注册成功");
        }
    }
}