package com.qinkai.web;

import com.qinkai.pojo.Cart;
import com.qinkai.pojo.Order;
import com.qinkai.pojo.User;
import com.qinkai.service.BookService;
import com.qinkai.service.OrderService;
import com.qinkai.service.impl.OrderServiceImpl;
import com.qinkai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {

    private OrderService orderService = WebUtils.getBeanFromIoc(OrderService.class);

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户信息
        User user = (User) req.getSession().getAttribute("user");

        //若用户未登录,跳转到登录界面
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        //保存订单
        String orderId = orderService.creatOrder(cart, user.getId());


        req.getSession().setAttribute("orderId",orderId);
        //重定向到支付界面，由于涉及数据库的修改，使用重定向防止重复提交
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户信息
        User user = (User) req.getSession().getAttribute("user");
        //若用户未登录,跳转到登录界面
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        //查询我的订单数据
        List<Order> orders = orderService.showMyOrders(user.getId());
        //保存到request域中
        req.setAttribute("orders",orders);
        //请求转发
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }
}
