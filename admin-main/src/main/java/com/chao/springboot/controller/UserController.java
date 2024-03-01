package com.chao.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Code;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.dto.UserDto;
import com.chao.springboot.entity.User;
import com.chao.springboot.service.UserService;
import com.chao.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/password")
    public Result password(@RequestBody UserDto passwordDTO) {
        passwordDTO.setPassword(SecureUtil.md5(passwordDTO.getPassword()));
        passwordDTO.setNewPassword(SecureUtil.md5(passwordDTO.getNewPassword()));
        userService.updatePassword(passwordDTO);
        return Result.success();
    }


    //修改或新增
    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    //个人信息修改功能
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");

        //获取当前用户信息
        User user = TokenUtils.getCurrentUser();
        if (user != null) {
            System.out.println("当前用户信息========================>"+user.getNickname());
        }

        return Result.success(userService.page(page, queryWrapper));

    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDto UserDto) {
        String username = UserDto.getUsername();
        String password = UserDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Code.CODE_400, "参数错误！");
        }
        UserDto dto = userService.login(UserDto);
        return Result.success(dto);

    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDto UserDto) {
        String username = UserDto.getUsername();
        String password = UserDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Code.CODE_400, "参数错误！");
        }
        return Result.success(userService.register(UserDto));
    }
}
