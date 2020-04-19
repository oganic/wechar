package com.example.wxrobot.common.model;

import com.example.wxrobot.common.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class TextMessage extends MessageType {

    @XStreamAlias("Content")
    private String content="欢迎关注此公众号，此公众号有三个功能\n" +
            "1.查找百度网盘资源：百度云+空格+搜索的内容。示例：百度云 周杰伦\n" +
            "2.淘宝拿优惠券：去手机淘宝复制宝贝链接粘贴到此处"+
            "3.机器人聊天\n" ;

    public TextMessage(String newmessage, Map<String, String> requestmap) {
        super(requestmap);
        this.content=newmessage;
    }

    public TextMessage(Map<String, String> requestmap) {
        super(requestmap);
    }
}
