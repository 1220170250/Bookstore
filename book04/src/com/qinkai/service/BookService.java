package com.qinkai.service;

import com.qinkai.pojo.Book;
import com.qinkai.pojo.Page;

import java.util.List;

public interface BookService {

    /**
     * 添加图书
     * @param book
     */
    public void addBook (Book book);

    /**
     * 删除图书
     * @param id
     */
    public void delBookById(int id);

    /**
     * 更新图书信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 通过id查询图书
     * @param id
     * @return
     */
    public Book queryBookById(int id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询当前页图书
     * @param pageNo 当前页码
     * @param pageSize 当前页显示数量
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize);

    /**
     * 查询价格区间内的当前页图书
     * @param pageNo 当前页码
     * @param pageSize 当前页显示数量
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @return
     */
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice);
}
