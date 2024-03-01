package com.chao.springboot.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {

    private Integer id;

    private Integer goodId;

    private Integer userId;

    private Integer num;

    private String goodName;

    private Float price;

    private String images;

}
