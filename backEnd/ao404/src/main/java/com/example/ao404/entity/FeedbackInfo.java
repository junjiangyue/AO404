package com.example.ao404.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class FeedbackInfo {

    private int feedbackId;
    private int userId;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp feedbackTime;
    private String feedbackContent;
    private int isAnswered;

    public FeedbackInfo(int feedbackId, int userId, String userName, Timestamp feedbackTime, String feedbackContent, int isAnswered) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.userName = userName;
        this.feedbackTime = feedbackTime;
        this.feedbackContent = feedbackContent;
        this.isAnswered = isAnswered;
    }
}
