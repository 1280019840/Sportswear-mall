package com.chao.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.springboot.comm.Code;
import com.chao.springboot.comm.RoleEnum;
import com.chao.springboot.entity.dto.UserDto;
import com.chao.springboot.entity.Menu;
import com.chao.springboot.entity.User;
import com.chao.springboot.exception.MyException;
import com.chao.springboot.mapper.RoleMapper;
import com.chao.springboot.mapper.RoleMenuMapper;
import com.chao.springboot.mapper.UserMapper;
import com.chao.springboot.service.MenuService;
import com.chao.springboot.service.UserService;
import com.chao.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Log LOG = Log.get();

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;

    @Resource
    MenuService menuService;

    @Resource
    UserMapper userMapper;

    @Override
    public UserDto login(UserDto UserDto) {
        // 用户密码 md5加密
        UserDto.setPassword(SecureUtil.md5(UserDto.getPassword()));
        User one = getUserInfo(UserDto);
        if (one != null){
            BeanUtil.copyProperties(one,UserDto,true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            UserDto.setToken(token);
            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            UserDto.setMenus(roleMenus);
            return UserDto;
        }else {
            throw new MyException(Code.CODE_600, "用户名或密码错误");
        }

    }

    @Override
    public User register(UserDto UserDto) {
        // 用户密码 md5加密
        UserDto.setPassword(SecureUtil.md5(UserDto.getPassword()));
        User one = getUserInfo(UserDto);
        if (one == null){
            one = new User();
            BeanUtil.copyProperties(UserDto,one,true);
            // 默认一个普通用户的角色
            one.setRole(RoleEnum.ROLE_USER.toString());
            if (one.getNickname() == null) {
                one.setNickname(one.getUsername());
            }
            save(one);//把 copy 完之后的用户数据存到数据库中
        }else {
            throw new MyException(Code.CODE_600, "用户已存在");
        }
        return null;
    }

    @Override
    public void updatePassword(UserDto userDTO) {
        int update = userMapper.updatePassword(userDTO);
        if (update < 1) {
            throw new MyException(Code.CODE_600, "密码错误");
        }
    }

    //封装方法
    private User getUserInfo(UserDto UserDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", UserDto.getUsername());
        queryWrapper.eq("password", UserDto.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new MyException(Code.CODE_500, "系统错误");
        }
        return one;
    }


    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

}
