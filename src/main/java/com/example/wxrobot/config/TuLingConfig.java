package com.example.wxrobot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tuling")
public class TuLingConfig {

    /**
     * 请求的http地址
     */
    private String httpUrl;

    /**
     * 机器人的key当一个用完可以切换
     */
    private String[] apiKey ;
}
