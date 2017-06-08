package com.main.example.tool.retry.service;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Service
public class RetryExampleService {
    public static int i=0;
    public String my(){
        return "MySpEL";
    }
    @Retryable(value=RemoteAccessException.class,maxAttempts=5,backoff=@Backoff(delay =5000l,multiplier =2))
    public int retry(){
        System.out.println("调用结果为："+i);
        i++;
        if(i<4){
            throw new RemoteAccessException("RPC调用异常");
        }
        return i;
    }
    @Recover
    public String recover(RemoteAccessException e){
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
