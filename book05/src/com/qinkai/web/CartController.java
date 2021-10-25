package com.qinkai.web;

import com.qinkai.pojo.Book;
import com.qinkai.pojo.Cart;
import com.qinkai.pojo.CartItem;
import com.qinkai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/cart")
@Controller
public class CartController {
    @Autowired
    BookService bookService;

    /**
     * 加入购物车
     *
     * @param id
     * @param session
     * @param req
     * @return
     */
    @RequestMapping("/addItem")
    public String addItem(int id, HttpSession session, HttpServletRequest req) {
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取Session域中保存的Cart,若没有则需新建一个
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //把商品项添加到购物车
        cart.addItem(cartItem);
        //保存最后一次添加到购物车的商品名
        session.setAttribute("lastAddName", cartItem.getName());

        /**
         * HTTP协议中有一个请求头Referer,它用户在发起请求时，把浏览器地址栏中地址发送给服务器
         */
        //重定向回点击添加购物车时的页面
        return "redirect:" + req.getHeader("Referer");
    }

    /**
     * Ajax请求添加商品到购物车
     *
     * @param id
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxAddItem")
    public Map<String, Object> ajaxAddItem(int id, HttpSession session) {
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取Session域中保存的Cart,若没有则需新建一个
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //把商品项添加到购物车
        cart.addItem(cartItem);
        //保存最后一次添加到购物车的商品名
        session.setAttribute("lastAddName", cartItem.getName());
        //将结果封装成Map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastAddName", cartItem.getName());
        return resultMap;
    }

    /**
     * 删除购物车商品项
     * @param id
     * @param req
     * @return
     */
    @RequestMapping("/deleteItem")
    public String deleteItem(int id, HttpServletRequest req) {
        //获取购物车并删除
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //重定向回之前页面
        return "redirect:" + req.getHeader("Referer");
    }

    /**
     * 清空购物车
     * @param req
     * @return
     */
    @RequestMapping("/clear")
    public String clear(HttpServletRequest req) {
        //获取购物车并清空
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        //重定向回之前页面
        return "redirect:" + req.getHeader("Referer");
    }

    /**
     * 修改购物车商品数量
     * @param id
     * @param count
     * @param req
     * @return
     */
    @RequestMapping("/updateCount")
    public String updateCount(int id,int count,HttpServletRequest req){
        //获取购物车并修改
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        //重定向回之前页面
        return "redirect:" + req.getHeader("Referer");
    }
}
