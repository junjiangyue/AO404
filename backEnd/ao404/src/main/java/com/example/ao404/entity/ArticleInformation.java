package com.example.ao404.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleInformation {
    private int articleId;
    private int userId;
    private String articleHeading;
    private String articleContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp publishTime;

    private int tagId;

    private int ArticleLikes;

    private int ArticleComments;

    public ArticleInformation(int articleId, int userId, String articleHeading, String articleContent, Timestamp publishTime, int tagId, int articleLikes, int articleComments) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleHeading = articleHeading;
        this.articleContent = articleContent;
        this.publishTime = publishTime;
        this.tagId = tagId;
        ArticleLikes = articleLikes;
        ArticleComments = articleComments;
    }

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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getArticleLikes() {
        return ArticleLikes;
    }

    public void setArticleLikes(int articleLikes) {
        ArticleLikes = articleLikes;
    }

    public int getArticleComments() {
        return ArticleComments;
    }

    public void setArticleComments(int articleComments) {
        ArticleComments = articleComments;
    }
}
