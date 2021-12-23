package com.example.ao404.controller;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.User;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ao404.mapper.FeedbackMapper.helper;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @ApiOperation(value = "获取用户列表")
    @PostMapping("getUserList")
    public Map<String, Object> getUserList() {
        Map<String,Object> map = new HashMap<>();

        List<User> userList = userMapper.userList();

        map.put("userList", userList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }
    @ApiOperation(value = "本周新增帖子数")
    @GetMapping("weekNew")
    public Map<String, Object> weekNew() {
        Map<String,Object> map = new HashMap<>();

        List<Article> artilceList = articleMapper.articleList();

        int count=0 ;

        for(int i=0;i<artilceList.size();i++)
        {
            long t1 = artilceList.get(i).getPublishTime().getTime();
            long t2 =  System.currentTimeMillis() ;
            int hours=(int) ((t2 - t1)/(1000*60*60));
            if(hours<=168)
            {
                count++;
            }
        }
        map.put("weekNew", count);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("deleteUser")
    public Map<String, Object> deleteUser(int userId) {
        Map<String,Object> map = new HashMap<>();

        userMapper.deleteUser(userId);

        map.put("msg", "删除成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "获取文章列表")
    @PostMapping("getArticleList")
    public Map<String, Object> getArticleList() {
        Map<String,Object> map = new HashMap<>();

        List<Article> artilceList = articleMapper.articleList();

        map.put("articleList", artilceList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("deleteArticle")
    public Map<String, Object> deleteArticle(int articleId) {
        Map<String,Object> map = new HashMap<>();

        articleMapper.deleteArticle(articleId);

        map.put("msg", "删除成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }



}