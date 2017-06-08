package com.main.core.config.signal.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        //通过topic和user前缀可以向客户端发送消息(基于内存)
        //config.enableSimpleBroker("/topic","/queue");
        //通过代理中继向客户端发送消息,可以连接远程rabbit,并修改监听端口和用户名密码
        config.enableStompBrokerRelay("/topic","/queue")
        .setRelayHost("localhost")
        .setRelayPort(61613)
        .setClientLogin("guest")
        .setClientPasscode("guest");
        //客户端向服务器发送消息需要加上app前缀
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 客户端连接Stomp的端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompServer");
    }

}
