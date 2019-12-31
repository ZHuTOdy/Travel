package com.travel.dao;

import com.travel.pojo.User;

/**
 * @author todyzhu
 */
public interface UserDao {
    /**
     * 根据用户名查询用户已是否注册
     *
     * @param user 传入的用户对象
     * @return 返回查询的User对象所有信息
     */
    public User findUserName(User user);
}
