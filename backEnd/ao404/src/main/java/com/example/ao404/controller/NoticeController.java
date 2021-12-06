package com.example.ao404.controller;


import com.example.ao404.entity.Notice;
import com.example.ao404.mapper.NoticeMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ao404.mapper.FeedbackMapper.helper;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeMapper noticeMapper;

    @ApiOperation(value = "获取通知")
    @PostMapping("getNotice")
    public Map<String, Object> getNotice(int userId) {

        Map<String, Object> map = new HashMap<>();

        List<Notice> myNotice = noticeMapper.getNotice(userId);

        map.put("myNotice", myNotice);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }
}