package com.travel.web;

import com.travel.pojo.ResultInfo;
import com.travel.pojo.Route;
import com.travel.service.FavoriteService;
import com.travel.service.impl.FavoriteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName FavoriteController
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-31 14:08
 **/
@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @RequestMapping("/favoritePage")
    @ResponseBody
    public String favorite(HttpServletRequest rq) {
        FavoriteServiceImpl fs = new FavoriteServiceImpl();
        HttpSession s = rq.getSession();
        int uid = (int) s.getAttribute("uid");
        return fs.getPage(uid) + "";
    }

    @RequestMapping("/favoritePageData")
    @ResponseBody
    public List<Route> favorite1(HttpServletRequest rq, String num) {
        FavoriteServiceImpl fs = new FavoriteServiceImpl();
        int uid = (Integer) rq.getSession().getAttribute("uid");
        return fs.getRouteData(uid, Integer.parseInt(num));
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public String getCountList(HttpServletRequest req) {
        FavoriteServiceImpl fs = new FavoriteServiceImpl();
        int pageNum = fs.getPageNum();
        return pageNum + "";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public List<Route> getList(String num) {
        FavoriteServiceImpl fs = new FavoriteServiceImpl();
        return fs.getRouteList(Integer.parseInt(num));
    }

    @RequestMapping("/favorite")
    @ResponseBody
    public ResultInfo favorite(HttpServletRequest request, String rid) {
        int uid = (Integer) request.getSession().getAttribute("uid");
        int r = Integer.parseInt(rid);
        System.out.println("favorite + " + rid);
        FavoriteServiceImpl favoriteService = new FavoriteServiceImpl();
        return favoriteService.queryFavorite(uid, r);
    }

    @RequestMapping("/addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(HttpServletRequest request, String rid) {
        int uid = (Integer) request.getSession().getAttribute("uid");
        int r = Integer.parseInt(rid);
        FavoriteServiceImpl favoriteService = new FavoriteServiceImpl();
        return favoriteService.addFavorite(uid, r);
    }


    @RequestMapping("/cancelFavorite")
    @ResponseBody
    public ResultInfo cancelFavorite(HttpServletRequest request, String rid) {
        int uid = (Integer) request.getSession().getAttribute("uid");
        int r = Integer.parseInt(rid);
        FavoriteServiceImpl favoriteService = new FavoriteServiceImpl();
        return favoriteService.cancelFavorite(uid, r);
    }
}
