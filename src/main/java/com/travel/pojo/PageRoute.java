package com.travel.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageRoute
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 15:01
 **/
public class PageRoute<T> implements Serializable {
    /**
     * 当前第几页
     */
    private Integer curentPage;
    /**
     * 共多少条数据
     */
    private Integer routeCount;
    /**
     * 共多少页
     */
    private Integer pageCount;
    /**
     * 每一行显示的对象
     */
    private List<T> routeList;

    //getter sertter fn + alt + insert

    public Integer getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(Integer curentPage) {
        this.curentPage = curentPage;
    }

    public Integer getRouteCount() {
        return routeCount;
    }

    public void setRouteCount(Integer routeCount) {
        this.routeCount = routeCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<T> routeList) {
        this.routeList = routeList;
    }

    @Override
    public String toString() {
        return "PageRoute{" +
                "curentPage='" + curentPage + '\'' +
                ", routeCount='" + routeCount + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", routeList=" + routeList +
                '}';
    }
}

