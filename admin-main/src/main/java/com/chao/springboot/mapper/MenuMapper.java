package com.chao.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.springboot.entity.Menu;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
