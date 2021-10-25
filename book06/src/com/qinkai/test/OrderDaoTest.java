package com.qinkai.test;

import com.qinkai.dao.OrderDao;
import com.qinkai.pojo.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {
    ApplicationContext ac =new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
    OrderDao orderDao = (OrderDao) ac.getBean("orderDao");

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
        for (Order order : orderDao.queryOrdersByUserId(1)) {
            System.out.println(order);
        }
    }
}