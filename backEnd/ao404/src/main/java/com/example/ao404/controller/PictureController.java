package com.example.ao404.controller;

import com.example.ao404.entity.FileProperties;
import com.example.ao404.entity.RestControllerHelper;
import com.example.ao404.service.FileService;
import com.example.ao404.tools.GetInformationFromRequest;
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
@CrossOrigin
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
    public String uploadAvatar(HttpServletRequest request,
                             @RequestParam("file") MultipartFile file){

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

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
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public  byte[] getAvatarImage(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();

        RestControllerHelper helper = new RestControllerHelper();


//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
//        Path path = avatarPath.resolve(avatarName).normalize();

        String avatarName =fileService.getAvatar(userId);
        if(avatarName == null)
        {
            return null;
        }

        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
        Path path = avatarPath.resolve(avatarName).normalize();


        File file = new File(path.toString());


//        String path = "file:/root/usr/java/file";

//        File file = new File(path.toString());
//        File file = new File(path+"/"+avatarName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());

            return bytes;
//            map.put("bytes",bytes);
//            helper.setMsg("Success");
//            helper.setData(map);
//            return helper.toJsonMap();



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
    public List<byte[]> getArticleImage(int articleId)
    {
        Map<String, Object> map = new HashMap<>();
        RestControllerHelper helper = new RestControllerHelper();
        List<String> articleImg = fileService.getArticleImgName(articleId);

        if(articleImg.size()==0)
        {
            return null;
        }
//        String avatarName =fileService.getAvatar(userId);
        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();


//        File file = new File(path.toString());


        List<byte[]> imgList = new ArrayList<>();
        for(int i=0;i<articleImg.size();i++)
        {
            String ImgName =articleImg.get(i);
            Path path = avatarPath.resolve(ImgName).normalize();

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
        return imgList;


    }
}
