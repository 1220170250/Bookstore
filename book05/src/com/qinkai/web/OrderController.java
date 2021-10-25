package com.qinkai.web;

import com.qinkai.pojo.Order;
import com.qinkai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/manager")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单
     * @return
     */
    @RequestMapping("/showAllOrders")
    public ModelAndView showAllOrders() {
        //查询订单数据
        List<Order> orders= orderService.showAllOrders();
        //保存到隐含模型
        ModelAndView m =new ModelAndView("manager/order_manager");
        m.addObject("orders",orders);
        return m;
    }
}
