package com.qinkai.dao;

import com.qinkai.pojo.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 保存订单到数据库
     * @param order
     */
    public int saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> queryOrders();


    /**
     * 根据用户id查询用户所有订单
     * @param userId
     * @return
     */

    public List<Order> queryOrdersByUserId(Integer userId);






}
