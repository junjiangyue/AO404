package com.example.ao404.service.impl;

import com.example.ao404.mapper.PictureMapper;
import com.example.ao404.mapper.UserMapper;
import com.example.ao404.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER= LoggerFactory.getLogger(FileService.class);

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public String uploadFile(int typeId,MultipartFile file,String fileType)throws Exception{
        String os = System.getProperty("os.name");
        File fileRealPath;   //文件存放地址
        if (os.toLowerCase().startsWith("win")) {  //windows系统
            String path = System.getProperty("user.dir");  //获取项目相对路径

            fileRealPath = new File(path+"/src//main/resources/file");
        }else{//linux系统
            //获取根目录
            //如果是在本地windows环境下，目录为项目的target\classes下
            //如果是linux环境下，目录为jar包同级目录
            File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!rootPath.exists()){
                rootPath = new File("");
            }
            fileRealPath = new File(rootPath.getAbsolutePath()+"/file/");
        }
        //判断文件夹是否存在
        if(!fileRealPath.exists()){
            //不存在，创建
            fileRealPath.mkdirs();
        }
//        获取文件名称
        String Name = file.getOriginalFilename();
        String prefix = Name.substring(Name.lastIndexOf(".")+1);

        int num = getCurNumber()+1;
        //文件名命名
        String fileName = String.valueOf(typeId)+ String.valueOf(num) +"." +prefix;

        //创建文件存放地址
        File resultPath = new File(fileRealPath+"/"+fileName);
        if (resultPath.exists()){
            LOGGER.warn("文件已经存在！");
            return "false！";
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        file.transferTo(resultPath);
//        fileMapper.insert(title, sdf.format(new Date()), imageName, fileName);

        pictureMapper.fileInsert(typeId,fileName,fileType);

        if(fileType=="avatar")
        {
            FileInputStream inputStream = new FileInputStream(resultPath);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());

            pictureMapper.delAvatar(typeId);
            pictureMapper.fileInsert(typeId,fileName,"avatar");
            userMapper.updateAvatar(typeId,bytes);
        }
        System.out.println("absolutePath:"+fileRealPath.getCanonicalPath());
        System.out.println("resultPath:"+resultPath.getCanonicalPath());
        return "true！";


    }

//    @Override
//    public List<FileInformation> getAllFile() {
//        return fileMapper.findAllFile() ;
//    }
//
//
//    private final Path filePath;
//
//    @Autowired
//    public FileServiceImpl(FileProperties fileProperties) {
//        filePath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
//    }
//
//
//    @Override
//    public Resource loadFileAsResource(String fileName){
//        Path path = filePath.resolve(fileName).normalize();
//        System.out.println(path+"---------------------------------------------------");
//        try {
//            UrlResource resource = new UrlResource(path.toUri());
//            if (resource.exists()) {
//                return resource;
//            }
//            throw new FileException("file " + fileName + " not found");
//        } catch (MalformedURLException e) {
//            throw new FileException("file " + fileName + " not found", e);
//        }
//    }
//
    @Override
    public String getAvatar(int userId) {
        return pictureMapper.getAvatar(userId);
    }

    @Override
    public List<String> getArticleImgName(int articleId) {
        return pictureMapper.getArticleImgName(articleId);
    }
//
//    @Override
//    public List<String> getSourceFile(int sourceId) {
//        return fileMapper.getSourceFileList(sourceId);
//    }
//
    @Override
    public int getCurNumber() {
        return pictureMapper.getCurNumber();
    }

}

