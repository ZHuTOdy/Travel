package com.travel.dao.impl;

import com.travel.dao.RouteDao;
import com.travel.pojo.*;
import com.travel.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName RouteDaoImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 15:11
 **/
public class RouteDaoImpl implements RouteDao {
    @Override
    public Route queryByRid(Route route) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        int rid = route.getRid();
        String sql = "select * from tab_route where rid = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }

    @Override
    public Seller querySellerByRoute(Route route) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from tab_seller where sid = ?";
        int sid = route.getSid();
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
    }

    @Override
    public Category queryCategoryByRoute(Route route) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        int cid = route.getCid();
        String sql = "select * from tab_category where cid = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
    }

    @Override
    public List<RouteImg> queryImgByRoute(Route route) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        int rid = route.getRid();
        String sql = "select * from tab_route_img where rid = ?";
        List<RouteImg> list =  jt.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return list;
    }
}
