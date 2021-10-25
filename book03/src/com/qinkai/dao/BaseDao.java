package com.qinkai.dao;

import com.qinkai.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {
    private Class<T> type = null;

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 获取泛型参数
     */
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();//获取父类泛型参数
        type = (Class<T>) typeArguments[0];
    }

    /**
     * 用于增、删、改操作
     *
     * @param sql  sql语句
     * @param args 可变形参表示操作的字段值
     * @return -1表示失败，其它表示操作的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            //捕获异常，但一定要抛出，不然事务是无法知道jdbc操作出现了异常就无法回滚
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询返回一个JavaBean
     * @param sql
     * @param args
     * @return
     */
    public  T queryForOne(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询返回多个JavaBean
     * @param sql
     * @param args
     * @return
     */
    public  List<T> queryForList( String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询返回某行某列单个值
     *
     * @param sql  查询sql语句
     * @param args 可变形参表示操作的字段值
     * @return 统计值
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
