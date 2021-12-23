package com.example.ao404.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PictureMapper {

    @Insert("insert into picture (type_id,file_name,file_type) " +
            "values(#{articleId},#{fileName},#{fileType})")
    public int fileInsert(@Param("articleId") int articleId,
                          @Param("fileName") String fileName,
                          @Param("fileType") String fileType
                         );



//    @Select("SELECT * from files")
//    public List<FileInformation> findAllFile();
//
    @Select("select file_name from picture where type_id =#{articleId} and file_type = 'article' ")
    public List<String> getArticleImgName(@Param("articleId") int articleId);


    @Select("select file_name from picture where type_id =#{userId} and file_type = 'avatar' ")
    public String getAvatar(@Param("userId") int userId);

    @Delete("delete from picture where type_id =#{userId} and file_type = 'avatar' ")
    public int delAvatar(@Param("userId") int userId);

    //
//    @Select("select file_name from files where source_id =${sourceId}")
//    List<String> getSourceFileList(@Param("sourceId") int sourceId);
//
    @Select("select max(picture_id) from picture")
    int getCurNumber();


}
