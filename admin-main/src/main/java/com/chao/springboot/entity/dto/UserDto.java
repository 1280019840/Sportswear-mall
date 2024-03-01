package com.chao.springboot.entity.dto;

import com.chao.springboot.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String email;
    private String phone;
    private String token;
    private String sex;
    private String role;
    private String newPassword;
    private List<Menu> menus;

}
