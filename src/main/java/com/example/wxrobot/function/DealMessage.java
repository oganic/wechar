package com.example.wxrobot.function;

import com.example.wxrobot.common.MessageType;
import com.example.wxrobot.common.model.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class DealMessage {

    private static final String news="查找百度网盘资源例如\n百度云 叶问4";

    @Autowired
    private ParseHtml parseHtml;

    public MessageType textMessage(Map<String, String> requestmap) {

        String oldmessages=requestmap.get("Content");
        System.out.println(oldmessages);
        MessageType message=null;
        System.out.println(oldmessages.indexOf(" "));
        switch (oldmessages.substring(0,oldmessages.indexOf(" ")==-1?0:oldmessages.indexOf(" "))){
            case "百度云":
                String last=parseHtml.pares(oldmessages.substring(4,oldmessages.length()));
                message=new TextMessage(last,requestmap);
                break;
            case "你想干嘛":
                message=new TextMessage("打你",requestmap);
                break;
            default:
                message=new TextMessage(news,requestmap);
                break;
        }
        return  message;
    }
}
