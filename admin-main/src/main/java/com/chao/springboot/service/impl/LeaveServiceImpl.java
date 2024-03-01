package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Leave;
import com.chao.springboot.mapper.LeaveMapper;
import com.chao.springboot.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

}
