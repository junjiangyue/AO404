package com.example.ao404.mapper;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.Tag;
import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    @Select("select * from tag where tag_id=#{tagId}")
    Tag searchTag(@Param("tagId") int tagId);

    @Select("select tag_id from likeTag where user_id=#{userId}")
    List<Integer> myLikeTag(@Param("userId") int userId);

    @Select("select * from article where tag_id = #{tagId}")
    List<Article> ArticleInTag(@Param("tagId") int tagId);

    @Select("select tag_id,tag_name,count(article_id) from tag natural join article group by tag_id")
    List<Tag> allTag();

    @Select("select tag_id,tag_name,count(article_id) from tag natural join article where tag_name like concat(concat('%', #{tagName}), '%') group by tag_id ;")
    List<Tag> resultTag(@Param("tagName") String tagName);
}
