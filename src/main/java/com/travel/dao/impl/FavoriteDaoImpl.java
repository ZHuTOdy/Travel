package com.travel.dao.impl;

import com.travel.dao.FavoriteDao;
import com.travel.pojo.Favorite;
import com.travel.pojo.Route;
import com.travel.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @ClassName FavoriteDaoImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-31 09:14
 **/
public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public Favorite queryFavorite(int uid, int rid) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from tab_favorite where uid = ? and rid = ?";
        Favorite favorite = null;
        try {
            favorite = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, rid);
        } catch (Exception e) {
        }
        return favorite;
    }

    @Override
    public int addFavorite(int uid, int rid) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql1 = "insert into tab_favorite (rid, date, uid) values(?,?,?)";
        String sql2 = "update tab_route set count = count + 1 where rid = ?";
        Date date = new Date();
        int len = jt.update(sql1, rid, date, uid);
        jt.update(sql2, rid);
        return len;
    }

    @Override
    public int cancelFavorite(int uid, int rid) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql1 = "delete from tab_favorite where uid = ? and rid = ?";
        String sql2 = "update tab_route set count = count - 1 where rid = ?";
        int len = jt.update(sql1, uid, rid);
        jt.update(sql2, rid);
        return len;
    }

    @Override
    public int getRidCount(int uid) {
        JdbcTemplate jd = new JdbcTemplate(JdbcUtils.getDataSource());
        return jd.queryForObject("select count(*) from tab_favorite where uid = ?", Integer.class, uid);
    }

    @Override
    public List<Route> getRoute(int uid, int start) {
        JdbcTemplate jd = new JdbcTemplate(JdbcUtils.getDataSource());
        return jd.query("SELECT * FROM tab_route  WHERE rid IN( SELECT rid FROM tab_favorite WHERE uid = ?) limit ?,?", new BeanPropertyRowMapper<>(Route.class), uid, start, 12);
    }

    @Override
    public List<Route> getRouteList(int start) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        return jt.query("select * from tab_route where count!=0 order by count desc limit ?,?", new BeanPropertyRowMapper(Route.class), start, 8);
    }

    @Override
    public int getRouteCount() {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        return jt.queryForObject("select count(*) from tab_route where count <> 0", Integer.class);
    }
}
