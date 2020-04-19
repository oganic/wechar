package com.example.wxrobot.entity.dto;


import lombok.Data;

@Data
public class HttpDTO {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;
}
