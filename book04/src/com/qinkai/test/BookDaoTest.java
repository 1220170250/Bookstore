package com.qinkai.test;

import com.qinkai.dao.BookDao;
import com.qinkai.dao.BookDaoImpl;
import com.qinkai.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {
    /**
     * 自动生成Test类：在要测试类对应接口中Ctrl+Shift+T
     */
    private BookDao bookDao =new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"天龙八部","金庸",new BigDecimal(23323),0,100,null));

    }

    @Test
    public void delBookById() {
        bookDao.delBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"天龙八部","金庸",new BigDecimal(23.40),0,100,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookDao.queryBooks()){
            System.out.println(book);
        }
    }
    @Test
    public void queryForTotalCount() {
        System.out.println(bookDao.queryForTotalCount());
    }
    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(5,4)){
            System.out.println(book);
        }
    }
    @Test
    public void queryForTotalCountByPrice() {
       System.out.println(bookDao.queryForTotalCount(0,50));
    }
    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItems(5,4,0,50)){
            System.out.println(book);
        }
    }

}