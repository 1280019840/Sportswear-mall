package com.chao.springboot.entity.vo;

import com.chao.springboot.entity.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

    private Integer id;

    private String oNo;

    private Integer userId;

    private String username;

    private String phone;

    private String email;

    private String address;

    private List<Good> goodList=new ArrayList<>();

    private Integer num;

    private Float total;

    private String state;

    private Date createTime;

    private String pay;

    private String express;

    private String remark;

    private String consignee;
}
