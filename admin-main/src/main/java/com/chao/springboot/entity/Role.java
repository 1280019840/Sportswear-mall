package com.chao.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("t_role")
public class Role implements Serializable {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String flag;
}
