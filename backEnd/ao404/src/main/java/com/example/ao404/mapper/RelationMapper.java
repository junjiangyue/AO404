package com.example.ao404.mapper;

import com.example.ao404.entity.UserInformation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {

    @Select("select following_id as user_id,user_name,user_avatar,'0','0' from relation natural join user where fan_id=#{userId} and user_id = following_id")
    List<UserInformation> following(@Param("userId") int userId);

    @Select("select fan_id as user_id,user_name,user_avatar,'0','0' from relation natural join user where following_id=#{userId} and user_id = fan_id")
    List<UserInformation> fans(@Param("userId") int userId );

    @Insert("insert into relation(fan_id,following_id) values(#{fanId},#{followingId})")
    int follow(@Param("fanId") int fanId, @Param("followingId") int followingId);

    @Delete("delete from relation where fan_id = #{fanId} and following_id = #{followingId}")
    int unfollow(@Param("fanId") int fanId, @Param("followingId") int followingId);


}
