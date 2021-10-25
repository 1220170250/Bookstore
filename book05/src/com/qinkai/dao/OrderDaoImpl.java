package com.qinkai.dao;

import com.qinkai.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {


    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `t_order`(`order_id`,`create_time`,`total_money`,`status`,`user_id`) VALUES(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql ="SELECT `order_id` orderId, `create_time` createTime,`total_money` price,`status`,`user_id` userId FROM `t_order`";
        return queryForList(sql);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql ="SELECT `order_id` orderId,`create_time` createTime,`total_money` price,`status`,`user_id` userId FROM `t_order` WHERE `user_id`= ?";
        return queryForList(sql,userId);
    }


}
