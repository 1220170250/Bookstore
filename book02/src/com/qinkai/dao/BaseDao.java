package com.qinkai.dao;

import com.qinkai.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();


    /**
     * 用于增、删、改操作
     * @param sql sql语句
     * @param args 可变形参表示操作的字段值
     * @return -1表示失败，其它表示操作的行数
     */
    public int update(String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询返回一个JavaBean
     * @param type 返回对象类型
     * @param sql  查询sql语句
     * @param args 可变形参表示操作的字段值
     * @param <T>  返回JavaBean类型的泛型
     * @return  JavaBean
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
           return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询返回多个JavaBean
     * @param type 返回对象类型
     * @param sql  查询sql语句
     * @param args 可变形参表示操作的字段值
     * @param <T>  返回JavaBean类型的泛型
     * @return  JavaBean的List
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询返回某行某列单个值
     * @param sql 查询sql语句
     * @param args 可变形参表示操作的字段值
     * @return 统计值
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

}
