package com.qinkai.web;


import com.qinkai.pojo.Cart;
import com.qinkai.pojo.Order;
import com.qinkai.pojo.User;
import com.qinkai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param session
     * @return
     */
    @RequestMapping("/createOrder")
    public String createOrder(HttpSession session){
        //获取购物车信息
        Cart cart = (Cart) session.getAttribute("cart");
        //获取用户信息
        User user = (User) session.getAttribute("user");

        //若用户未登录,跳转到登录界面
        if (user == null) {
            return "user/login";
        }
        //保存订单
        String orderId = orderService.creatOrder(cart, user.getId());
        session.setAttribute("orderId",orderId);
        //重定向到支付界面，由于涉及数据库的修改，使用重定向防止重复提交
        return "redirect:/pages/cart/checkout.jsp";

    }

    /**
     * 查询当前用户所有订单
     * @param session
     * @return
     */
    @RequestMapping("/showMyOrders")
    public ModelAndView showMyOrders(HttpSession session) {
        //获取用户信息
        User user = (User) session.getAttribute("user");
        //若用户未登录,跳转到登录界面
        ModelAndView m;
        if (user == null) {
            m =new ModelAndView("user/login");
            return m;
        }
        //查询我的订单数据
        List<Order> orders = orderService.showMyOrders(user.getId());
        //保存到隐含模型中
        m =new ModelAndView("order/order");
        m.addObject("orders",orders);
        return m;
    }
}
