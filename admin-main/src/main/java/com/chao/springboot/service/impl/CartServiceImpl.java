package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Cart;
import com.chao.springboot.mapper.CartMapper;
import com.chao.springboot.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-12-20
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
