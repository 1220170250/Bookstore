package com.qinkai.web;

import com.qinkai.pojo.User;
import com.qinkai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session) {
        //调用Service方法验证
        User user = userService.login(new User(null, username, password, null));

        //验证用户名和密码
        ModelAndView m;
        if (user != null) {
            //登陆成功
            //保存用户信息到Session域中
            session.setAttribute("user", user);
            m = new ModelAndView("user/login_success");
        } else {
            //把错误信息和用户名保存到隐含模型中
            m = new ModelAndView("user/login");
            m.addObject("msg", "用户名或密码错误！");
            m.addObject("username", username);
        }
        return m;
    }

    /**
     * 注册
     *
     * @param user
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(@Valid User user, BindingResult result, String code, HttpSession session) {
        ModelAndView m;

        //是否有校验错误
        boolean hasErrors = result.hasErrors();
        if (hasErrors) {
            m = new ModelAndView("user/regist");
            m.addObject("msg", "输入信息有错！");
            m.addObject("username", user.getUsername());
            m.addObject("email", user.getEmail());
            //转发到错误界面
        } else {
            //获取Session中的验证码
            String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
            //删除Session域中的验证码
            session.removeAttribute(KAPTCHA_SESSION_KEY);
            //判断验证码,在第一次表单提交后会删除Session域中的验证码，当再次接到表单提交请求时token若为空阻止操作，防止表单重复提交
            if (token != null && token.equalsIgnoreCase(code)) {
                //验证码正确
                //调用Service进行Dao操作检测用户名是否可用
                if (userService.existsUsername(user.getUsername())) {
                    //把错误信息和用户名保存到隐含模型中
                    m = new ModelAndView("user/regist");
                    m.addObject("msg", "当前用户名已存在！");
                    m.addObject("username", user.getUsername());
                    m.addObject("email", user.getEmail());
                } else {
                    //当前用户名可用，调用Service进行Dao操作保存注册信息，并跳转到注册成功界面
                    userService.registerUser(user);
                    m = new ModelAndView("user/regist_success");
                }
            } else {
                //验证码不正确，则跳回注册界面
                //把错误信息和用户名保存到隐含模型中
                m = new ModelAndView("user/regist");
                m.addObject("msg", "验证码错误！");
                m.addObject("username", user.getUsername());
                m.addObject("email", user.getEmail());
            }
        }
        return m;
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //销毁Session域中用户登录信息
        session.invalidate();
        //重定向到首页req.getContextPath()获得项目路径
        return "redirect:/";
    }

    /**
     * Ajax请求判断用户名是否存在
     * ResponseBody注解：当返回值为对象时自动封装为json,放到响应体中
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxExistsUsername")
    public Map<String, Object> ajaxExistsUsername(String username) {
        //调用service方法判断用户名是否存在
        boolean existsUsername = userService.existsUsername(username);
        //将结果封装成Map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        return resultMap;
    }
}
