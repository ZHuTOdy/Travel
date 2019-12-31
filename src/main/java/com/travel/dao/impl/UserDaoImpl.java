package com.travel.dao.impl;

import com.travel.dao.UserDao;
import com.travel.pojo.PageRoute;
import com.travel.pojo.Route;
import com.travel.pojo.User;
import com.travel.pojo.UserModel;
import com.travel.utils.JdbcUtils;
import com.travel.utils.UuidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 11:24
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public User findUserName(User user) {
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String username = user.getUsername();
        String sql = "select * from tab_user where username = ?";
        User u = null;
        try{
            u = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        }catch (Exception e){
        }
        return u;
    }

    public void insertUser(User u ){
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "insert into tab_user (uid, username, password, name, birthday, sex, telephone, email, status, code) values(?,?,?,?,?,?,?,?,?,?)";
        String user = u.getUsername();
        String pwd = u.getPassword();
        String nam = u.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try{
            if (u.getBirthday() != null){
                birthday = sdf.parse(u.getBirthday());
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        String sex = u.getSex();
        String phone = u.getTelephone();
        String email = u.getEmail();
        String status = "N";
        String code = UuidUtil.getUuid();
        jt.update(sql, null, user, pwd, nam, birthday, sex, phone, email, status, code);
    }

    public User queryUserNameByUid(User user){
        JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from tab_user where uid = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUid());
    }

    public Object Login(String userName, String passWord) {
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(JdbcUtils.getDataSource());
        Map<String, Object> map = jt.queryForMap("SELECT *,COUNT(*) COUNT FROM tab_user WHERE username='" + userName + "' ");
        if (passWord.equals(map.get("password")) && (long) map.get("count") > 0) {
            return map;
        }
        else {
            return "0";
        }
    }

    public String register(UserModel model) {
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(JdbcUtils.getDataSource());
        String sql = "select COUNT(*) FROM tab_user where USERNAME='" + model.getUserName() + "'";
        int count = jt.queryForObject(sql, Integer.class);
        if (count > 0) {
            return "该用户已存在";
        }
        try {
            sql = "insert into tab_user (username, PASSWORD, NAME, birthday, sex, telephone, email, code) " + "values('" + model.getUserName() + "', '" + model.getPassword() + "', '" + model.getName() + "', '" + model.getBirthday() + "', '" + model.getSex() + "','" + model.getTelephone() + "', '" + model.getEmail() + "','" + model.getCode() + "')";
            count = jt.update(sql);
            return "1";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String activateAccount(String code) {
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(JdbcUtils.getDataSource());
        try {
            String sql = "select status FROM tab_user where code='" + code + "'";
            String status = jt.queryForObject(sql, String.class);
            if (status.equals("Y")) {
                return "It is your account that has been activated.Thus you don't do it again";
            }
            sql = "UPDATE tab_user set status ='Y' WHERE code='" + code + "'";
            int count = jt.update(sql);
            return count>0?"Congratulation!Your account has been activated successfully!":"Failed activation!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public PageRoute getRouteList(int cid, int curentPage) {
        List<Route> query = null;
        JdbcTemplate jt = null;
        PageRoute pg = new PageRoute();
        try {
            jt = new JdbcTemplate(JdbcUtils.getDataSource());
            int n = 8 * (curentPage-1);
            String sql = "select * from tab_route where cid = " + cid+ "  limit  " + n + ",8" ;
            query = jt.query(sql, new BeanPropertyRowMapper<>(Route.class));
            int routeCount = jt.queryForObject("select count(*) from tab_route where cid = ?", Integer.class, cid);
            int pageCount = (int) Math.ceil(routeCount / 8.0);
            pg.setRouteList(query);
            pg.setRouteCount(routeCount);
            pg.setPageCount(pageCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return pg;
    }

    public List<Route> findRoutename(String name) {
        List<Route> route = new ArrayList<>();
        try {

            JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getDataSource());
            route=jt.query("select * from tab_route where rname like '%"+name+"%' ",
                    new BeanPropertyRowMapper<>(Route.class));
            System.out.println(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }
}
