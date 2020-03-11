package com.example.wxrobot.common.model;

import com.example.wxrobot.common.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class TextMessage extends MessageType {

    @XStreamAlias("Content")
    private String content;

    public TextMessage(String newmessage, Map<String, String> requestmap) {
        super(requestmap);
        this.content=newmessage;
    }
}
