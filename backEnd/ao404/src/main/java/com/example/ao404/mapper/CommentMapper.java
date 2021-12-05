package com.example.ao404.mapper;

import com.example.ao404.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    @Select("select comment_id,user_id,user_name,user_avatar,article_id,comment_info,comment_time from comment natural join user where article_id = #{articleId} ")
    List<Comment> articleComment(@Param("articleId") int articleId);

}
