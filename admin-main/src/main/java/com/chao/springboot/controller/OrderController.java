package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Code;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.*;
import com.chao.springboot.entity.dto.OrderDto;
import com.chao.springboot.entity.vo.DataVo;
import com.chao.springboot.entity.vo.OrderVo;
import com.chao.springboot.service.*;
import com.chao.springboot.utils.MyUUID;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.poi.ss.formula.functions.Odd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderGoodService orderGoodService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodService goodService;

    @GetMapping("/dataOrderAndGood")
    public Result dataOrderAndGood(){

        int goodCount=0;
        int orderCount=0;
        for (Good good : goodService.list()) {
            goodCount+=good.getNum();
        }
        for (OrderGood orderGood : orderGoodService.list()) {
            orderCount+=orderGood.getNum();
        }

        List<DataVo> resList=new ArrayList<>();
        DataVo dataVo = new DataVo("订单商品数量",orderCount);
        DataVo dataVo2 = new DataVo("库存商品数量",goodCount);
        resList.add(dataVo);
        resList.add(dataVo2);


        return Result.success(resList);
    }

    @GetMapping("/dataGoodType")
    public Result dataGoodType(){

        Map<String,Integer> map=new HashMap<>();
        List<OrderGood> orderGoods = orderGoodService.list();

        for (OrderGood orderGood : orderGoods) {
            Good good = goodService.getById(orderGood.getGoodId());
            String type = good.getType();
            map.put(type, map.getOrDefault(type, 0)+orderGood.getNum());
        }

        List<DataVo> resList=new ArrayList<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            String key = stringIntegerEntry.getKey();
            Integer value = stringIntegerEntry.getValue();
            DataVo dataVo = new DataVo(key,value);
            resList.add(dataVo);
        }
        return Result.success(resList);
    }



    //修改或新增
    @PostMapping("/save")
    @Transactional
    public Result save(@RequestBody OrderDto orderDto) {

        Order order=new Order(orderDto.getUserId(),orderDto.getUsername(),orderDto.getPhone(),orderDto.getEmail(),orderDto.getAddress(),
                orderDto.getNum(),orderDto.getTotal(),orderDto.getState(),orderDto.getPay(),orderDto.getExpress(),orderDto.getRemark(),orderDto.getConsignee());
        orderService.saveOrUpdate(order);
        Integer id = order.getId();

        List<List<Integer>> goodList = orderDto.getGoodId();
        for ( List<Integer> good : goodList) {
            Integer key = good.get(0);
            Integer value = good.get(1);

            OrderGood orderGood=new OrderGood();
            orderGood.setGoodId(key);
            orderGood.setNum(value);
            orderGood.setOId(id);
            orderGoodService.save(orderGood);

            Good byId = goodService.getById(key);
            if(byId.getNum()<value){
                return Result.error(Code.CODE_600,"商品库存不足");
            }
            byId.setNum(byId.getNum()-value);
            goodService.saveOrUpdate(byId);

            QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",orderDto.getUserId());
            queryWrapper.eq("good_id",key);
            cartService.remove(queryWrapper);

        }


        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Order order) {
        return Result.success(orderService.saveOrUpdate(order));
    }

    @GetMapping("/updateOrder")
    public Result updateOrder(@RequestParam(defaultValue = "") String state,@RequestParam Integer id) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Order order = orderService.getOne(queryWrapper);
        order.setState(state);

        return Result.success(orderService.saveOrUpdate(order));
    }


    //查询所有数据
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String state,@RequestParam Integer userId) {
        List<OrderVo> resList=new ArrayList<>();
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        if(!state.equals("")){
            queryWrapper.eq("state",state);
        }
        List<Order> list = orderService.list(queryWrapper);

        for (Order order : list) {
            OrderVo orderVo=new OrderVo();
            BeanUtils.copyProperties(order,orderVo);

            QueryWrapper<OrderGood> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("o_id",order.getId());
            List<OrderGood> orderGoods = orderGoodService.list(queryWrapper1);

            for (OrderGood orderGood : orderGoods) {
                Good good = goodService.getById(orderGood.getGoodId());
                orderVo.getGoodList().add(good);
            }

            resList.add(orderVo);
        }

        return Result.success(resList);
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(orderService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(orderService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String username) {
        IPage<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        queryWrapper.orderByDesc("id");

        return Result.success(orderService.page(page, queryWrapper));

    }

}
