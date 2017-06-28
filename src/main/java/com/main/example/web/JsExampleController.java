package com.main.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
@RequestMapping("js")
public class JsExampleController {
    @RequestMapping("index")
    public String index(){
        return "/web/es6/index";
    }
}
