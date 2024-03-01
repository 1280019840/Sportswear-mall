package com.chao.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.springboot.entity.Menu;

import java.util.List;


public interface MenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
