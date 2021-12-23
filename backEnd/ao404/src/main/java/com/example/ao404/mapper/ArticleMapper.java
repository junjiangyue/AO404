package com.example.ao404.mapper;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.Comment;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    @Insert("insert into article(user_id,article_heading,article_content)"+
    "values(#{article.userId},#{article.articleHeading},#{article.articleContent})")
    @Options(useGeneratedKeys = true,keyProperty = "article.articleId")
    int publishArticle(@Param("article") Article article);

    @Select("select * from article where user_id = #{userId}")
    List<Article> getUserArticle(@Param("userId") int userId);

    @Select("select * from article")
    List<Article> articleList();

    @Select("select * from article where article_id = #{articleId}")
    Article articleInfo(@Param("articleId") int articleId);



    @Select("select * from article natural join relation where fan_id = #{userId} and user_id = following_id")
    List<Article> getHomeArticle(@Param("userId") int userId);

    @Select("select count(user_id) from likes where article_id=#{articleId}")
    int LikeNum(@Param("articleId") int articleId);

    @Select("select * from comment where article_id=#{articleId}")
    List<Comment> ArticleComment(@Param("articleId") int articleId);

    @Delete("delete from article where article_id = #{articleId}")
    void deleteArticle(@Param("articleId") int articleId);

    @Insert("insert into likes(user_id,article_id)"+
            "values(#{userId},#{articleId})")
    int likeArticle(@Param("userId") int userId,@Param("articleId") int articleId );

    @Delete("delete from likes where article_id = #{articleId} and user_id = #{userId}")
    void unlikeArticle(@Param("articleId") int articleId,@Param("userId") int userId);

    @Select("select count(*) from likes where article_id=#{articleId} and user_id =#{userId}")
    int isLiked(@Param("articleId") int articleId,@Param("userId") int userId);


}
