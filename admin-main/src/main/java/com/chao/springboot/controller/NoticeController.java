package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Notice;
import com.chao.springboot.entity.Type;
import com.chao.springboot.service.NoticeService;
import com.chao.springboot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {


    @Autowired
    private NoticeService noticeService;


    //修改或新增
    @PostMapping
    public Result save(@RequestBody Notice notice) {
        notice.setCreateTime(new Date());
        return Result.success(noticeService.saveOrUpdate(notice));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(noticeService.list());
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(noticeService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(noticeService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        IPage<Notice> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("content", name);
        queryWrapper.orderByDesc("id");

        return Result.success(noticeService.page(page, queryWrapper));

    }

}
