package com.example.ao404.entity;

import lombok.Data;

@Data
public class UserInformation {

    private int userId;
    private String userName;
    private int isMyFan;
    private int isMyFollow;

    public UserInformation(int userId, String userName, int isMyFan, int isMyFollow) {
        this.userId = userId;
        this.userName = userName;
        this.isMyFan = 0;
        this.isMyFollow = 0;
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

    public int getIsMyFan() {
        return isMyFan;
    }

    public void setIsMyFan(int isMyFan) {
        this.isMyFan = isMyFan;
    }

    public int getIsMyFollow() {
        return isMyFollow;
    }

    public void setIsMyFollow(int isMyFollow) {
        this.isMyFollow = isMyFollow;
    }
}
