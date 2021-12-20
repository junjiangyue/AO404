package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "picture对象")
public class Picture {
    private int pictureId;
    private int typeId;
    private String fileName;
    private String fileType;

    public Picture(int pictureId, int typeId, String fileName, String fileType) {
        this.pictureId = pictureId;
        this.typeId = typeId;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int gettypeId() {
        return typeId;
    }

    public void settypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Picture() {
    }
}
