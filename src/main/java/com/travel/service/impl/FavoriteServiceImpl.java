package com.travel.service.impl;

import com.travel.dao.impl.FavoriteDaoImpl;
import com.travel.pojo.Favorite;
import com.travel.pojo.ResultInfo;
import com.travel.pojo.Route;
import com.travel.service.FavoriteService;

import java.util.List;

/**
 * @ClassName FavoriteServiceImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-31 09:11
 **/
public class FavoriteServiceImpl implements FavoriteService {
    @Override
    public ResultInfo queryFavorite(int uid, int rid) {
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        Favorite favorite = favoriteDao.queryFavorite(uid, rid);
        ResultInfo resultInfo = new ResultInfo();
        if (favorite != null) {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("200");
            resultInfo.setData(favorite);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("500");
        }
        return resultInfo;
    }

    public ResultInfo addFavorite(int uid, int rid) {
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        int len = favoriteDao.addFavorite(uid, rid);
        ResultInfo resultInfo = new ResultInfo();
        if (len > 0) {
            resultInfo.setErrorMsg("200");
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("500");
        }
        return resultInfo;
    }

    public ResultInfo cancelFavorite(int uid, int rid) {
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        int len = favoriteDao.cancelFavorite(uid, rid);
        ResultInfo resultInfo = new ResultInfo();
        if (len > 0) {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("200");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("500");
        }
        return resultInfo;
    }

    public int getPage(int uid) {
        int rCount = new FavoriteDaoImpl().getRidCount(uid);
        int page = 0;
        if (rCount % 12 > 0) {
            page = rCount / 12;
            page += 1;
        } else {
            page = rCount / 12;
        }
        return page;
    }

    public List<Route> getRouteData(int uid, int num) {
        return new FavoriteDaoImpl().getRoute(uid, (num - 1) * 12);
    }

    @Override
    public int getPageNum() {
        // TODO Auto-generated method stub
        FavoriteDaoImpl f = new FavoriteDaoImpl();
        int pageSize = 8;
        int count = f.getRouteCount();
        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }

    @Override
    public List<Route> getRouteList(int numPage) {
        FavoriteDaoImpl f = new FavoriteDaoImpl();
        int start = (numPage - 1) * 8;
        return f.getRouteList(start);
    }
}
