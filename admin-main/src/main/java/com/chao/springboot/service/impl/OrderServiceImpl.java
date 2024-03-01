package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Order;
import com.chao.springboot.mapper.OrderMapper;
import com.chao.springboot.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-12-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
