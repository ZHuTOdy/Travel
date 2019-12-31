package com.travel.web;

import com.travel.service.impl.IndexServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 毛佩匀
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    private IndexServiceImpl service = new IndexServiceImpl();

    @ResponseBody
    @RequestMapping("/navigation")
    public Object navigation() {
        return service.navigation();
    }
    @ResponseBody
    @RequestMapping("/popularRoute")
    public Object popularRoute() {
        String sql="SELECT * FROM tab_route ORDER BY COUNT DESC LIMIT 4";
        return service.route(sql);
    }
    @ResponseBody
    @RequestMapping("/newRoute")
    public Object newRoute() {
        String sql="SELECT * FROM tab_route ORDER BY rdate DESC LIMIT 4";
        return service.route(sql);
    }
    @ResponseBody
    @RequestMapping("/randRoute")
    public Object randRoute() {
        String sql="SELECT * FROM tab_route ORDER BY RAND() LIMIT 4";
        return service.route(sql);
    }
    @ResponseBody
    @RequestMapping("/inlandRoute")
    public Object inlandRoute() {
        String sql="SELECT	* FROM tab_route r,tab_category c WHERE r.cid=c.cid AND r.cid=5 ORDER BY RAND() LIMIT 6";
        return service.route(sql);
    }
    @ResponseBody
    @RequestMapping("/abordRoute")
    public Object abordRoute() {
        String sql="SELECT	* FROM tab_route r,tab_category c WHERE r.cid=c.cid AND r.cid=4 ORDER BY RAND() LIMIT 6";
        return service.route(sql);
    }
}
