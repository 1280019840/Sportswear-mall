package com.chao.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_dict")
public class Dict {

    private String name;
    private String value;
    private String type;

}
