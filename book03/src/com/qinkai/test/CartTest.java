package com.qinkai.test;

import com.qinkai.pojo.Cart;
import com.qinkai.pojo.CartItem;
import javafx.scene.chart.Chart;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"水浒传",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"水浒传",2,new BigDecimal(10),new BigDecimal(20)));
        cart.addItem(new CartItem(2,"西游记",1,new BigDecimal(20),new BigDecimal(20)));
        System.out.println(cart);

    }

    @Test
    public void deleteItem() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"水浒传",1,new BigDecimal(10.33),new BigDecimal(10.33)));
        cart.addItem(new CartItem(1,"水浒传",2,new BigDecimal(10.33),new BigDecimal(20.66)));
        cart.addItem(new CartItem(2,"西游记",1,new BigDecimal(20.55),new BigDecimal(20.55)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"水浒传",1,new BigDecimal(10.33),new BigDecimal(10.33)));
        cart.addItem(new CartItem(1,"水浒传",2,new BigDecimal(10.33),new BigDecimal(20.66)));
        cart.addItem(new CartItem(2,"西游记",1,new BigDecimal(20.55),new BigDecimal(20.55)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"水浒传",1,new BigDecimal(10.33),new BigDecimal(10.33)));
        cart.addItem(new CartItem(1,"水浒传",2,new BigDecimal(10.33),new BigDecimal(20.66)));
        cart.addItem(new CartItem(2,"西游记",1,new BigDecimal(20.55),new BigDecimal(20.55)));
        cart.updateCount(2,2);
        System.out.println(cart);
    }
}