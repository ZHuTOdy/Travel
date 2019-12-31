package com.travel.service;

import com.travel.pojo.Route;

/**
 * @author todyzhu
 */
public interface RouteService {
    /**
     * 根据查询信息返回结果
     *
     * @param route 给入的route对象
     * @return 返回查询到的route对象
     */
    public Route query(Route route);

    /**
     * 根据给入的route对象添加seller成员
     *
     * @param route 给入的route对象
     * @return 返回添加完seller的route对象
     */
    public Route addSellerIntoRoute(Route route);

    /**
     * 根据给入的route对象添加Category成员
     *
     * @param route 给入的route对象
     * @return 返回添加完seller的route对象
     */
    public Route addCategoryIntoRoute(Route route);

    /**
     * 根据传入的route对象添加RouteImg成员
     *
     * @param route 给入的route对象
     * @return 返回添加完RouteImg的route对象
     */
    public Route addRouteImgIntoRoute(Route route);
}
