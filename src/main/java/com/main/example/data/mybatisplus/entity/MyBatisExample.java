package com.main.example.data.mybatisplus.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xsooy-pc on 2017/5/30.
 */
@TableName("mybatis_example")
@Data
public class MyBatisExample implements Serializable {
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private int age;
    @TableField("id_card")
    private String idCard;
    private String address;
    @TableField(exist = false)
    private int status;

}
