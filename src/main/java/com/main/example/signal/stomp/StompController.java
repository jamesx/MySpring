package com.main.example.signal.stomp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xsooy-pc on 2017/6/3.
 */
@Controller
@RequestMapping("stomp")
public class StompController {
    @RequestMapping("index")
    public String index(){
        return "signal/stomp/index";
    }
}
