package com.example.wxrobot.service;


import com.example.wxrobot.common.MessageType;

import java.util.Map;

/**
 * 处理消息类
 *
 * @author ouguiming
 * @since 2020-04-18
 */
public interface IRootService {
    /**
     * 返回xml
     */
    MessageType textMessage(Map<String,String> requestmap);

    /**
     * 返回xml
     */
    MessageType defMessage(Map<String,String> requestmap);
}
