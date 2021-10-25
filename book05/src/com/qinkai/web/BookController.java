package com.qinkai.web;

import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;
import com.qinkai.service.BookService;
import com.qinkai.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/manager")
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 查询当前页图书
     * @param pageNo 要查询的页码
     * @param pageSize
     * @return
     */

    @RequestMapping("/page")
    public ModelAndView page(@RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "4")int pageSize) {
        //调用Service方法进行Dao操作获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置url
        page.setUrl("manager/page?pageSize=4");
        //回传隐含模型
        ModelAndView m =new ModelAndView("manager/book_manager");
        m.addObject("page",page);
        return m;
    }

    /**
     * 添加图书，并返回最后一页
     * @param pageNo 总页数
     * @param book
     * @return
     */
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public String add(int pageNo, @Valid Book book, BindingResult result, Model model) {
        //是否有校验错误
        boolean hasErrors = result.hasErrors();
        Map<String,Object> errorsMap =new HashMap<>();
        if(hasErrors){
            //获取到所有校验字段的错误
            List<FieldError> errors =result.getFieldErrors();
            for(FieldError fieldError :errors){
                //key是错误的字段，value是错误的信息
                errorsMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            model.addAttribute("errorInfo",errorsMap);
            //转发到错误界面
            return "error/error400";
        }else{
            //获取添加后的跳转页,+1是防止新增页，若没有新增页pageNo>pageTotal跳转到尾页
            int newPageNo = pageNo+1;
            //调用Service方法进行Dao操作
            bookService.addBook(book);
            //重定向  跳转到page界面
            return "redirect:/manager/page?pageNo="+newPageNo;
        }
    }

    /**
     * 更新图书
     * @param pageNo 当前更新项所在页
     * @param book
     * @return
     */
    @RequestMapping(value = "/book",method = RequestMethod.PUT)
    public String update(int pageNo, @Valid Book book, BindingResult result, Model model) {
        //是否有校验错误
        boolean hasErrors = result.hasErrors();
        Map<String,Object> errorsMap =new HashMap<>();
        if(hasErrors){
            //获取到所有校验字段的错误
            List<FieldError> errors =result.getFieldErrors();
            for(FieldError fieldError :errors){
                //key是错误的字段，value是错误的信息
                errorsMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            model.addAttribute("errorInfo",errorsMap);
            //转发到错误界面
            return "error/error400";
        }else{
            //调用Service方法进行Dao操作
            bookService.updateBook(book);
            return "redirect:/manager/page?pageNo=" + pageNo;
        }
    }

    /**
     * 删除图书
     * @param id
     * @param pageNo
     * @return
     */
    @RequestMapping("/delete")
    public String delete(int id,int pageNo) {
        //调用Service方法进行Dao操作
        bookService.delBookById(id);
        return "redirect:/manager/page?pageNo="+pageNo;

    }

    /**
     * 获取当前id的图书信息
     * @param id
     * @return
     */
    @RequestMapping("/getBook")
    public ModelAndView getBook(int id) {
        //调用Service方法进行Dao操作
        Book book = bookService.queryBookById(id);

        ModelAndView m =new ModelAndView("manager/book_edit");
        m.addObject("book",book);
        return m;
    }

}
