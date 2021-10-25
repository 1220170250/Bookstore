package com.qinkai.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * 购物车具备功能：
 * 1.加入购物车
 * 2.删除商品项
 * 3.清空购物车
 * 2.修改商品项数量
 * 实现购物车技术：
 * Session（采用）
 * 数据库
 * redis+数据库+cookie
 */
public class Cart {

    //购物车商品集合,Integer 商品id CartItem 商品项
    //LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 向购物车添加商品项，分两种情况：
     * 1.购物车已存在该商品
     * 2.购物车不存在该商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //当前购物车不存在要添加的商品项,在末尾添加
            items.put(cartItem.getId(), cartItem);
        } else {
            //当前购物车已存在要添加的商品项，只需修改对应的商品项,数量和总价
            item.setCount(item.getCount() + cartItem.getCount());
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * 删除购物车中id对应的商品
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);

    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();

    }

    /**
     * 修改购物车某商品项
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        if (item == null) {
        } else {
            //在当前购物车找到要修改的商品项，修改对应的商品项,数量和总价
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }


    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 遍历所有商品项得到购物车商品总数
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount = totalCount + entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * 遍历所有商品项得到购物车商品总金额
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
