package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Notice;
import com.chao.springboot.mapper.NoticeMapper;
import com.chao.springboot.service.NoticeService;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
