package com.example.ao404.mapper;


import com.example.ao404.entity.Article;
import com.example.ao404.entity.Tag;
import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Repository
public interface TagMapper {

    @Select("select * from tag where tag_id=#{tagId}")
    Tag searchTag(@Param("tagId") int tagId);

    @Select("select tag_id from tag where tag_name=#{tagName}")
    Integer searchTagName(@Param("tagName") String tagName);

    @Select("select tag_id from likeTag where user_id=#{userId}")
    List<Integer> myLikeTag(@Param("userId") int userId);

//    @Select("select * from article where tag_id = #{tagId}")
//    List<Article> ArticleInTag(@Param("tagId") int tagId);
    @Select("select * from article natural join art_tag where tag_id = #{tagId}")
    List<Article> ArticleInTag(@Param("tagId") int tagId);

//    @Select("select tag_id,tag_name,count(article_id) from tag natural join article group by tag_id")
//    List<Tag> allTag();
    @Select("select tag_id,tag_name,count(article_id) as article_num from tag natural join art_tag group by tag_id")
    List<Tag> allTag();

//    @Select("select tag_id,tag_name,count(article_id) from tag natural join article where tag_name like concat(concat('%', #{tagName}), '%') group by tag_id ;")
//    List<Tag> resultTag(@Param("tagName") String tagName);
    @Select("select tag_id,tag_name,count(article_id) as article_num from tag natural join art_tag where tag_name like concat(concat('%', #{tagName}), '%') group by tag_id")
    List<Tag> resultTag(@Param("tagName") String tagName);

    @Insert("insert into tag(tag_name)"+
            "values(#{tag.tagName})")
    @Options(useGeneratedKeys = true,keyProperty = "tag.tagId")
    int addTag(@Param("tag") Tag tag);

    @Insert("insert into art_tag(tag_id,article_id) values(#{tagId},#{articleId})")
    int publishArtTag(@Param("tagId") int tagId,@Param("articleId") int articleId);
//    @Select("select article_id from art_tag where tag_id = #{tagId}")
//    List<Integer> articleListInTag(@Param("tagId") int tagId);

    @Select("select tag_id,tag_name,count(article_id) as article_num from art_tag natural join tag where article_id = #{articleId} group by tag_id")
    List<Tag> articleTagList(@Param("articleId") int articleId);

    @Insert("insert into likeTag(tag_id,user_id) values(#{tagId},#{userId})")
    int likeTag(@Param("tagId") int tagId,@Param("userId") int userId);

    @Delete("delete from likeTag where user_id = #{userId} and tag_id = #{tagId}")
    int unlikeTag(@Param("tagId") int tagId,@Param("userId") int userId);

}
