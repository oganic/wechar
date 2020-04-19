package com.example.wxrobot.service.impl;

import com.example.wxrobot.common.MessageType;
import com.example.wxrobot.common.model.TextMessage;
import com.example.wxrobot.service.IRootService;
import com.example.wxrobot.util.BaiDuCloudUtils;
import com.example.wxrobot.util.TaoBaoUtils;
import com.example.wxrobot.util.TuLingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class RootServiceImpl implements IRootService {

    @Autowired
    private TuLingUtils tuLingUtils;

    @Autowired
    private BaiDuCloudUtils baiDuCloudUtils;

    @Autowired
    private TaoBaoUtils taoBaoUtils;

    public MessageType textMessage(Map<String, String> requestmap) {

        String oldmessages = requestmap.get("Content");
        int legth=oldmessages.length();
        log.info("系统时间："+new Date(System.currentTimeMillis())+" 输入内容："+oldmessages);
        MessageType message = null;
        //截取第一个空格前面的字符
        String cut = oldmessages.substring(0, oldmessages.indexOf(" ") == -1 ? 0 : oldmessages.indexOf(" "));
        if (oldmessages.length()>50) {
            String tao = taoBaoUtils.tranWord(taoBaoUtils.taoBaoCoupons(oldmessages,requestmap));
            message = new TextMessage(tao, requestmap);
        } else if ("百度云".equals(cut)) {
            String list = baiDuCloudUtils.pares(oldmessages.substring(4, legth));
            message = new TextMessage(list, requestmap);
        } else {
            String news = tuLingUtils.tuLingMessage(requestmap, oldmessages);
            message = new TextMessage(news, requestmap);
        }
        log.info("返回内容:"+((TextMessage) message).getContent());
        return message;
    }


    /**
     * 功能尚未开发
     * 所造成的错误代码
     */
    public MessageType defMessage(Map<String, String> requestmap) {
        MessageType messageType = new TextMessage(requestmap);
        return messageType;
    }

}
