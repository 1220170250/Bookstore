package com.qinkai.test;

import com.qinkai.dao.OrderDao;
import com.qinkai.dao.OrderDaoImpl;
import com.qinkai.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(10.22), 0, 1));
    }

    @Test
    public void queryOrders() {
        for (Order order : orderDao.queryOrders()) {
            System.out.println(order);
        }
    }
    @Test
    public void queryOrderItemsByOrderId(){
        for (Order order : orderDao.queryOrdersByUserId(2)) {
            System.out.println(order);
        }
    }
}