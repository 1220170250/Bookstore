package com.qinkai.web;

import com.google.gson.Gson;
import com.qinkai.pojo.User;
import com.qinkai.service.UserService;
import com.qinkai.service.impl.UserServiceImpl;
import com.qinkai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登录
     *
     * @throws ServletException
     * @throws IOException
     */

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用Service方法验证
        User user = userService.login(new User(null, username, password, null));

        //验证用户名和密码
        if (user != null) {
            //登陆成功
            //保存用户信息到Session域中
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //把错误信息和用户名保存到Request域中
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);

            //登录失败
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 注册
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session域中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //判断验证码,在第一次表单提交后会删除Session域中的验证码，当再次接到表单提交请求时token若为空阻止操作，防止表单重复提交
        if (token != null && token.equalsIgnoreCase(code)) {
            //验证码正确
            //调用Service进行Dao操作检测用户名是否可用
            if (userService.existsUsername(username)) {
                //把错误信息和用户名保存到Request域中
                req.setAttribute("msg", "当前用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //当前用户名不可用，则跳回注册界面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                System.out.println("当前用户名已存在");
            } else {
                //当前用户名可用，调用Service进行Dao操作保存注册信息，并跳转到注册成功界面
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            //把错误信息和用户名保存到Request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            //验证码不正确，则跳回注册界面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            System.out.println("验证码不正确");
        }
    }

    /**
     * Ajax请求判断用户名是否存在
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求表单参数
        String username = req.getParameter("username");
        //调用service方法判断用户名是否存在
        boolean existsUsername = userService.existsUsername(username);
        //将结果封装成Map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        //将Map对象转换为json格式
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        //通过输出流返回json字符串
        resp.getWriter().write(json);

    }

    /**
     * 注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session域中用户登录信息
        req.getSession().invalidate();
        //重定向到首页req.getContextPath()获得项目路径
        resp.sendRedirect(req.getContextPath());
    }

}
