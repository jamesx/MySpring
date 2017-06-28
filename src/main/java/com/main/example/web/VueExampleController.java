package com.main.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xsooy-pc on 2017/6/10.
 *
 */
@Controller
@RequestMapping("vue")
public class VueExampleController {
    @RequestMapping("index")
    public String index(){
        return "/web/vue/index";
    }
}

