package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Comment;
import com.chao.springboot.mapper.CommentMapper;
import com.chao.springboot.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
