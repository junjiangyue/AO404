package com.example.ao404.controller;

import com.example.ao404.entity.Admin;
import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.entity.User;
import com.example.ao404.entity.UserInformation;
import com.example.ao404.mapper.RelationMapper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.tools.GetInformationFromRequest;
import com.example.ao404.tools.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired()
    private UserMapper userMapper;
    @Autowired
    private RelationMapper relationMapper;


    RestControllerHelper helper = new RestControllerHelper();


    @ApiOperation(value="登录")
    @PostMapping("login")
    public Map<String,Object> login(int userId, String userPassword){
        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isEmpty(userId))
        {
            map.put("msg","关键数据缺失");

            return map;
        }

        Admin admin = userMapper.loginAdmin(userId);

        if(admin!=null)
        {
          if(userPassword.equals(admin.getAdminPassword()))
          {
            try
            {Map<String,String> payload = new HashMap<>();
              payload.put("userId", String.valueOf(userId));
              //payload.put("userPassword",userPassword);
              String token = JwtConfig.getToken(payload);
              map.put("msg","登录成功");
              map.put("token",token);}
            catch (Exception e){
              map.put("msg",e.getMessage());
            }

            return map;
          }
          else {
            map.put("msg","密码错误");
            return map;
          }
        }
        else {
          User user = userMapper.loginUser(userId);
          if (user == null) {
            map.put("msg", "用户不存在");
            return map;
          }
          if (userPassword.equals(user.getUserPassword())) {
            try {
              Map<String, String> payload = new HashMap<>();
              payload.put("userId", String.valueOf(userId));
              //payload.put("userPassword",userPassword);
              String token = JwtConfig.getToken(payload);
              map.put("msg", "登录成功");
              map.put("token", token);
            } catch (Exception e) {
              map.put("msg", e.getMessage());
            }

            return map;
          } else {
            map.put("msg", "密码错误");
            return map;

          }
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public Map<String,Object> register(String userName,String userPassword,String userEmail )
    {
        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(userPassword)||StringUtils.isEmpty(userEmail))
        {
            map.put("msg","关键数据缺失");

            return map;
        }
        else{
            User user1 = new User(userName, userPassword, userEmail);
            int result = userMapper.register(user1);
            if(result>0)
            {
                map.put("msg","注册成功");
                map.put("userId",user1.getUserId());
                return map;
            }
            else {
                map.put("msg","注册失败");

                return map;
            }
        }
    }

    @ApiOperation(value="获取关注的人和粉丝")
    @PostMapping("getMyRelation")
    public Map<String,Object> getMyRelation(HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        List<UserInformation> followList = relationMapper.following(userId);
        List<UserInformation> fanList = relationMapper.fans(userId);

        for(int i = 0; i<followList.size();i++)
        {
            followList.get(i).setIsMyFollow(1);
            for(int j = 0; j<fanList.size();j++)
            {
                if(fanList.get(j).getUserId()==followList.get(i).getUserId())
                {
                    fanList.get(j).setIsMyFollow(1);
                }
            }
        }
        for(int m = 0; m<fanList.size();m++)
        {
            fanList.get(m).setIsMyFan(1);
            for(int n = 0; n<followList.size();n++)
            {
                if(followList.get(n).getUserId()==fanList.get(m).getUserId())
                {
                    followList.get(n).setIsMyFan(1);
                }
            }
        }
        map.put("followList",followList);
        map.put("fanList",fanList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value="获取我的信息")
    @GetMapping("myInfo")
    public Map<String,Object> getMyInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        User user= userMapper.loginUser(userId);

        map.put("userId",user.getUserId());
        map.put("userName",user.getUserName());
        map.put("userEmail",user.getUserEmail());
        map.put("userAvatar",user.getUserAvatar());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value="修改密码")
    @PostMapping("resetPassword")
    public Map<String,Object> resetPassword(HttpServletRequest request,String oldPassword,String newPassword) {

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        if(userMapper.loginUser(userId).getUserPassword()!=oldPassword)
        {
            map.put("msg","原密码错误");
            helper.setMsg("Error");
            helper.setData(map);
            return helper.toJsonMap();

        }
        int result = userMapper.updatePassword(userId,newPassword);

        if(result ==1)
        {
            map.put("msg","修改成功");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();

        }
        else {
            map.put("msg","修改失败");
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();

        }
    }
}
