package com.qinkai.web;


import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;
import com.qinkai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置url
        page.setUrl("client/page?pageSize=4");
        //回传隐含模型
        ModelAndView m = new ModelAndView("client/index");
        m.addObject("page", page);
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
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, minPrice, maxPrice);
        //设置页码点击跳转地址
        StringBuilder sb = new StringBuilder("client/pageByPrice?pageSize=4");
        //如果请求参数中有价格区间参数，则需动态修改页码跳转地址,使其带上价格区间参数,否则价格区间默认为0-Integer.MAX_VALUE,会跳转到默认值搜索后对应的页码
        if (req.getParameter("minPrice") != null) {
            sb.append("&minPrice=").append(req.getParameter("minPrice"));
        }
        if (req.getParameter("maxPrice") != null) {
            sb.append("&maxPrice=").append(req.getParameter("maxPrice"));
        }
        page.setUrl(sb.toString());
        //回传隐含模型
        ModelAndView m = new ModelAndView("client/index");
        m.addObject("page", page);
        return m;
    }


}
