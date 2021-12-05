package com.example.ao404.mapper;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.Comment;
import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    @Insert("insert into article(user_id,article_heading,article_content,tag_id)"+
    "values(#{userId},#{articleHeading},#{articleContent},#{tagId})")
    int publishArticle(@Param("userId") int userId,@Param("articleHeading") String articleHeading,@Param("articleContent") String articleContent,@Param("tagId") int tagId);

    @Select("select * from article where user_id = #{userId}")
    List<Article> getUserArticle(@Param("userId") int userId);


    @Select("select * from article natural join relation where fan_id = #{userId} and user_id = following_id")
    List<Article> getHomeArticle(@Param("userId") int userId);

    @Select("select count(user_id) from likes where article_id=#{articleId}")
    int LikeNum(@Param("articleId") int articleId);

    @Select("select * from comment where article_id=#{articleId}")
    List<Comment> ArticleComment(@Param("articleId") int articleId);


}
