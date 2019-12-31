package com.travel.web;

import com.travel.pojo.PageRoute;
import com.travel.pojo.ResultInfo;
import com.travel.pojo.Route;
import com.travel.service.impl.RouteServiceImpl;
import com.travel.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName RouteController
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 15:36
 **/
@Controller
@RequestMapping("/route")
public class RouteController {

    @RequestMapping("/hello")
    @ResponseBody
    public ResultInfo hello(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        request.getSession().setAttribute("uid", 38);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("200");
        resultInfo.setData(request.getSession().getAttribute("uid"));
        return resultInfo;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Route query(String rid) {
        Route route = new Route();
        int r = Integer.parseInt(rid);
        route.setRid(r);
        RouteServiceImpl routeService = new RouteServiceImpl();
        route = routeService.query(route);
        route = routeService.addSellerIntoRoute(route);
        route = routeService.addCategoryIntoRoute(route);
        route = routeService.addRouteImgIntoRoute(route);
        return route;
    }

    @RequestMapping("/pageRoute")
    @ResponseBody
    public PageRoute pageRoute(int cid, int curentPage) {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.returnList(cid, curentPage);
    }
    @RequestMapping("/LikepageRoute")
    @ResponseBody
    public List<Route> LikepageRoute(String searchName) {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.resultRouteName(searchName);
    }
}
