package com.example.ao404;

import com.example.ao404.entity.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@MapperScan("com.example.ao404.mapper")
@SpringBootApplication
@EnableConfigurationProperties({FileProperties.class})
public class Ao404Application {

    public static void main(String[] args) {
        SpringApplication.run(Ao404Application.class, args);
    }

}
