package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Application;
import com.chao.springboot.mapper.ApplicationMapper;
import com.chao.springboot.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-12-23
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}
