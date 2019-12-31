package com.travel.service;

import com.travel.pojo.ResultInfo;
import com.travel.pojo.User;

/**
 * @author todyzhu
 */
public interface UserService {
    /**
     * 根据给入的User返回查询信息
     *
     * @param u 给入的注册用户
     * @return 查询结果
     */
    public ResultInfo register(User u);
}
