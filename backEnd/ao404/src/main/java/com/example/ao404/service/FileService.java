package com.example.ao404.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public String uploadFile(int articleId,MultipartFile file,String fileType) throws Exception;

//    public List<FileInformation> getAllFile();
//
//
//    public Resource loadFileAsResource(String fileName);
//
    public List<String> getArticleImgName(int articleId);

    public String getAvatar(int articleId);
//
//    List<String> getSourceFile(int sourceId);
//
    int getCurNumber();
}
