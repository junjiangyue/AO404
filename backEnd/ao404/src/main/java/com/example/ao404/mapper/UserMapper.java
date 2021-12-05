package com.example.ao404.mapper;

import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    @Select("select * from user where user_id=#{userId}")
    User loginUser(@Param("userId") int userId);


    @Insert("insert into user(user_name,user_password,user_email)"+
            "values(#{userName},#{userPassword},#{userEmail})")
    int register(@Param("userName") String userName,@Param("userPassword") String userPassword,@Param("userEmail") String userEmail);


    @Update("update user set user_avatar = #{userAvatar} where user_id = #{userId} ")
    int updateAvatar(@Param("userId") int userId,@Param("userAvatar") byte[] userAvatar);

    @Update("update user set user_password = #{newPassword} where user_id = #{userId}")
    int updatePassword(@Param("userId") int userId,@Param("newPassword") String newPassword);
}
