package com.qinkai.web;

import com.google.gson.Gson;
import com.qinkai.pojo.Book;
import com.qinkai.pojo.Cart;
import com.qinkai.pojo.CartItem;
import com.qinkai.service.BookService;
import com.qinkai.service.impl.BookServiceImpl;
import com.qinkai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    BookService bookService = WebUtils.getBeanFromIoc(BookService.class);

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数中商品id，并根据id得到对应商品信息,并封装成一个CartItem
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取Session域中保存的Cart,若没有则需新建一个
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //把商品项添加到购物车
        cart.addItem(cartItem);
        //保存最后一次添加到购物车的商品名
        req.getSession().setAttribute("lastAddName", cartItem.getName());

        /**
         * HTTP协议中有一个请求头Referer,它用户在发起请求时，把浏览器地址栏中地址发送给服务器
         */
        //重定向回点击添加购物车时的页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数中商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车并删除
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
            //重定向回之前页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车并清空
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            //重定向回之前页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数中商品id count
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        //获取购物车并修改
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            //重定向回之前页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * Ajax请求添加商品到购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数中商品id，并根据id得到对应商品信息,并封装成一个CartItem
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //获取Session域中保存的Cart,若没有则需新建一个
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);

        }
            //把商品项添加到购物车
            cart.addItem(cartItem);
            //保存最后一次添加到购物车的商品名
            req.getSession().setAttribute("lastAddName", cartItem.getName());
            //将结果封装成Map对象
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("totalCount", cart.getTotalCount());
            resultMap.put("lastAddName", cartItem.getName());

            //将Map对象转换为json格式
            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            //通过输出流返回json字符串
            resp.getWriter().write(json);

    }
}
