package com.qinkai.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;

    static {
        try {

            Properties properties =new Properties();
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
     * @return 返回null说明连接失败，不为null说明连接成功
     */
    public static Connection getConnection(){
        Connection con =null;
        try {
            con = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭链接，放回数据库连接池中
     * @param con
     */
    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
