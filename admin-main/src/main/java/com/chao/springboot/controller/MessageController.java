package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beust.ah.A;
import com.chao.springboot.comm.Code;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.*;
import com.chao.springboot.service.*;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.LocaleData;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageService messageService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ApplicationService applicationService;


    //修改或新增
    @PostMapping("/save")
    public Result save(@RequestBody Message message) {
        message.setCreateTime(new Date());
        message.setLove(0);
        return Result.success(messageService.saveOrUpdate(message));
    }

    //修改或新增
    @GetMapping("/like/{id}/{userId}")
    public Result like(@PathVariable Integer id,@PathVariable Integer userId) {

        QueryWrapper<Application> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("good_id",id);
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("type",2);
        Application application = applicationService.getOne(queryWrapper);
        if(application!=null){
            return Result.error(Code.CODE_600,"已经点过赞了！");
        }

        Message message = messageService.getById(id);
        message.setLove(message.getLove()+1);

        applicationService.save(new Application(id,userId,2));

        return Result.success(messageService.saveOrUpdate(message));
    }



    //查询所有数据
    @GetMapping
    public Result findAll() {
        QueryWrapper<Message> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<Message> resList = messageService.list(queryWrapper);
        QueryWrapper<Leave> queryWrapper2=new QueryWrapper<>();

        for (Message message : resList) {
            queryWrapper2.clear();
            queryWrapper2.eq("message_id",message.getId());
            List<Leave> list = leaveService.list(queryWrapper2);
            message.setLeaveList(list);
        }

        return Result.success(resList);
    }

    //删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Leave> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("message_id",id);
        leaveService.remove(queryWrapper2);

        return Result.success(messageService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(messageService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        IPage<Message> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        queryWrapper.orderByDesc("id");

        return Result.success(messageService.page(page, queryWrapper));

    }
}
