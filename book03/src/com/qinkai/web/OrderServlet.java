package com.qinkai.web;

import com.qinkai.pojo.Book;
import com.qinkai.pojo.Order;
import com.qinkai.service.OrderService;
import com.qinkai.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询订单数据
        List<Order> orders = orderService.showAllOrders();
        //保存到request域中
        req.setAttribute("orders", orders);
        //请求转发,第一个/表示工程路径
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

}
