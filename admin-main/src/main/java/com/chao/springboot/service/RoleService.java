package com.chao.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.springboot.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {


    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
