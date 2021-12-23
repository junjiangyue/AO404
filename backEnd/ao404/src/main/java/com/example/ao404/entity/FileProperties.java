package com.example.ao404.entity;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@ConfigurationProperties(prefix = "file")
@Component
@Data
public class FileProperties {
//    private String docDir= "../file/";

    private String docDir;
//    private String docDir= "src/main/resources/file/";
}
