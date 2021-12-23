package com.example.ao404.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ArticleInfoForUser {

    private int articleId;
    private int userId;
    private String userName;
    private byte[] userAvatar;
    private String articleHeading;
    private String articleContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp publishTime;

//    private int tagId;

    private List<Tag> tagList;

    private int ArticleLikes;

    private int ArticleComments;
    private int isLiked;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
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

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public ArticleInfoForUser(int articleId, int userId, String userName, byte[] userAvatar, String articleHeading, String articleContent, Timestamp publishTime, List<Tag> tagList, int articleLikes, int articleComments, int isLiked) {
        this.articleId = articleId;
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.articleHeading = articleHeading;
        this.articleContent = articleContent;
        this.publishTime = publishTime;
        this.tagList = tagList;
        ArticleLikes = articleLikes;
        ArticleComments = articleComments;
        this.isLiked = isLiked;
    }
}
