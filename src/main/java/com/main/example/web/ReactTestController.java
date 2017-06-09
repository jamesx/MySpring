package com.main.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by superMan719 on 2017/6/9.
 */
@Controller
@RequestMapping("react")
public class ReactTestController {
    @RequestMapping("index")
    public String index(){
        return "/web/index";
    }
}
