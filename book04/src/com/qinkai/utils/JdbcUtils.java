package com.qinkai.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    //使用ThreadLocal实现不同线程间数据库连接的隔离，保证一个事务中用的是一个数据库连接
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {

            Properties properties = new Properties();
            //读取properties文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的链接
     *
     * @return 返回null说明连接失败，不为null说明连接成功
     */
    public static Connection getConnection() {
        //获取当前线程的ThreadLocal变量
        Connection conn = conns.get();
        //如果是事务的第一步操作，之前还没有获取连接，先要获取连接并保存
        if (conn == null) {
            try {
                //从数据库连接池中获取数据库连接
                conn = dataSource.getConnection();
                //把第一次获取的数据库连接保存到ThreadLocal变量，供后面的jdbc操作使用
                conns.set(conn);
                conn.setAutoCommit(false);//设置手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //Tomcat底层使用了线程池，所以这里要手动清除ThreadLocal变量
        conns.remove();
    }

    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.rollback();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //Tomcat底层使用了线程池，所以这里要手动清除ThreadLocal变量
        conns.remove();
    }

    /**
     * 关闭链接，放回数据库连接池中
     *
     * @param con
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
