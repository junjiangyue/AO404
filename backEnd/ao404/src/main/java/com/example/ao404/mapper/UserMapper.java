package com.example.ao404.mapper;

import com.example.ao404.entity.Admin;
import com.example.ao404.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    @Select("select * from user where user_id=#{userId}")
    User loginUser(@Param("userId") int userId);

  @Select("select * from admin where admin_id=#{adminId}")
  Admin loginAdmin(@Param("adminId") int adminId);

    @Select("select * from user")
    List<User> userList();


    @Insert("insert into user(user_name,user_password,user_email)"+
            "values(#{user.userName},#{user.userPassword},#{user.userEmail})")
    @Options(useGeneratedKeys = true,keyProperty = "user.userId")
    int register(@Param("user") User user);

//  int register(@Param("userName") String userName,@Param("userPassword") String userPassword,@Param("userEmail") String userEmail);


    @Update("update user set user_avatar = #{userAvatar} where user_id = #{userId} ")
    int updateAvatar(@Param("userId") int userId,@Param("userAvatar") byte[] userAvatar);

    @Update("update user set user_password = #{newPassword} where user_id = #{userId}")
    int updatePassword(@Param("userId") int userId,@Param("newPassword") String newPassword);

    @Select("SELECT * FROM user WHERE user_name LIKE CONCAT(CONCAT('%', #{userName}), '%');")
    List<User> resultUser(@Param("userName") String userName);

    @Delete("delete from user where user_id = #{userId}")
    void deleteUser(@Param("userId") int userId);
}
