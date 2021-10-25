package com.qinkai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public List<Book> queryForBooksByPrice(int minPrice, int maxPrice) {
        return bookDao.queryForBooksByPrice(minPrice,maxPrice);
    }

}
