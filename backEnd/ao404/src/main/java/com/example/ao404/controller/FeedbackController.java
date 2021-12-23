package com.example.ao404.controller;

import com.example.ao404.entity.*;
import com.example.ao404.mapper.FeedbackMapper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ao404.mapper.FeedbackMapper.helper;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "反馈")
    @PostMapping("feedback")
    public Map<String, Object> giveFeedback(HttpServletRequest request, String feedbackContent) {

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        int result = feedbackMapper.feedback(userId, feedbackContent);
        if (result > 0) {
            map.put("msg", "Success");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();

        } else {
            map.put("msg", "Failure");
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();

        }
    }

    @ApiOperation(value = "获取反馈")
    @GetMapping("getfeedback")
    public Map<String, Object> getfeedback() {
        Map<String, Object> map = new HashMap<>();

        List<Feedback> feedbackList = feedbackMapper.getFeedback();
        List<FeedbackInfo> FeedbackInfoList = new ArrayList<>();

        for(int i=0;i<feedbackList.size();i++)
        {
            User user = userMapper.loginUser(feedbackList.get(i).getUserId());
            FeedbackInfoList.add(new FeedbackInfo(feedbackList.get(i).getFeedbackId(),feedbackList.get(i).getUserId(),user.getUserName(),feedbackList.get(i).getFeedbackTime(),feedbackList.get(i).getFeedbackContent(),feedbackList.get(i).getIsAnswered()));
        }

        map.put("msg", "Success");
        map.put("feedbackList",FeedbackInfoList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "获取某用户的所有反馈")
    @GetMapping("getUserfeedback")
    public Map<String, Object> getUserfeedback(HttpServletRequest request) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        Map<String, Object> map = new HashMap<>();

        List<Feedback> feedbackList = feedbackMapper.getUserFeedback(userId);
        List<FeedbackInfo> FeedbackInfoList = new ArrayList<>();

        for(int i=0;i<feedbackList.size();i++)
        {
            User user = userMapper.loginUser(feedbackList.get(i).getUserId());
            FeedbackInfoList.add(new FeedbackInfo(feedbackList.get(i).getFeedbackId(),feedbackList.get(i).getUserId(),user.getUserName(),feedbackList.get(i).getFeedbackTime(),feedbackList.get(i).getFeedbackContent(),feedbackList.get(i).getIsAnswered()));
        }

        map.put("msg", "Success");
        map.put("feedbackList",FeedbackInfoList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "处理反馈")
    @PostMapping("answerfeedback")
    public Map<String, Object> answerfeedback(int feedbackId,String message,String messageHead) {
        Map<String, Object> map = new HashMap<>();

        int result =feedbackMapper.answerfeedback(feedbackId,message,messageHead);
        feedbackMapper.answerfeedback1(feedbackId);
        if (result > 0) {
            map.put("msg", "处理成功");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();

        } else {
            map.put("msg", "处理失败");
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();

        }

    }


    @ApiOperation(value = "获取管理员的处理结果")
    @GetMapping("getanswer")
    public Map<String, Object> getanswer(int feedbackId) {
        Map<String, Object> map = new HashMap<>();


        Answer answer = feedbackMapper.getanswer(feedbackId);

        map.put("msg", "Success");
        map.put("answer",answer);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }



}