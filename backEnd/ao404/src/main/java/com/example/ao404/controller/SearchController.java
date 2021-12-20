package com.example.ao404.controller;

import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.entity.Tag;
import com.example.ao404.entity.User;
import com.example.ao404.mapper.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RelationMapper relationMapper;

    RestControllerHelper helper = new RestControllerHelper();
    @ApiOperation(value = "搜索")
    @PostMapping("search")
    public Map<String, Object> search(String searchKey) {

        Map<String,Object> map = new HashMap<>();
        List<User> userResult = userMapper.resultUser(searchKey);

//        for(int c=0;c<userResult.size();c++)
//        {
//            List<UserInformation> followList = relationMapper.following(userResult.get(c).getUserId());
//            List<UserInformation> fanList = relationMapper.fans(userResult.get(c).getUserId());
//
//            for(int i = 0; i<followList.size();i++)
//            {
//                userResult.get(c).setIsMyFollow(1);
//            }
//            for(int m = 0; m<fanList.size();m++)
//            {
//                userResult.get(m).setIsMyFan(1);
//
//            }
//        }

        List<Tag> tagResult = tagMapper.resultTag(searchKey);
        map.put("userResult",userResult);
        map.put("tagResult",tagResult);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();



    }

}
