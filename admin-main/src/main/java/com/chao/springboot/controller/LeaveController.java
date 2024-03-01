package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Images;
import com.chao.springboot.entity.Leave;
import com.chao.springboot.service.ImagesService;
import com.chao.springboot.service.LeaveService;
import com.chao.springboot.utils.ImagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;


    //修改或新增
    @PostMapping("/save")
    public Result save(@RequestBody Leave leave) throws IOException {
        return Result.success(leaveService.saveOrUpdate(leave));
    }

    //查询所有数据
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(leaveService.list());
    }


    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(leaveService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(leaveService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        IPage<Leave> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        return Result.success(leaveService.page(page, queryWrapper));

    }
}
