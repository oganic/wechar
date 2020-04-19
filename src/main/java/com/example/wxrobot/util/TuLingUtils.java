package com.example.wxrobot.util;

import com.example.wxrobot.common.ErrorEnums;
import com.example.wxrobot.common.exception.TuLingException;
import com.example.wxrobot.config.TuLingConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.oganic.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TuLingUtils {


    @Autowired
    private TuLingConfig tuLingConfig;

    /**
     * 处理图铃消息
     */
    public  String tuLingMessage(Map<String, String> requestmap, String text) {
        String news = null;
        try {
            news = WeChatUtil.TuLingRobot(tuLingConfig.getHttpUrl(), tuLingConfig.getApiKey(), text);
        } catch (JsonProcessingException e) {
            throw new TuLingException(ErrorEnums.TULING_JSONERR,requestmap);
        }
        return news;
    }
}
