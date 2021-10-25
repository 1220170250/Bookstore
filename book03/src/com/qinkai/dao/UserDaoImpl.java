package com.qinkai.dao;

import com.qinkai.pojo.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User queryByUserName(String username) {
        String sql = "SELECT `id`,`username`,`password`,`email`FROM t_user WHERE username= ?";

        return queryForOne(sql,username);
    }

    @Override
    public User queryByUserNameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email`FROM t_user WHERE username= ? and password= ?";
        return queryForOne(sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user (`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

}
