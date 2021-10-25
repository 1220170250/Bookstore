package com.qinkai.test;


import com.qinkai.dao.OrderItemDao;
import com.qinkai.pojo.OrderItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class OrderItemDaoTest {


    ApplicationContext ac = new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
    OrderItemDao orderItemDao = (OrderItemDao) ac.getBean("orderItemDao");



    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "天龙八部", 1, new BigDecimal(20.50), new BigDecimal(20.50), "1234567890"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        for (OrderItem orderItem : orderItemDao.queryOrderItemsByOrderId("16112100111901")) {
            System.out.println(orderItem);
        }

    }
}