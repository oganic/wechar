package com.example.wxrobot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "taobao")
public class TaoBaoConfig {

    /**
     * 根据商品名字查询
     */
    private String gw;

    /**
     * 淘宝链接转优惠券
     */
    private String ecoTaoBao;

    /**
     * appkey
     */
    private String appKey;

    /**
     * APPSECRET
     */
    private String appSecret;
}
