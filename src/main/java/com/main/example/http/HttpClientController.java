package com.main.example.http;

import com.fasterxml.jackson.annotation.JsonView;
import com.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsooy-pc on 2017/6/3.
 */
@RestController
@RequestMapping("http")
public class HttpClientController {
    @RequestMapping("get_test")
    public String  get(){
        Map<String,String>map=new HashMap<>();
        map.put("username","张三");
        map.put("password","123");
        String result=HttpClientUtil.doGet("http://localhost:8080/http/reply",map);
        return result;
    }
    @RequestMapping("post_test")
    public String post(){
        Map<String,String>map=new HashMap<>();
        map.put("username","李四");
        map.put("password","456");
        String result=HttpClientUtil.doPost("http://localhost:8080/http/reply",map);
        return result;
    }
    @RequestMapping("get_test2")
    public Map<String,String>  get2()throws  Exception{
        Map<String,String>map=new HashMap<>();
        map.put("username","王五");
        String password = map.put("password", "123");
       Map<String,String> result=HttpClientUtil.doGet("http://localhost:8080/http/reply",map,HashMap.class);
        return result;
    }
    @RequestMapping("post_test2")
    public Map<String,String> post2()throws Exception{
        Map<String,String>map=new HashMap<>();
        map.put("username","赵六");
        map.put("password","789");
       Map<String,String> result=HttpClientUtil.doPost("http://localhost:8080/http/reply",map,HashMap.class,null);
        return result;
    }
    @RequestMapping("reply")
    public Map<String,String> reply(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Map<String,String>map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return map;

    }

}
