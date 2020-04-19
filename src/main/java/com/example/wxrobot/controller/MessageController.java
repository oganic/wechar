package com.example.wxrobot.controller;

import com.example.wxrobot.common.MessageType;
import com.example.wxrobot.common.model.TextMessage;
import com.example.wxrobot.entity.dto.HttpDTO;
import com.example.wxrobot.service.IRootService;
import com.example.wxrobot.util.CheckHelp;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private CheckHelp checkHelp;

    @Autowired
    private IRootService rootService;

    @GetMapping("/tell")
    public String checkOut(HttpDTO dto) {
        if (checkHelp.checkout(dto)) {
            System.out.println("验证成功");
            return dto.getEchostr();
        } else {
            System.out.println("验证失败");
        }
        return dto.getEchostr();
    }

    @PostMapping("tell")
    public String getMessage(HttpServletRequest request) {
        MessageType message = null;
        //解析发送过来的xml数据
        Map<String, String> requestmap = checkHelp.parseRequest(request);
        //判断传值过来的是什么类型,做响应处理
        String msgType = requestmap.get("MsgType");
        if ("text".equals(msgType)) {
            message = rootService.textMessage(requestmap);
        } else {
            message = rootService.defMessage(requestmap);
        }
        //将实体类转化为xml文件
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        return xStream.toXML(message);

    }
}
