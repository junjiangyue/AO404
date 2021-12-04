package com.example.ao404.controller;

import com.example.ao404.entity.User;
import com.example.ao404.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired(required=false)
    private UserMapper userMapper;

    @ApiOperation(value="登录")
    @PostMapping("login")
    public Map<String,Object> login(int userId, String userPassword){

        Map<String,Object> map = new HashMap<>();
        User user = userMapper.loginUser(userId);
        if(user==null)
        {
            map.put("msg","用户不存在");
            return map;
        }
        if(userPassword.equals(user.getUserPassword()))
        {

            map.put("msg","登陆成功");
            return map;
        }
        else
        {
            map.put("msg","密码错误");
            return map;

        }
    }
}
