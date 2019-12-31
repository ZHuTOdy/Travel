package com.travel.web;

import com.travel.dao.impl.IndexDaoImpl;
import com.travel.pojo.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-08-02 08:52
 **/
@Controller
@RequestMapping("/category")
public class CategoryController {

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Category> findAll(){
        return new IndexDaoImpl().navigation();
    }
}
