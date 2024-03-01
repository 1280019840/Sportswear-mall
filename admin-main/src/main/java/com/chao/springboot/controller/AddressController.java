package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Address;
import com.chao.springboot.entity.Message;
import com.chao.springboot.service.AddressService;
import com.chao.springboot.service.MessageService;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    //修改或新增
    @PostMapping
    public Result save(@RequestBody Address address) {
        return Result.success(addressService.saveOrUpdate(address));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(addressService.list());
    }

    @GetMapping("/byUserId/{id}")
    public Result findAllByUserId(@PathVariable Integer id) {
        QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);

        return Result.success(addressService.list(queryWrapper));
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(addressService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(addressService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        IPage<Address> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        queryWrapper.orderByDesc("id");

        return Result.success(addressService.page(page, queryWrapper));

    }

}
