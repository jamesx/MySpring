package com.main.core.config.signal.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by Administrator on 2017/2/17.
 */
@EnableRabbit
@Configuration
@PropertySource(value={"classpath:properties/rabbit.properties"})
public class RabbitConfig {
    @Autowired
    Environment environment;
    /**
     * rabbit连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        //默认监听localhost的5672端口
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setHost(environment.getProperty("rabbit.host"));//设置默认监听的ip
        connectionFactory.setPort(Integer.parseInt(environment.getProperty("rabbit.port")));//设置默认监听端口号
        connectionFactory.setUsername(environment.getProperty("rabbit.username"));
        connectionFactory.setPassword(environment.getProperty("rabbit.password"));
        connectionFactory.setChannelCacheSize(Integer.parseInt(environment.getProperty("channel.cache.size")));
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        connectionFactory.createConnection();
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin=new RabbitAdmin(connectionFactory());
        rabbitAdmin.afterPropertiesSet();
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(JsonMessageConverter());
        template.setReplyTimeout(2000);
        template.setRetryTemplate(retryTemplate());
        template.setMandatory(true);
        template.setConfirmCallback(messageConfirm());
        template.setReturnCallback(messageReturn());
        return template;
    }
    @Bean
    public MessageConfirm messageConfirm(){
        return new MessageConfirm();
    }
    @Bean
    public  MessageReturn messageReturn(){
        return new MessageReturn();
    }
    //retryTemplate为连接失败时的重试模板
    @Bean
    public RetryTemplate retryTemplate(){
        RetryTemplate retryTemplate=new RetryTemplate();
        ExponentialBackOffPolicy back=new ExponentialBackOffPolicy();
        back.setInitialInterval(5000);
        back.setMaxInterval(10000);
        back.setMultiplier(10.0);
        retryTemplate.setBackOffPolicy(back);
        SimpleRetryPolicy policy=new SimpleRetryPolicy();
        policy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;

    }
    @Bean
    public MessageConverter JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    // Every queue is bound to the default direct exchange
    public Queue asyncQueue() {
        Queue queue=new Queue(environment.getProperty("queue.async"));
        queue.setAdminsThatShouldDeclare(rabbitAdmin());
        return queue;
    }
    @Bean
    // Every queue is bound to the default direct exchange
    public Queue replyQueue() {
        Queue queue=new Queue(environment.getProperty("queue.reply"));
        queue.setAdminsThatShouldDeclare(rabbitAdmin());
        return queue;
    }
    @Bean
    // Every queue is bound to the default direct exchange
    public Queue errorQueue() {
        Queue queue=new Queue(environment.getProperty("queue.error"));
        queue.setAdminsThatShouldDeclare(rabbitAdmin());
        return queue;
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory rabbitListenerFactory=new SimpleRabbitListenerContainerFactory();
        rabbitListenerFactory.setMessageConverter(JsonMessageConverter());
        rabbitListenerFactory.setConnectionFactory(connectionFactory());
        rabbitListenerFactory.setConcurrentConsumers(9);
        rabbitListenerFactory.setMaxConcurrentConsumers(50);
        rabbitListenerFactory.setPrefetchCount(3);
        rabbitListenerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        rabbitListenerFactory.setErrorHandler(errorHandler());
        return rabbitListenerFactory;
    }
    @Bean
    public MQErrorHandler errorHandler(){
        return new MQErrorHandler();
    }
    @Bean
    public Exchange exchange(){
        DirectExchange directExchange=new DirectExchange(environment.getProperty("exchange.direct"));
        directExchange.setAdminsThatShouldDeclare(rabbitAdmin());
        return directExchange;
    }
    @Bean
    public Binding asyncBinding() {
        Binding binding = new Binding(environment.getProperty("queue.async"), DestinationType.QUEUE, exchange().getName(), environment.getProperty("route.async"), null);
        binding.setAdminsThatShouldDeclare(rabbitAdmin());
        return binding;
    }
    @Bean
    public Binding replyBinding() {
        Binding binding = new Binding(environment.getProperty("queue.reply"), DestinationType.QUEUE, exchange().getName(), environment.getProperty("route.reply"), null);
        binding.setAdminsThatShouldDeclare(rabbitAdmin());
        return binding;
    }
    @Bean
    public Binding errorBinding() {
        Binding binding = new Binding(environment.getProperty("queue.error"), DestinationType.QUEUE, exchange().getName(), environment.getProperty("route.error"), null);
        binding.setAdminsThatShouldDeclare(rabbitAdmin());
        return binding;
    }
}
