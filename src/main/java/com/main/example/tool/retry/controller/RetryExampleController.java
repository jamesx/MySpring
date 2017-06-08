package com.main.example.tool.retry.controller;

import com.main.example.tool.retry.service.RetryExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Controller
@RequestMapping("retry")
public class RetryExampleController {
    @Autowired
    RetryExampleService service;
    @GetMapping("retry")
    public String retry(){
        int i= service.retry();
        System.out.print("调用成功，结果为:"+i);
        return "index";
    }
}
