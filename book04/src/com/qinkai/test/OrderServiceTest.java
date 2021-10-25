package com.qinkai.test;

import com.qinkai.pojo.Cart;
import com.qinkai.pojo.CartItem;
import com.qinkai.pojo.Order;
import com.qinkai.service.OrderService;
import com.qinkai.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService =new OrderServiceImpl();
    @Test
    public void creatOrder() {

        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"java 从入门到放弃",1,new BigDecimal(80.00),new BigDecimal(80.00)));

        orderService.creatOrder(cart,1);

    }
    @Test
    public void showAllOrders() {
        for (Order order:orderService.showAllOrders()) {
            System.out.println(order);
        }
    }
    @Test
    public void showMyOrders() {
        for (Order order:orderService.showMyOrders(1)) {
            System.out.println(order);
        }
    }
}