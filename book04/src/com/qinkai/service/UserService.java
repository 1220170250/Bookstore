package com.qinkai.service;

import com.qinkai.pojo.User;

public interface UserService {

    /**
     * 注册账号
      * @param user 注册用户信息
     */
    public void registerUser(User user);

    /**
     * 登录用户账号
     * @param user
     * @return
     */
    public User login(User user);
    /**
     * 验证用户名是否存在
     * @param username 用户名
     * @return false表示不存在 true表示存在
     */
    public boolean existsUsername(String username);
}
