package com.qinkai.dao;

import com.qinkai.pojo.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO `t_order_item`(`name`,`count`,`price`,`total_money`,`order_id`) VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql ="SELECT `name`,`count`,`price`,`total_money` totalPrice,`order_id` orderId FROM `t_order_item` WHERE `order_id`= ?";
        return queryForList(sql,orderId);
    }
}
