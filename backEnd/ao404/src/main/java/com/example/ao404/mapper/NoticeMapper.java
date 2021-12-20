package com.example.ao404.mapper;

import com.example.ao404.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticeMapper {

    @Select("select * from notice where user_id = #{userId}")
    List<Notice> getNotice(@Param("userId") int userId);
}
