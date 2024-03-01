package com.chao.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.springboot.entity.User;
import com.chao.springboot.entity.dto.UserDto;


public interface UserService extends IService<User> {

    UserDto login(UserDto UserDto);

    User register(UserDto UserDto);

    void updatePassword(UserDto UserDto);
}
