package com.chao.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("t_role_menu")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;
}
