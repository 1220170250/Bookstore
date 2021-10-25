package com.qinkai.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class Book {
    private Integer id;
    @NotEmpty
    @Length(min = 1, max = 18)
    private String name;
    @NotEmpty
    @Length(min = 1, max = 12)
    private String author;
    @Digits(integer = 6, fraction = 2, message = "请输入正确的数值")
    @Min(value = 0, message = "请输入正确的数值")
    private BigDecimal price;
    @Min(value = 0, message = "请输入正确的数值")
    private Integer sales;
    @Min(value = 0, message = "请输入正确的数值")
    private Integer stock;
    private String imagePath = "static/img/default.jpg";

    public Book() {
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imagePath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //图书封面不能为空
        if (imagePath != null && !"".equals(imagePath)) {
            this.imagePath = imagePath;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        //图书封面不能为空
        if (imagePath != null && !"".equals(imagePath)) {
            this.imagePath = imagePath;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
