package com.main.core.config.signal.stomp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/8.
 */
@Data
public class StompMessage implements Serializable{
    private String message;

    public StompMessage(String message) {
        this.message = message;
    }

    public StompMessage() {
    }
}
