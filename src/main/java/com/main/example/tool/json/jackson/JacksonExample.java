package com.main.example.tool.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.main.example.tool.json.entity.MyTest;
import com.main.example.tool.json.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/29.
 */
public class JacksonExample {
    public static void main(String args[])throws  Exception{
        ObjectMapper mapper=new ObjectMapper();


        /**
         * 集合转json
         */
        List<MyTest> list1= Lists.newArrayList(
         new MyTest("1","sam",18),
         new MyTest("2","tom",21),
         new MyTest("3","amy",32)
        );
        String jsonArray=mapper.writeValueAsString(list1);

        /**
         * json串转数组
         */
        MyTest[] array1=mapper.readValue(jsonArray,MyTest[].class);
        System.out.println(array1[0]+"--json串转数组");

        /**
         * json串转集合
         */
        List<MyTest> list2=mapper.readValue(jsonArray,List.class);
        System.out.println(list2.get(1)+"--json串转集合");

        /**
         *使用TypeReference帮助Jackson理解正确的转换类型
         */
        List<MyTest> list3=mapper.readValue(jsonArray,new TypeReference<List<MyTest>>() { });
        System.out.println(list3.get(2)+"--json串转集合");

        /**
         * jackson转换多级对象
         */
        MyTest test1=new MyTest("1","sam",18);
        test1.setUser(Lists.newArrayList(new User("sss",123),new User("asa",456)));
        String objArray=mapper.writeValueAsString(test1);
        MyTest test2=mapper.readValue(objArray,MyTest.class);
        System.out.println(test2+"--多级对象");

        /**
         * List类型转换
         */
        List<MyTest> list4=new ArrayList<>();
        list4.add(test1);
        list4.add(test2);
        MyTest test3=new MyTest("2","solr",22);
        test3.setUser(Lists.newArrayList(new User("li",32),new User("wang",44)));
        list4.add(test3);
        String ss=mapper.writeValueAsString(list4);
        List<MyTest>asList=mapper.readValue(ss,List.class);
        System.out.println(asList.get(2)+"--负责对象集合类型转换");

        /**
         * map类型转换
         */
        Map<String,MyTest> map=new HashMap<>();
        map.put("ss1",test1);
        map.put("ss2",test2);
        String mary=mapper.writeValueAsString(map);
        Map<String,MyTest>map2=mapper.readValue(mary,Map.class);
        System.out.println(map2.get("ss2")+"--负责对象集合转换");


        /**
         * json和List的互相转换
         */
        List<MyTest>list=new ArrayList<>();
        List<User> user= Lists.newArrayList(new User("li",32),new User("wang",44));
        MyTest test5=new MyTest("1","es",15);
        test5.setUser(user);
        MyTest test6=new MyTest("2","solr",22);
        test6.setUser(user);
        list.add(test5);
        list.add(test6);
        String json=mapper.writeValueAsString(list);
        List<MyTest>list7=mapper.readValue(json,List.class);
        System.out.println(json+"--json转复杂list");
        System.out.println(list7.size());

        Map<String,MyTest> map5=new HashMap<>();
        map.put("test1",test1);
        map.put("test2",test2);
        String json2=mapper.writeValueAsString(map);
        System.out.println(json2+"--Json转复杂map");
        Map<String,MyTest> map6=mapper.readValue(json2,Map.class);
    }
}
