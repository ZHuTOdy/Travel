package com.travel.dao;

import com.travel.pojo.Category;
import com.travel.pojo.Route;
import com.travel.pojo.RouteImg;
import com.travel.pojo.Seller;

import java.util.List;

/**
 * @author todyzhu
 */
public interface RouteDao {
    /**
     * 根据传入的route返回查询信息
     *
     * @param route 传入的route对象
     * @return 查询结果
     */
    public Route queryByRid(Route route);

    /**
     * 根据传入的Route对象查询卖家信息
     *
     * @param route 传入的route对象
     * @return 查询到的卖家信息
     */
    public Seller querySellerByRoute(Route route);

    /**
     * 根据传入的route对象查询所属分类
     *
     * @param route 传入的route对象
     * @return 查询到的所属分类
     */
    public Category queryCategoryByRoute(Route route);

    /**
     * 根据传入的route对象查询对应的Img
     *
     * @param route 想要查询的route对象
     * @return 查询到的routeImg
     */
    public List<RouteImg> queryImgByRoute(Route route);
}
