package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Type;
import com.chao.springboot.mapper.TypeMapper;
import com.chao.springboot.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
