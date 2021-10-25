package com.qinkai.test;

import com.qinkai.dao.OrderItemDao;
import com.qinkai.dao.OrderItemDaoImpl;
import com.qinkai.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null,"天龙八部",1,new BigDecimal(20.50),new BigDecimal(20.50),"1234567890"));
    }
    @Test
    public void queryOrderItemsByOrderId() {
        for (OrderItem orderItem: orderItemDao.queryOrderItemsByOrderId("16097529339331")) {
            System.out.println(orderItem);
        }

    }
}