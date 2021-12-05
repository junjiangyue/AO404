package com.example.ao404.mapper;

import com.example.ao404.entity.User;
import com.example.ao404.entity.UserInformation;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {

    @Select("select following_id,user_name,'0','0' from relation natural join user where fan_id=#{userId} and user_id = following_id")
    List<UserInformation> following(@Param("userId") int userId);

    @Select("select fan_id,user_name,'0','0' from relation natural join user where following_id=#{userId} and user_id = fan_id")
    List<UserInformation> fans(@Param("userId") int userId );
}
