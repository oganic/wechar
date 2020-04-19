package com.example.wxrobot.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorEnums {

    /**
     * 没有资源
     */
    NO_RESOURCE(4, "没有资源"),


    /**
     * 图铃解析json失败
     */
    TULING_JSONERR(5, "图铃解析json失败"),


    /**
     * 微信服务端出错
     */
    WECHAT_ERROR(404, "微信服务端出错"),

    /**
     * 不是正确的链接噢
     */
    LINK_ERROR(403, "不是正确的手机淘宝链接"),

    /**
     * 淘宝服务出错
     */
    TAOBAO_ERROR(101, "无此商品或淘宝服务出错");

    /**
     *api响应码
     */
    private Integer code;

    /**
     *API信息
     */
    private String desc;
}
