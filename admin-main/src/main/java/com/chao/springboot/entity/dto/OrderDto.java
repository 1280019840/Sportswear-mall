package com.chao.springboot.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;

    private String oNo;

    private Integer userId;

    private String username;

    private  List<List<Integer>> goodId;

    private Integer num;

    private Float total;

    private String state;

    private LocalDate createTime;

    private String pay;

    private String phone;

    private String email;

    private String address;

    private String express;

    private String remark;

    private String consignee;
}
