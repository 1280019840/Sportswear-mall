package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Good;
import com.chao.springboot.entity.Images;
import com.chao.springboot.service.GoodService;
import com.chao.springboot.service.ImagesService;
import com.chao.springboot.utils.ImagesUtils;
import com.chao.springboot.utils.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/images")
public class ImagesController {


    @Autowired
    private ImagesService imagesService;

    @Autowired
    private ImagesUtils imagesUtils;

    //修改或新增
    @PostMapping
    public Result save(@RequestParam MultipartFile file) throws IOException {
        String url = imagesUtils.upload(file,1);
        Images images=new Images();
        images.setUrl(url);
        images.setCreateTime(new Date());
        return Result.success(imagesService.saveOrUpdate(images));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(imagesService.list());
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(imagesService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(imagesService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        IPage<Images> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Images> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        return Result.success(imagesService.page(page, queryWrapper));

    }

}
