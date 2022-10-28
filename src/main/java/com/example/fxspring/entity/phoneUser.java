package com.example.fxspring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class phoneUser {
    @TableField(value = "username")
    private String username;
    @TableField(value = "uphone")
    private String uphone;
    @TableField(value = "uaddress")
    private String uaddress;
}
