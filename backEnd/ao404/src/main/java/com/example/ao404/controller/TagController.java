package com.example.ao404.controller;

import com.example.ao404.entity.Article;
import com.example.ao404.entity.ArticleInformation;
import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.entity.Tag;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.TagMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;

    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "关注的tag列表")
    @GetMapping("myLikeTag")
    public Map<String, Object> myLikeTag(int userId) {
        Map<String, Object> map = new HashMap<>();

        List<Integer> tagList = tagMapper.myLikeTag(userId);

        List<Article> tagArticle = new ArrayList<>();
        List<ArticleInformation> articleInTag = new ArrayList<>();

        for (int m = 0; m < tagList.size(); m++) {
            tagArticle.addAll(tagMapper.ArticleInTag(tagList.get(m)));
            System.out.println(tagArticle.size());
        }
        for (int i = 0; i < tagArticle.size(); i++) {
            articleInTag.add(new ArticleInformation(tagArticle.get(i).getArticleId(), tagArticle.get(i).getUserId(), tagArticle.get(i).getArticleHeading(), tagArticle.get(i).getArticleContent(), tagArticle.get(i).getPublishTime(), tagArticle.get(i).getTagId(), articleMapper.LikeNum(tagArticle.get(i).getArticleId()), articleMapper.ArticleComment(tagArticle.get(i).getArticleId()).size()));
        }

        Collections.reverse(articleInTag);
//        map.put("tagArticle",tagArticle);
        map.put("articleInTag", articleInTag);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "tag页面")
    @GetMapping("tagPage")
    public Map<String, Object> tagPage(int tagId) {
        Map<String, Object> map = new HashMap<>();

        String tagName = tagMapper.searchTag(tagId).getTagName();
        List<Article> tagArticle = tagMapper.ArticleInTag(tagId);
        List<ArticleInformation> articleInTag = new ArrayList<>();

        for (int i = 0; i < tagArticle.size(); i++) {
            articleInTag.add(new ArticleInformation(tagArticle.get(i).getArticleId(), tagArticle.get(i).getUserId(), tagArticle.get(i).getArticleHeading(), tagArticle.get(i).getArticleContent(), tagArticle.get(i).getPublishTime(), tagArticle.get(i).getTagId(), articleMapper.LikeNum(tagArticle.get(i).getArticleId()), articleMapper.ArticleComment(tagArticle.get(i).getArticleId()).size()));
        }

        Collections.sort(articleInTag, new Comparator<ArticleInformation>() {
            @Override
            public int compare(ArticleInformation o1, ArticleInformation o2) {
                return o2.getArticleLikes() - o1.getArticleLikes();
            }
        });
//        map.put("tagArticle",tagArticle);
        map.put("tagId",tagId);
        map.put("tagName",tagName);
        map.put("tagInArticle", articleInTag);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "tag前十")
    @GetMapping("topTag")
    public Map<String,Object> topTag(){
        Map<String, Object> map = new HashMap<>();

        List<Tag> tagAllList = tagMapper.allTag();

        Collections.sort(tagAllList, new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                return o2.getArticleNum() - o1.getArticleNum();
            }
        });
        map.put("tagAllList",tagAllList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

}