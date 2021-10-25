package com.qinkai.web;

import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;
import com.qinkai.service.BookService;
import com.qinkai.service.impl.BookServiceImpl;
import com.qinkai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 以分页形式展示全部图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用Service方法进行Dao操作获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置页码点击跳转地址
        page.setUrl("client/bookServlet?action=page");
        //保存到request域中
        req.setAttribute("page", page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }


    /**
     * 以分页形式展示价格区间内图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int minPrice = WebUtils.parseInt(req.getParameter("minPrice"), 0);
        int maxPrice = WebUtils.parseInt(req.getParameter("maxPrice"), Integer.MAX_VALUE);

        //调用Service方法进行Dao操作获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,minPrice,maxPrice);
        //设置页码点击跳转地址
        StringBuilder sb =new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果请求参数中有价格区间参数，则需动态修改页码跳转地址,使其带上价格区间参数,否则价格区间默认为0-Integer.MAX_VALUE,会跳转到默认值搜索后对应的页码
        if(req.getParameter("minPrice")!= null){
            sb.append("&minPrice=").append(req.getParameter("minPrice"));
        }
        if(req.getParameter("maxPrice")!= null){
            sb.append("&maxPrice=").append(req.getParameter("maxPrice"));
        }
        page.setUrl(sb.toString());
        //保存到request域中
        req.setAttribute("page", page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
