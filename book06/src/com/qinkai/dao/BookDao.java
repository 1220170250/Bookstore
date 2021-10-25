package com.qinkai.dao;

import com.qinkai.pojo.Book;

import java.util.List;

public interface BookDao {

    /**
     * 添加图书
     * @param book
     * @return 返回变动的行数
     */
    public int addBook (Book book);

    /**
     * 删除图书
     * @param id
     * @return 返回变动的行数
     */
    public int delBookById(int id);

    /**
     * 更新图书信息
     * @param book
     * @return 返回变动的行数
     */
    public int updateBook(Book book);

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
     * 查询价格区间内所有图书
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public List<Book> queryForBooksByPrice(int minPrice, int maxPrice);
}
