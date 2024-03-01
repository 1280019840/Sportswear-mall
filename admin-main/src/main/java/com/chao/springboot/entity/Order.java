package com.chao.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.chao.springboot.utils.MyUUID;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
@ApiModel(value="TOrder对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String oNo;

    private Integer userId;

    private String username;

    private String phone;

    private String email;

    private String address;

    private Integer goodId;

    private String goodName;

    private Integer num;

    private Float total;

    private String state;

    private Date createTime;

    private String pay;

    private String express;

    private String remark;

    private String consignee;


    public Order(Integer userId,String username,String phone,String email,String address,Integer num,
                 Float total,String state,String pay,String express,String remark,String consignee){
        this.userId=userId;
        this.username=username;
        this.phone=phone;
        this.email=email;
        this.address=address;
        this.num=num;
        this.total=total;
        this.state=state;
        this.pay=pay;
        this.oNo="O"+ MyUUID.createUUID();
        this.createTime=new Date();
        this.express=express;
        this.remark=remark;
        this.state="待发货";
        this.consignee=consignee;
    }


}
