package com.main.example.signal.stomp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xsooy-pc on 2017/6/3.
 */
@Controller
@RequestMapping("stomp")
public class StompController {
    private static Logger log= LogManager.getLogger();
    @RequestMapping("index")
    public String index(){
        return "signal/stomp/index";
    }

    /**
     * 接收请求
     * @param message
     */
    @MessageMapping("user01")
    @SendTo("/topic/hello")
    public GenericMessage<String>  server(GenericMessage<String> message){
    log.info("Server收到消息: "+message);
    return new GenericMessage<String>("stompServer收到消息了");
    }
    /**
     * 接收请求并给予响应
     * 处理对/app/user02的订阅
     */
    @SubscribeMapping("user02")
    public GenericMessage<String> subServer(GenericMessage<String> message){
        log.info("SubServer收到消息: "+message);
        return message;
    }
}
