package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Message;
import com.chao.springboot.mapper.MessageMapper;
import com.chao.springboot.service.MessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
