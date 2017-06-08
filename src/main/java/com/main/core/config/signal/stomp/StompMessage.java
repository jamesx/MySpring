package com.main.core.config.signal.stomp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by superMan791 on 2017/5/6.
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
