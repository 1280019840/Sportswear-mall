package com.chao.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.springboot.comm.Result;
import com.chao.springboot.entity.Cart;
import com.chao.springboot.entity.Good;
import com.chao.springboot.entity.Message;
import com.chao.springboot.entity.vo.CartVo;
import com.chao.springboot.service.CartService;
import com.chao.springboot.service.GoodService;
import com.chao.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodService goodService;


    //修改或新增
    @GetMapping("/{goodId}/{userId}")
    public Result save(@PathVariable Integer goodId,@PathVariable Integer userId) {

        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("good_id",goodId);
        queryWrapper.eq("user_id",userId);
        Cart cart = cartService.getOne(queryWrapper);
        if(null!=cart){
            cart.setNum(cart.getNum()+1);
        }else {
            cart=new Cart();
            cart.setGoodId(goodId);
            cart.setUserId(userId);
            cart.setNum(1);
        }

        return Result.success(cartService.saveOrUpdate(cart));
    }

    //查询所有数据
    @GetMapping
    public Result findAll() {

        return Result.success(cartService.list());
    }

    @GetMapping("byUserId/{id}")
    public Result findAllByUserId(@PathVariable Integer id) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<Cart> list = cartService.list(queryWrapper);

        List<CartVo> resList=new ArrayList<>();
        for (Cart cart : list) {
            Good good = goodService.getById(cart.getGoodId());

            CartVo cartVo=new CartVo(cart.getId(),good.getId(),cart.getUserId(),cart.getNum(),good.getName(),good.getPrice(),good.getImages());
            resList.add(cartVo);
        }

        return Result.success(resList);
    }

    @GetMapping("update/{id}/{num}")
    public Result update(@PathVariable Integer id,@PathVariable Integer num) {
        Cart cart = cartService.getById(id);
        cart.setNum(num);

        cartService.saveOrUpdate(cart);
        return Result.success();
    }


    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(cartService.removeById(id));
    }

    @GetMapping("delete/{id}")
    public Result del(@PathVariable Integer id) {
        return Result.success(cartService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteByBatch(@RequestBody List<Integer> ids) {
        return Result.success(cartService.removeByIds(ids));
    }

    //分页查询  Mybatis-plus插件实现
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        IPage<Cart> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        queryWrapper.orderByDesc("id");

        return Result.success(cartService.page(page, queryWrapper));

    }

}
