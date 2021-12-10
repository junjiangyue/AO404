package com.example.ao404.controller;

import com.example.ao404.mapper.FeedbackMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.example.ao404.mapper.FeedbackMapper.helper;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @ApiOperation(value = "反馈")
    @PostMapping("feedback")
    public Map<String, Object> giveFeedback(int userId, String feedbackContent) {

        Map<String, Object> map = new HashMap<>();

        int result = feedbackMapper.feedback(userId,feedbackContent);
        if(result>0)
        {
            map.put("msg","Success");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();

        }
        else
        {
            map.put("msg","Failure");
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();

        }
    }
}
