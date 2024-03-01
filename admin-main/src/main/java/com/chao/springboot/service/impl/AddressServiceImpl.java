package com.chao.springboot.service.impl;

import com.chao.springboot.entity.Address;
import com.chao.springboot.mapper.AddressMapper;
import com.chao.springboot.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-12-21
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
