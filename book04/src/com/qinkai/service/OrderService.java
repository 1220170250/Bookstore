package com.qinkai.service;

import com.qinkai.pojo.Cart;
import com.qinkai.pojo.Order;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单，将订单信息和具体订单项信息保存，并修改销售和库存信息
     * @param cart 购物车
     * @param userId 用户id
     * @return 订单号
     */
    public String creatOrder(Cart cart,Integer userId);

    /**
     * 显示所有的订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 显示我的订单
     * @return
     */
    public List<Order> showMyOrders(Integer userId);
}
