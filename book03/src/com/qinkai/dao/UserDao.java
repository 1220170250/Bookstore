package com.qinkai.dao;

import com.qinkai.pojo.User;

public interface UserDao {


    /**
     * 通过用户名查询用户信息，用于验证用户名
     * @param username 用户名
     * @return null表示当前用户名不存在
     */
    public User queryByUserName(String username);

    /**
     * 通过用户名和密码验证用户信息,用于登录
     * @param username 用户名
     * @param password 密码
     * @return null表示用户名或密码不正确
     */
    public User queryByUserNameAndPassword(String username,String password);

    /**
     * 保存当前用户信息到数据库，用于注册
     * @param user 用户
     * @return -1表示保存失败，成功返回数据库新增保存行数
     */
    public int saveUser(User user);
}
