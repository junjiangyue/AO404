package com.example.ao404.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@ApiModel(value = "comment对象")
public class Comment {
    private int commentId;
    private int userId;
    private int articleId;
    private String commentInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp commentTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Comment(int commentId, int userId, int articleId, String commentInfo, Timestamp commentTime) {
        this.commentId = commentId;
        this.userId = userId;
        this.articleId = articleId;
        this.commentInfo = commentInfo;
        this.commentTime = commentTime;
    }
}
