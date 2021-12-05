package com.example.ao404.controller;

import com.example.ao404.entity.Article;
import com.example.ao404.entity.ArticleInformation;
import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.TagMapper;
import com.example.ao404.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;

    RestControllerHelper helper = new RestControllerHelper();


    @ApiOperation(value = "发布帖子")
    @PostMapping("publishArticle")
    public Map<String, Object> publishArticle(int userId, String articleHeading,String articleContent,int tagId) {

        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isEmpty(userId+"")||StringUtils.isEmpty(articleContent)||StringUtils.isEmpty(articleHeading)||StringUtils.isEmpty(tagId+""))
        {
            map.put("msg","关键数据缺失");

            return map;
        }

        if(userMapper.loginUser(userId)==null)
        {
            map.put("msg","用户id不存在");
            return map;
        }
        if(tagMapper.searchTag(tagId)==null)
        {
            map.put("msg","tag不存在");
            return map;

        }
        int result = articleMapper.publishArticle(userId,articleHeading,articleContent,tagId);
        if(result>0)
        {
            map.put("msg","发布成功");

            return map;
        }
        else {
            map.put("msg","发布失败");

            return map;
        }


    }


    @ApiOperation(value = "首页的帖子")
    @GetMapping("homeArticle")
    public Map<String,Object> homeArticle(int userId){

        Map<String,Object> map = new HashMap<>();

        List<Article> homeList = articleMapper.getHomeArticle(userId);

        List<ArticleInformation> homeList1 = new ArrayList<>();

        for(int i=0;i<homeList.size();i++)
        {
            homeList1.add(new ArticleInformation(homeList.get(i).getArticleId(),homeList.get(i).getUserId(),homeList.get(i).getArticleHeading(),homeList.get(i).getArticleContent(),homeList.get(i).getPublishTime(),homeList.get(i).getTagId(),articleMapper.LikeNum(homeList.get(i).getArticleId()),articleMapper.ArticleComment(homeList.get(i).getArticleId()).size()));
        }

        Collections.reverse(homeList1);
        map.put("homeList",homeList1);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "某个用户发布的所有帖子")
    @GetMapping("userArticle")
    public Map<String,Object> userArticle(int userId){

        Map<String,Object> map = new HashMap<>();

        List<Article> articleList = articleMapper.getUserArticle(userId);

        List<ArticleInformation> userArtList = new ArrayList<>();

        for(int i=0;i<articleList.size();i++)
        {
            userArtList.add(new ArticleInformation(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),articleList.get(i).getTagId(),articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size()));
        }

        Collections.reverse(userArtList);
        map.put("userArtList",userArtList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

}