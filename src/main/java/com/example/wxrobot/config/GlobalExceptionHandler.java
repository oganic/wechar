package com.example.wxrobot.config;

import com.example.wxrobot.common.MessageType;
import com.example.wxrobot.common.exception.BaseRuntimeException;
import com.example.wxrobot.common.model.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * 解决自己编写代码，但微信服务器发送过来的消息无法获取
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BaseRuntimeException.class})
    @ResponseBody
    public String exception(BaseRuntimeException e) {
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        MessageType messageType=new TextMessage(e.getMessage(),e.getRequestmap());
        return xStream.toXML(messageType);
    }
}
