package com.qinkai.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;
import com.qinkai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientBookController {
    @Autowired
    private BookService bookService;

    /**
     * 查询当前页图书
     *
     * @param pageNo   要查询的页码
     * @param pageSize
     * @return
     */
    @RequestMapping("/page")
    public ModelAndView page(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize) {

        //调用Service方法进行Dao操作获取page对象
        PageHelper.startPage(pageNo,pageSize);
        List<Book> list = bookService.queryBooks();
        //使用PageInfo保存得到的页面数据，包括当前页码PageNum总页码Pages总记录数当前页记录数Size当前页的尺寸PageSize前一页PrePage数据List
        PageInfo<Book> info = new PageInfo<>(list);

        //回传隐含模型
        ModelAndView m = new ModelAndView("client/index");
        m.addObject("info", info);
        //回传页码要去服务器的url
        m.addObject("url","client/page?pageSize=4");
        return m;
    }

    /**
     * 按价格查询当前页图书
     * @param pageNo
     * @param pageSize
     * @param minPrice
     * @param maxPrice
     * @param req
     * @return
     */
    @RequestMapping("/pageByPrice")
    public ModelAndView pageByPrice(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
                                    @RequestParam(value = "minPrice", required = false, defaultValue = "0") int minPrice,
                                    @RequestParam(value = "maxPrice", required = false, defaultValue = Integer.MAX_VALUE + "") int maxPrice,
                                    HttpServletRequest req) {
        //调用Service方法进行Dao操作获取page对象
        PageHelper.startPage(pageNo,pageSize);
        List<Book> list = bookService.queryForBooksByPrice(minPrice,maxPrice);
        //使用PageInfo保存得到的页面数据，包括当前页码pageNum总页码pages总记录数当前页记录数size当前页的尺寸pageSize前一页prePage数据list
        PageInfo<Book> info = new PageInfo<>(list);

        //设置页码点击跳转地址
        StringBuilder sb = new StringBuilder("client/pageByPrice?pageSize=4");
        //如果请求参数中有价格区间参数，则需动态修改页码跳转地址,使其带上价格区间参数,否则价格区间默认为0-Integer.MAX_VALUE,会跳转到默认值搜索后对应的页码
        if (req.getParameter("minPrice") != null) {
            sb.append("&minPrice=").append(req.getParameter("minPrice"));
        }
        if (req.getParameter("maxPrice") != null) {
            sb.append("&maxPrice=").append(req.getParameter("maxPrice"));
        }

        //回传隐含模型
        ModelAndView m = new ModelAndView("client/index");
        m.addObject("info", info);
        //回传页码要去服务器的url
        m.addObject("url",sb.toString());
        return m;
    }


}
