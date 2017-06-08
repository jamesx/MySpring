package com.main.example.data.mybatisplus.controller;

import com.main.example.data.mybatisplus.entity.MyBatisExample;
import com.main.example.data.mybatisplus.service.MyBatisExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xsooy-pc on 2017/5/30.
 */
@Controller
@RequestMapping("mybatis")
public class MyBatisExampleController {
    @Autowired
    MyBatisExampleService service;
    @RequestMapping("selectById")
    @ResponseBody
    public MyBatisExample selectById(){
       long id=Long.parseLong("869406054652395520");
        return service.selectById(id);
    }
    @RequestMapping("insert")
    public void insert(){
        MyBatisExample example=new MyBatisExample();
        example.setName("tom");
        example.setAge(18);
        example.setAddress("上海");
        example.setIdCard("37068718839943434");
        example.setStatus(1);
       service.insert(example);
    }
    @RequestMapping
    @ResponseBody
    public List<MyBatisExample> findAll(){
     return service.findAll();
    }
}
