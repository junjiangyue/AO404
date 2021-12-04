package com.example.ao404.mapper;

import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where user_id=#{userId}")
    User loginUser(@Param("userId") int userId);

}
