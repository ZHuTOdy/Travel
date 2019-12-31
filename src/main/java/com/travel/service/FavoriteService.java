package com.travel.service;

import com.travel.pojo.ResultInfo;
import com.travel.pojo.Route;

import java.util.List;

/**
 * @author todyzhu
 */
public interface FavoriteService {
    /**
     * 根据参数查询是否收藏线路
     *
     * @param uid 用户编号
     * @param rid 线路编号
     * @return 返回结果信息
     */
    public ResultInfo queryFavorite(int uid, int rid);

    public int getPageNum();

    public List<Route> getRouteList(int pageNum);
}
