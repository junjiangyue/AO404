package com.example.ao404.controller;

import com.example.ao404.entity.FileProperties;
import com.example.ao404.entity.Picture;
import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("picture")
public class PictureController {

    private static final Logger LOGGER= LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private FileService fileService;


    @Autowired
    private FileProperties fileProperties;


    @ApiOperation(value="上传文章图片")
    @ResponseBody
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("articleId") int articleId,
                             @RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
            return "上传文件为空！";
        }
        try {
            String result = fileService.uploadFile(articleId,file,"article");
            LOGGER.info(result);
            return "上传文件成功！";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            return "文件上传失败！";
        }
    }


    @ApiOperation(value="上传头像")
    @ResponseBody
    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("userId") int userId,
                             @RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
            return "上传文件为空！";
        }
        try {
            String result = fileService.uploadFile(userId,file,"avatar");
            LOGGER.info(result);

            return "上传文件成功！";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            return "文件上传失败！";
        }
    }


    @ApiOperation(value="获取头像")
    @PostMapping("getAvatar")
    @ResponseBody
    public  Map<String,Object> getAvatarImage(int userId)
    {

        RestControllerHelper helper = new RestControllerHelper();

        String avatarName =fileService.getAvatar(userId);

        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
        Path path = avatarPath.resolve(avatarName).normalize();


        File file = new File(path.toString());
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            helper.setMsg("Success");
            helper.setData(bytes);
            return helper.toJsonMap();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @ApiOperation(value="获取文章图片")
    @PostMapping("getArticleImg")
    @ResponseBody
    public Map<String, Object> getArticleImage(int articleId)
    {
        Map<String, Object> map = new HashMap<>();
        RestControllerHelper helper = new RestControllerHelper();
        List<String> articleImg = fileService.getArticleImgName(articleId);

        Path imgPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();

        List<byte[]> imgList = new ArrayList<>();
        for(int i=0;i<articleImg.size();i++)
        {
            Path path = imgPath.resolve(articleImg.get(i)).normalize();

            File file = new File(path.toString());
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
//                return bytes;
                imgList.add(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        map.put("imgList",imgList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
