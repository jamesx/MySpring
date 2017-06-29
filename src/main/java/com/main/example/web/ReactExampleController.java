package com.main.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/29.
 */
@Controller
@RequestMapping("react")
public class ReactExampleController {
    @RequestMapping("index")
    public String index(){
        return "web/react/index";
    }
}
