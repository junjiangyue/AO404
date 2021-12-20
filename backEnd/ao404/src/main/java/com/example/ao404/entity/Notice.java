package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "notice对象")
public class Notice {

    private int userId;
    private String noticeContent;
    private String noticeTitle;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Notice(int userId, String noticeContent, String noticeTitle) {
        this.userId = userId;
        this.noticeContent = noticeContent;
        this.noticeTitle = noticeTitle;
    }

    public Notice() {
    }
}
