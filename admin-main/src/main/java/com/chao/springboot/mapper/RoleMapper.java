package com.chao.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.springboot.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from t_role where flag = #{flag}")
    Integer selectByFlag(String role);
}
