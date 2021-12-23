package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="tag对象")
public class Tag {

    private int tagId;
    private String tagName;
    private int articleNum;



    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
        this.articleNum = 0;
    }

    public Tag(int tagId, String tagName, int articleNum) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.articleNum = articleNum;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }
}
