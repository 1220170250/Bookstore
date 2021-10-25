package com.qinkai.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDao<T> {
    private Class<T> type = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.update(sql, args);
    }

    /**
     * 查询返回一个JavaBean
     *
     * @param sql
     * @param args
     * @return
     */
    public T queryForOne(String sql, Object... args) {
        T object = null;
        try {
            object = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(type), args);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 查询返回多个JavaBean
     *
     * @param sql
     * @param args
     * @return
     */
    public List<T> queryForList(String sql, Object... args) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(type), args);
    }

    /**
     * 查询返回某行某列单个值
     *
     * @param sql  查询sql语句
     * @param args 可变形参表示操作的字段值
     * @return 统计值
     */
    public Object queryForSingleValue(String sql, Object... args) {

        return jdbcTemplate.queryForObject(sql,Object.class, args);

    }

}
