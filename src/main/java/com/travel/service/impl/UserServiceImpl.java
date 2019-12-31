package com.travel.service.impl;

import com.travel.dao.impl.UserDaoImpl;
import com.travel.pojo.*;
import com.travel.service.UserService;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 11:34
 **/
public class UserServiceImpl implements UserService {
    UserDaoImpl dao = new UserDaoImpl();

    @Override
    public ResultInfo register(User u) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findUserName(u);
        ResultInfo resultInfo = new ResultInfo();
        if (user == null){
            userDao.insertUser(u);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("200");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("500");
        }
        return resultInfo;
    }

    public User showName(User u){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserNameByUid(u);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setData(user.getName());
        return user;
    }

    public Object Login(String userName, String passWord) {
        return dao.Login(userName, passWord);
    }

    public String register(UserModel model) {
        return dao.register(model);
    }
    public String activateAccount(String code){
        return dao.activateAccount(code);
    }

    public PageRoute returnList(int cid, int currentPage){
        return dao.getRouteList(cid,currentPage);
    }
    public List<Route> resultRouteName(String name){
        return dao.findRoutename(name);
    }
}
