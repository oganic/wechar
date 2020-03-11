package com.example.wxrobot.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
public class MessageType {

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private String createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    public MessageType(Map<String, String> requestmap) {
        this.toUserName=requestmap.get("FromUserName");
        this.fromUserName=requestmap.get("ToUserName");
        this.msgType="text";
        this.createTime=System.currentTimeMillis()/1000+"";
    }


}
