package com.qinkai.dao;

import com.qinkai.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 保存订单项到数据库
     * @param orderItem
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单id查询所有订单项
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
