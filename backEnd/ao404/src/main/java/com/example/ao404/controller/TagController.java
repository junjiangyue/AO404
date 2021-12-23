package com.example.ao404.controller;

import com.example.ao404.entity.*;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.TagMapper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "关注的tag列表")
    @GetMapping("myLikeTag")
    public Map<String, Object> myLikeTag(HttpServletRequest request) {

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        List<Integer> tagList = tagMapper.myLikeTag(userId);

        List<Article> tagArticle = new ArrayList<>();
        List<ArticleInformation> articleInTag = new ArrayList<>();

        for (int m = 0; m < tagList.size(); m++) {
            tagArticle.addAll(tagMapper.ArticleInTag(tagList.get(m)));
            System.out.println(tagArticle.size());
        }
        for (int i = 0; i < tagArticle.size(); i++) {
            List<Tag> tagList1 = tagMapper.articleTagList(tagArticle.get(i).getArticleId());
            User user = userMapper.loginUser(tagArticle.get(i).getUserId());

            articleInTag.add(new ArticleInformation(tagArticle.get(i).getArticleId(), tagArticle.get(i).getUserId(), user.getUserName(),user.getUserAvatar(),tagArticle.get(i).getArticleHeading(), tagArticle.get(i).getArticleContent(), tagArticle.get(i).getPublishTime(), tagList1, articleMapper.LikeNum(tagArticle.get(i).getArticleId()), articleMapper.ArticleComment(tagArticle.get(i).getArticleId()).size()));
        }
        for (int i = 0; i < articleInTag.size() - 1; i++) {
            for (int j = articleInTag.size() - 1; j > i; j--) {
                if (articleInTag.get(j).equals(articleInTag.get(i))) {
                    articleInTag.remove(j);
                }
            }
        }

        Collections.sort(articleInTag, new Comparator<ArticleInformation>() {
            @Override
            public int compare(ArticleInformation o1, ArticleInformation o2) {
                return o2.getArticleId() - o1.getArticleId();
            }
        });
//        Collections.reverse(articleInTag);
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
            List<Tag> tagList1 = tagMapper.articleTagList(tagArticle.get(i).getArticleId());
            User user = userMapper.loginUser(tagArticle.get(i).getUserId());

            articleInTag.add(new ArticleInformation(tagArticle.get(i).getArticleId(), tagArticle.get(i).getUserId(),user.getUserName(),user.getUserAvatar(), tagArticle.get(i).getArticleHeading(), tagArticle.get(i).getArticleContent(), tagArticle.get(i).getPublishTime(), tagList1, articleMapper.LikeNum(tagArticle.get(i).getArticleId()), articleMapper.ArticleComment(tagArticle.get(i).getArticleId()).size()));
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

    @ApiOperation("文章加tag")
    @PostMapping("addTag")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> addTag(String tagName){
        Map<String, Object> map = new HashMap<>();

        Integer result = tagMapper.searchTagName(tagName);
        if(result!=null)
        {
            map.put("msg","标签存在");
            map.put("tagId",result);
            map.put("tagName",tagName);
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();

        }
        else {
            Tag tag = new Tag(tagName);
            tagMapper.addTag(tag);
            map.put("msg","标签不存在，新增该标签");
            map.put("tagId",tag.getTagId());
            map.put("tagName",tagName);
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }

    }

    @ApiOperation("获取用户订阅的所有tag")
    @GetMapping("getMyLikeTag")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> getMyLikeTag(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        List<Integer> temp = tagMapper.myLikeTag(userId);
        List<Tag> myLikeTag = new ArrayList<>();
        for (int i=0;i<temp.size();i++)
        {
            String tagName = tagMapper.searchTag(temp.get(i)).getTagName();
            Tag tag = new Tag();
            tag.setTagId(temp.get(i));
            tag.setTagName(tagName);
            tag.setArticleNum(tagMapper.ArticleInTag(temp.get(i)).size());
            myLikeTag.add(tag);
        }
        map.put("list",myLikeTag);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation("订阅tag")
    @PostMapping("likeTag")
    public Map<String,Object> likeTag(HttpServletRequest request,int tagId) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        List<Integer> myliketag = tagMapper.myLikeTag(userId);
        for(int i=0;i<myliketag.size();i++)
        {
            if(myliketag.get(i).equals(tagId))
            {
                map.put("msg","已经订阅该tag");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        }


        tagMapper.likeTag(tagId,userId);


        map.put("msg","订阅成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation("取消订阅tag")
    @PostMapping("unlikeTag")
    public Map<String,Object> unlikeTag(HttpServletRequest request,int tagId) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        tagMapper.unlikeTag(tagId,userId);
        map.put("msg","取消订阅成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

}