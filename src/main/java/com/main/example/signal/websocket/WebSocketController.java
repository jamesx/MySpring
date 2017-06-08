package com.main.example.signal.websocket;
import com.alibaba.fastjson.JSON;
import com.main.core.config.signal.websocket.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xsooy-pc on 2017/6/3.
 */
@Controller
@RequestMapping("websocket")
public class WebSocketController {
    @Autowired
    SpringWebSocketHandler handler;
    @RequestMapping("index")
    public String websocket(HttpServletRequest request){
        String username=request.getParameter("username");
        request.getSession().setAttribute("SESSION_USERNAME",username);
        return "signal/websocket/index";
    }
    @RequestMapping("sendToUser")
    @ResponseBody
    public String sendToUser(String message,String username){
        String json= JSON.toJSONString(message);
        handler.sendMessageToUser(username,new TextMessage(json));
        return "ok!";
    }
    @RequestMapping("server")
    public String server(){
        return "signal/websocket/server";
    }
    @RequestMapping("login")
    public String login(HttpServletRequest request){
       return "signal/websocket/login";
    }
}
