package com.example.ao404.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@ApiModel(value="feedback对象")
public class Feedback {

    private int feedbackId;
    private int userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp feedbackTime;
    private String feedbackContent;

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Timestamp feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Feedback(int feedbackId, int userId, Timestamp feedbackTime, String feedbackContent) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedbackTime = feedbackTime;
        this.feedbackContent = feedbackContent;
    }

    public Feedback() {
    }
}
