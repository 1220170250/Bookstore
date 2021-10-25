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
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = WebUtils.getBeanFromIoc(BookService.class);

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取添加后的跳转页,+1是防止新增页，若没有新增页pageNo>pageTotal跳转到尾页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0)+1;
        //获取请求参数封装为javaBean
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用Service方法进行Dao操作
        bookService.addBook(book);
        //跳转到page界面
        /**
         * 浏览器会记录下最后一次请求的全部信息，当用户按下F5后，浏览器自动发起最后一次请求
         * 故这里用重定向(添加是一次请求，转发页面是一次请求，F5之后只会转发页面)
         * 这里重定向的/前只是到端口号，手动添加工程名
         */
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用Service方法进行Dao操作
        bookService.delBookById(id);
        //重定向到page界面,删除时通过传递pageNo参数，使重定向后仍然在当前页，如果当前页不存在，pageNo>pageTotal跳转到尾页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    /**
     * 更新图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数封装为javaBean
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用Service方法进行Dao操作
        bookService.updateBook(book);
        //重定向到page界面，获取getBook action请求转发过来的pageNo参数
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    /**
     * 获取选中图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用Service方法进行Dao操作
        Book book = bookService.queryBookById(id);

        //保存到request域中
        req.setAttribute("book", book);
        //请求转发到图书信息编辑页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 列出全部图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询图书数据
        List<Book> books = bookService.queryBooks();
        //保存到request域中
        req.setAttribute("books", books);
        //请求转发,第一个/表示工程路径
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 以分页形式展示图书信息
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
        //设置url
        page.setUrl("manager/bookServlet?action=page");
        //保存到request域中
        req.setAttribute("page", page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
