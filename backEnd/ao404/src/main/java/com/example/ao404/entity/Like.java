package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="like对象")
public class Like {
    private int userId;
    private int articleId;

    public Like(int userId, int articleId) {
        this.userId = userId;
        this.articleId = articleId;
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

    public Like() {
    }
}
