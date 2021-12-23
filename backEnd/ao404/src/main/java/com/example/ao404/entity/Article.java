package com.example.ao404.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@ApiModel(value = "article对象")
public class Article {
    private int articleId;
    private int userId;
    private String articleHeading;
    private String articleContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp publishTime;

//    private int tagId;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getArticleHeading() {
        return articleHeading;
    }

    public void setArticleHeading(String articleHeading) {
        this.articleHeading = articleHeading;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

//    public int getTagId() {
//        return tagId;
//    }
//
//    public void setTagId(int tagId) {
//        this.tagId = tagId;
//    }

    public Article(int articleId, int userId, String articleHeading, String articleContent, Timestamp publishTime) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleHeading = articleHeading;
        this.articleContent = articleContent;
        this.publishTime = publishTime;
//        this.tagId = tagId;
    }

    public Article(int userId, String articleHeading, String articleContent) {
        this.userId = userId;
        this.articleHeading = articleHeading;
        this.articleContent = articleContent;
    }

    public Article() {
    }
}
