package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Address;
import com.chao.springboot.entity.Comment;
import com.chao.springboot.entity.Message;
import com.chao.springboot.service.CommentService;
import com.chao.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    //修改或新增
    @PostMapping
    public Result save(@RequestBody Comment  comment) {
        return Result.success(commentService.saveOrUpdate(comment));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(commentService.list());
    }

    @GetMapping("/{goodId}")
    public Result findAllByGoodId(@PathVariable Integer goodId) {
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("good_id",goodId);

        return Result.success(commentService.list(queryWrapper));
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(commentService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(commentService.removeByIds(ids));
    }

    //评价商品
    @PostMapping("/commentGoods")
    public Result commentGoods(@RequestBody Comment comment) {
        return Result.success(commentService.save(comment));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        queryWrapper.orderByDesc("id");

        return Result.success(commentService.page(page, queryWrapper));

    }

}
