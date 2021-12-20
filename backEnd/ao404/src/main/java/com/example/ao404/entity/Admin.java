package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "admin对象")
public class Admin {

    private int adminId;
    private String adminPassword;

    public Admin(int adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public Admin() {
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
