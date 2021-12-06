package com.example.ao404.mapper;

import com.example.ao404.entity.RestControllerHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface FeedbackMapper {

    @Insert("insert into feedback(user_id,feedback_content)"+
            "values(#{userId},#{feedbackContent})")
    int feedback(@Param("userId") int userId,@Param("feedbackContent") String feedbackContent );

    RestControllerHelper helper = new RestControllerHelper();




}
