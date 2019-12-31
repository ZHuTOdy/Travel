package com.travel.dao;

import com.travel.pojo.Favorite;
import com.travel.pojo.Route;

import java.util.List;

/**
 * @author todyzhu
 */
public interface FavoriteDao {

    /**
     * 根据给入的参数查询是否存在收藏
     *
     * @param uid 用户编号
     * @param rid 路线编号
     * @return 返回的Favorite
     */
    public Favorite queryFavorite(int uid, int rid);

    /**
     * 添加收藏
     *
     * @param uid 传入的用户编号
     * @param rid 传入的路线编号
     * @return 返回的插入行数
     */
    public int addFavorite(int uid, int rid);

    /**
     * 获取用户收藏线路量
     *
     * @param uid 用户编号
     * @return 收藏线路数量
     */
    public int getRidCount(int uid);

    /**
     * 获取线路列表
     *
     * @param uid   用户编号
     * @param start 开始查询的编号
     * @return 线路集合
     */
    public List<Route> getRoute(int uid, int start);

    public List<Route> getRouteList(int start);

    public int getRouteCount();

    public int cancelFavorite(int uid, int rid);
}
