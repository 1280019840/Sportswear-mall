package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Good;
import com.chao.springboot.mapper.GoodMapper;
import com.chao.springboot.service.GoodService;
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
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

}
