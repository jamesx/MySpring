package com.main.example.tool.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.main.example.tool.json.entity.MyTest;
import com.main.example.tool.json.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsooy-pc on 2017/5/29.
 */
public class FastJsonExample {
    public static void main(String[] args){
        /**
         *Json和List的互相转换
         */
        List<MyTest>list=new ArrayList<>();
        List<User> user= Lists.newArrayList(new User("li",32),new User("wang",44));
        MyTest test1=new MyTest("1","es",15);
        test1.setUser(user);
        MyTest test2=new MyTest("2","solr",22);
        test2.setUser(user);
        list.add(test1);
        list.add(test2);
        String jsonString= JSON.toJSONString(list,true);
        System.out.println(jsonString+"--Json和List的互相转换");
        List<MyTest>list2=JSON.parseArray(jsonString,MyTest.class);
        System.out.println(list2.size()+"--Json和List的互相转换");

        /**
         * --JSON和Map互相转换
         */
        Map<String,MyTest> map=new HashMap<>();
        map.put("test1",test1);
        map.put("test2",test2);
        String jsonString2=JSON.toJSONString(map,true);
        System.out.println(jsonString2+"--JSON和Map互相转换");
        Map<String,MyTest>map2= (Map<String, MyTest>) JSON.parse(jsonString2);
        System.out.println(map2+"--JSON和Map互相转换");
        /**
         * json与数组的互相转换
         */
        MyTest[] bytes={
           test1,test2
        };
        String json2=JSON.toJSONString(bytes);
        JSONArray bytes2=JSON.parseArray(json2);
        System.out.println(bytes2+"--数组");

        /**
         * Json和对象之间的互相转换
         */
        String obj=JSON.toJSONString(test1);
        MyTest my=JSON.parseObject(obj,MyTest.class);
        System.out.println(obj+"---json-object");
        System.out.println(my+"---json-object");
    }

}
