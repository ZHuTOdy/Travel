package com.travel.service.impl;

import com.travel.dao.impl.RouteDaoImpl;
import com.travel.pojo.*;
import com.travel.service.RouteService;

import java.util.List;

/**
 * @ClassName RouteServiceImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 15:07
 **/
public class RouteServiceImpl implements RouteService {
    @Override
    public Route query(Route r) {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        return routeDao.queryByRid(r);
    }

    @Override
    public Route addSellerIntoRoute(Route route) {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        Seller seller = routeDao.querySellerByRoute(route);
        route.setSeller(seller);
        return route;
    }

    @Override
    public Route addCategoryIntoRoute(Route route) {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        Category category = routeDao.queryCategoryByRoute(route);
        route.setCategory(category);
        return route;
    }

    @Override
    public Route addRouteImgIntoRoute(Route route) {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        List<RouteImg> list = routeDao.queryImgByRoute(route);
        route.setRouteImgList(list);
        return route;
    }
}
