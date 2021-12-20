package com.example.ao404.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "test")
@RestController
public class HelloController {

    @ApiOperation(value="测试用")
    @GetMapping(value="/hello")
    public String hello(String target){
        return "hello!!!!!";
    }
}
