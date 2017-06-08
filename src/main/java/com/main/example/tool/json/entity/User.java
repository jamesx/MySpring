package com.main.example.tool.json.entity;

import lombok.Data;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Data
public class User {
    private String address;
    private Integer phone;

    public User() {
    }
    public User(String address, Integer phone) {
        this.address = address;
        this.phone = phone;
    }
}
