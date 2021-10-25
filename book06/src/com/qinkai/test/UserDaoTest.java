package com.qinkai.test;

import com.qinkai.dao.UserDao;
import com.qinkai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {

    ApplicationContext ac = new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
    UserDao userDao = (UserDao) ac.getBean("userDao");


    @Test
    public void queryByUserName() {

        if (userDao.queryByUserName("admin") == null) {
            System.out.println("当前用户名可用");
        } else {
            System.out.println("当前用户名已存在");
        }
    }

    @Test
    public void queryByUserNameAndPassword() {

        if (userDao.queryByUserNameAndPassword("admin", "admin") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("登录成功");
        }

    }

    @Test
    public void saveUser() {

        User user = new User(null, "zj", "123456", "zj@qq.com");
        if (userDao.saveUser(user) == -1) {
            System.out.println("注册失败");
        } else {
            System.out.println("注册成功");
        }

    }
}