package com.chao.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickname;
    @JsonIgnore
    private String password;
    private String email;
    private String address;
    private String phone;
    private Date createTime;
    private  String avatarUrl;
    private String role;
    private String sex;
}
