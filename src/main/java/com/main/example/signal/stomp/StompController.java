package com.main.example.signal.stomp;

import com.main.core.config.signal.stomp.StompMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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

    //简单的文本回应类，使用该对象可以在任意地方回复消息到某个主题
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 接收请求
     * @param message
     */
    @MessageMapping("user01")
    @SendTo("/topic/hello")
    public StompMessage  server(StompMessage message){
    log.info("Server收到消息: "+message);
    messagingTemplate.convertAndSend("/topic/hello",new StompMessage("user01:哈哈哈!"));

    return new StompMessage("user01收到消息了");
    }
    /**
     * 接收请求并给予响应
     * 处理对/app/user02的订阅
     */
    @SubscribeMapping("user02")
    public StompMessage subServer(){
        StompMessage message=new StompMessage("这是Subscribe消息!");
        return message;
    }
}
