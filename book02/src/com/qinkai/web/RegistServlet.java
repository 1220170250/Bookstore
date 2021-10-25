package com.qinkai.web;

import com.qinkai.pojo.User;
import com.qinkai.service.UserService;
import com.qinkai.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //判断验证码
        if ("6n6np".equalsIgnoreCase(code)) {
            //验证码正确
            //调用Service进行Dao操作检测用户名是否可用
          if (userService.existsUsername(username)) {
                //当前用户名不可用，则跳回注册界面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
                System.out.println("当前用户名已存在");
            } else {
                //当前用户名可用，调用Service进行Dao操作保存注册信息，并跳转到注册成功界面
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
            }

        } else {
            //验证码不正确，则跳回注册界面
            req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
            System.out.println("验证码不正确");
        }

    }
}
