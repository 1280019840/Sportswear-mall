package com.chao.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.springboot.entity.dto.UserDto;
import com.chao.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("update t_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserDto userDto);
}
