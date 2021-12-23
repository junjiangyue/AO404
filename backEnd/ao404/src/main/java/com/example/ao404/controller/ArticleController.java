package com.example.ao404.controller;

import com.example.ao404.entity.*;
import com.example.ao404.mapper.ArticleMapper;
import com.example.ao404.mapper.CommentMapper;
import com.example.ao404.mapper.TagMapper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private CommentMapper commentMapper;

    RestControllerHelper helper = new RestControllerHelper();


    @ApiOperation(value = "发布帖子")
    @PostMapping("publishArticle")
    public Map<String, Object> publishArticle( @RequestBody Article_tag article_tag, String articleHeading,  String articleContent,HttpServletRequest request){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();

//        if(StringUtils.isEmpty(userId+"")||StringUtils.isEmpty(articleContent)||StringUtils.isEmpty(articleHeading))
//        {
//            map.put("msg","关键数据缺失");
//
//            return map;
//        }

        if(userMapper.loginUser(userId)==null)
        {
            map.put("msg","用户id不存在");
            return map;
        }
//        for(int i=0;i<article_tag.getTagList().size();i++)
//        {
//            Integer result = tagMapper.searchTagName(article_tag.getTagList().get(i));
//            if(result==null)
//            {
//                Tag tag = new Tag(article_tag.getTagList().get(i));
//                tagMapper.addTag(tag);
//            }
//        }
//        if(tagMapper.searchTag(tagId)==null)
//        {
//            map.put("msg","tag不存在");
//            return map;
//
//        }
        //修改！！！
        Article article = new Article(userId,articleHeading,articleContent);
        int result = articleMapper.publishArticle(article);

        if(result>0)
        {
            for(int i=0;i<article_tag.getTagList().size();i++) {
                Integer result1 = tagMapper.searchTagName(article_tag.getTagList().get(i));
                if (result1 == null) {
                    Tag tag = new Tag(article_tag.getTagList().get(i));
                    tagMapper.addTag(tag);
                    tagMapper.publishArtTag(tag.getTagId(),article.getArticleId());
                }
                else{
                    tagMapper.publishArtTag(result1,article.getArticleId());
                }
            }
                    map.put("msg","发布成功");
            map.put("articleId",article.getArticleId());

            return map;
        }
        else {
            map.put("msg","发布失败");

            return map;
        }


    }


    @ApiOperation(value = "首页的帖子")
    @GetMapping("homeArticle")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> homeArticle(HttpServletRequest request){

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();

        List<Article> homeList = articleMapper.getHomeArticle(userId);

        List<ArticleInfoForUser> homeList1 = new ArrayList<>();

        for(int i=0;i<homeList.size();i++)
        {
            List<Tag> tagList = tagMapper.articleTagList(homeList.get(i).getArticleId());
            User user = userMapper.loginUser(homeList.get(i).getUserId());
            int isLiked = articleMapper.isLiked(homeList.get(i).getArticleId(),userId);
            homeList1.add(new ArticleInfoForUser(homeList.get(i).getArticleId(),homeList.get(i).getUserId(),user.getUserName(),user.getUserAvatar(),homeList.get(i).getArticleHeading(),homeList.get(i).getArticleContent(),homeList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(homeList.get(i).getArticleId()),articleMapper.ArticleComment(homeList.get(i).getArticleId()).size(),isLiked));
        }

        Collections.reverse(homeList1);
        map.put("homeList",homeList1);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "我发布的所有帖子")
    @GetMapping("myArticle")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> myArticle(HttpServletRequest request){

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();

        List<Article> articleList = articleMapper.getUserArticle(userId);

        List<ArticleInfoForUser> userArtList = new ArrayList<>();

        for(int i=0;i<articleList.size();i++)
        {
            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
            User user = userMapper.loginUser(articleList.get(i).getUserId());
            int isLiked = articleMapper.isLiked(articleList.get(i).getArticleId(),userId);
            userArtList.add(new ArticleInfoForUser(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),user.getUserName(),user.getUserAvatar(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size(),isLiked));

//            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
//            userArtList.add(new ArticleInformation(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size()));
        }

        Collections.reverse(userArtList);
        map.put("userArtList",userArtList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "某个用户发布的所有帖子")
    @GetMapping("userArticle")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> userArticle(HttpServletRequest request,int userId){

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int myId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();

        List<Article> articleList = articleMapper.getUserArticle(userId);

        List<ArticleInfoForUser> userArtList = new ArrayList<>();

        for(int i=0;i<articleList.size();i++)
        {
            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
            User user = userMapper.loginUser(articleList.get(i).getUserId());
            int isLiked = articleMapper.isLiked(articleList.get(i).getArticleId(),myId);
            userArtList.add(new ArticleInfoForUser(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),user.getUserName(),user.getUserAvatar(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size(),isLiked));

//            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
//            userArtList.add(new ArticleInformation(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size()));
        }

        Collections.reverse(userArtList);
        map.put("userArtList",userArtList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "帖子的评论")
    @GetMapping("articleComment")
    public Map<String,Object> articleComment(int articleId){

        Map<String,Object> map = new HashMap<>();

        List<Comment> commentList = commentMapper.articleComment(articleId);

        map.put("commentList",commentList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }


    @ApiOperation(value = "帖子详情")
    @GetMapping("articleInfo")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String,Object> articleInfo(HttpServletRequest request,int articleId) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int myId = getInfo.getUserId();


        Map<String,Object> map = new HashMap<>();
        Article article = articleMapper.articleInfo(articleId);
        User user = userMapper.loginUser(article.getUserId());
        List<Tag> tagList = tagMapper.articleTagList(article.getArticleId());

        int isLiked = articleMapper.isLiked(article.getArticleId(),myId);

        ArticleInfoForUser articleInfoForUser = new ArticleInfoForUser(article.getArticleId(), article.getUserId(), user.getUserName(), user.getUserAvatar(), article.getArticleHeading(), article.getArticleContent(), article.getPublishTime(), tagList, articleMapper.LikeNum(article.getArticleId()), articleMapper.ArticleComment(article.getArticleId()).size(),isLiked);
        map.put("articleInformation", articleInfoForUser);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

        @ApiOperation(value = "所有帖子")
    @GetMapping("allArticle")
    public Map<String,Object> allArticle() {

        Map<String, Object> map = new HashMap<>();

        List<Article> articleList = articleMapper.articleList();

        List<ArticleInformation> AllArtList = new ArrayList<>();


        List<User> temp = new ArrayList<>();
        int judge=0;
            User user = new User();
        for (int i = 0; i < articleList.size(); i++) {
            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
            for(int j=0;j< temp.size();i++)
            {
                if(temp.get(j).getUserId()==articleList.get(i).getUserId())
                {
                    user = temp.get(j);
                    judge=1;
                    break;
                }
            }
            if(judge==0)
            {
                user = userMapper.loginUser(articleList.get(i).getUserId());
                temp.add(user);
            }
            int likenum = articleMapper.LikeNum(articleList.get(i).getArticleId());
            int commentnum = articleMapper.ArticleComment(articleList.get(i).getArticleId()).size();
            ArticleInformation articleInformation = new ArticleInformation(articleList.get(i).getArticleId(), articleList.get(i).getUserId(), user.getUserName(), user.getUserAvatar(), articleList.get(i).getArticleHeading(), articleList.get(i).getArticleContent(), articleList.get(i).getPublishTime(), tagList, likenum, commentnum);
            AllArtList.add(articleInformation);

//            List<Tag> tagList = tagMapper.articleTagList(articleList.get(i).getArticleId());
//            userArtList.add(new ArticleInformation(articleList.get(i).getArticleId(),articleList.get(i).getUserId(),articleList.get(i).getArticleHeading(),articleList.get(i).getArticleContent(),articleList.get(i).getPublishTime(),tagList,articleMapper.LikeNum(articleList.get(i).getArticleId()),articleMapper.ArticleComment(articleList.get(i).getArticleId()).size()));
        }

        Collections.reverse(AllArtList);
        map.put("AllArtList", AllArtList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();


    }

    @ApiOperation(value = "点赞帖子")
    @PostMapping("likeArticle")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String, Object> likeArticle( HttpServletRequest request ,int articleId) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();
        articleMapper.likeArticle(userId,articleId);
        map.put("msg","点赞成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "取消点赞帖子")
    @PostMapping("unlikeArticle")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String, Object> unlikeArticle( HttpServletRequest request ,int articleId) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();
        articleMapper.unlikeArticle(articleId,userId);
        map.put("msg","取消点赞成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "评论帖子")
    @PostMapping("comment")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String, Object> comment( HttpServletRequest request ,int articleId,String commentInfo) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String,Object> map = new HashMap<>();

        commentMapper.makeComment(userId,articleId,commentInfo);
        map.put("msg","评论成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }



}