package com.chao.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chao.springboot.entity.Dict;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}
