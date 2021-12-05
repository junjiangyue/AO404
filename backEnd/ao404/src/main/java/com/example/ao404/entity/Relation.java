package com.example.ao404.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="relation对象")
public class Relation {
    private int fanId;
    private int followingId;
}
