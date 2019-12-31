package com.travel.dao.impl;

import com.travel.pojo.Category;
import com.travel.pojo.Route;
import com.travel.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName IndexDaoImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-08-01 15:03
 **/
public class IndexDaoImpl {
    public List<Category> navigation() {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        return jt.query("select * from tab_category order by cid", new BeanPropertyRowMapper<>(Category.class));
    }

    public List<Route> route(String sql) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        return jt.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }
}
