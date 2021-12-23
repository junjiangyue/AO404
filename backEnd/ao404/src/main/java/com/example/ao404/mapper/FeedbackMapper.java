package com.example.ao404.mapper;

import com.example.ao404.entity.Answer;
import com.example.ao404.entity.Feedback;
import com.example.ao404.entity.RestControllerHelper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackMapper {

    @Insert("insert into feedback(user_id,feedback_content,isAnswered)"+
            "values(#{userId},#{feedbackContent},0)")
    int feedback(@Param("userId") int userId,@Param("feedbackContent") String feedbackContent );

    RestControllerHelper helper = new RestControllerHelper();

    @Select("select * from feedback")
    List<Feedback> getFeedback();

    @Select("select * from feedback where user_id = #{userId}")
    List<Feedback> getUserFeedback(@Param("userId") int userId);

    @Update("update feedback set isAnswered = 1 where feedback_id =#{feedbackId} ")
    int answerfeedback1(@Param("feedbackId") int feedbackId);

    @Insert("insert into answer(feedback_id,message,message_head)"+
            "values(#{feedbackId},#{message},#{messageHead})")
    int answerfeedback(@Param("feedbackId") int feedbackId,@Param("message") String message,@Param("messageHead") String messageHead );

    @Select("select * from answer where feedback_id=#{feedbackId}")
    Answer getanswer(@Param("feedbackId") int feedbackId);


}
