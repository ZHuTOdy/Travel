package com.travel.service.impl;

import com.travel.dao.impl.IndexDaoImpl;
import com.travel.pojo.Category;
import com.travel.pojo.Route;

import java.util.List;

/**
 * @ClassName IndexServiceImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-08-01 15:07
 **/
public class IndexServiceImpl {
    private IndexDaoImpl dao = new IndexDaoImpl();

    public List<Category> navigation() {
        return dao.navigation();
    }
    public List<Route> route(String sql) {
        return dao.route(sql);
    }
}
