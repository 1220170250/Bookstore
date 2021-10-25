package com.qinkai.dao;

import com.qinkai.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) VALUES(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImagePath());
    }

    @Override
    public int delBookById(int id) {
        String sql = "DELETE FROM `t_book` WHERE `id` = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE `t_book` SET `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? WHERE `id` =?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImagePath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` FROM `t_book` WHERE `id`=?";
        return queryForOne(sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` FROM `t_book`";
        return queryForList(sql);
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "SELECT COUNT(*) FROM `t_book`";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` FROM `t_book` LIMIT ?,?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCount(int minPrice, int maxPrice) {
        String sql = "SELECT COUNT(*) FROM `t_book` WHERE `price` BETWEEN ? AND ?";
        Number count = (Number) queryForSingleValue(sql, minPrice, maxPrice);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int minPrice, int maxPrice) {
        String sql = "SELECT `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
                "FROM `t_book` WHERE `price` BETWEEN ? AND ? ORDER BY `price` LIMIT ?,?";
        return queryForList(sql, minPrice, maxPrice, begin, pageSize);
    }
}
