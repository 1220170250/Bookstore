package com.qinkai.web;

import com.qinkai.service.UserService;
import com.qinkai.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //验证用户名和密码
        if(userService.login(username,password)!=null){
            //登陆成功
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req, resp);
        }else{
           //登录失败
            req.getRequestDispatcher("/pages/user/login.html").forward(req, resp);
        }
    }


}
