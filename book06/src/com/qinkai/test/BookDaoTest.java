package com.qinkai.test;

import com.qinkai.dao.BookDao;
import com.qinkai.pojo.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class BookDaoTest {
    /**
     * 自动生成Test类：在要测试类对应接口中Ctrl+Shift+T
     */
    ApplicationContext ac =new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
    BookDao bookDao = (BookDao) ac.getBean("bookDao");

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"天龙八部","金庸",new BigDecimal(23323),0,100,null));
    }

    @Test
    public void delBookById() {
        bookDao.delBookById(72);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(64,"天龙八部","金庸",new BigDecimal(23.40),0,100,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(64));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookDao.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void queryForBooksByPrice() {
        for (Book book : bookDao.queryForBooksByPrice(0,50)){
            System.out.println(book);
        }
    }

}