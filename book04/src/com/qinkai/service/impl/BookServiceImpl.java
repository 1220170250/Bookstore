package com.qinkai.service.impl;

import com.qinkai.dao.BookDao;
import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;
import com.qinkai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void delBookById(int id) {
        bookDao.delBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        //求总记录数并设置
        Integer pageTotalCount = bookDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码并设置
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求求当前页数据并设置
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        //求总记录数并设置
        Integer pageTotalCount = bookDao.queryForTotalCount(minPrice,maxPrice);
        page.setPageTotalCount(pageTotalCount);
        //求总页码并设置
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求求当前页数据并设置
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize,minPrice,maxPrice);
        page.setItems(items);

        return page;
    }
}
