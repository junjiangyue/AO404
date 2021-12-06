package com.example.ao404.controller;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.User;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ao404.mapper.FeedbackMapper.helper;

@RestController
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