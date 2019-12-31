package com.travel.pojo;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * @ClassName Category
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 14:51
 **/
public class Category implements Serializable {
    private int cid;
    private String cname;

    @Override
    public String toString() {
        return "Category{" + "cid=" + cid + ", cname=" + cname + '\'' + '}';
    }

    public Category() {
    }

    public Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
