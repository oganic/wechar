package com.example.wxrobot.controller;

import com.example.wxrobot.common.MessageType;
import com.example.wxrobot.common.model.TextMessage;
import com.example.wxrobot.entity.HttpDTO;
import com.example.wxrobot.util.CheckHelp;
import com.example.wxrobot.function.DealMessage;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private CheckHelp checkHelp;

    @Autowired
    private DealMessage dealMessage;

    @GetMapping("/tell")
    public String checkOut(HttpDTO dto){
        if (checkHelp.checkout(dto)){
            System.out.println("验证成功");
            return dto.getEchostr();
        }else{
            System.out.println("验证失败");
        }
        return  "验证失败";
    }


    @PostMapping("tell")
    public String getMessage(HttpServletRequest request){
        String xml=null;
        try {
            //解析发送过来的xml数据
            Map<String,String> requestmap=checkHelp.parseRequest(request.getInputStream());
            //处理获得的信息,并且回复
            String type=requestmap.get("MsgType");
            MessageType message=null;
            switch (type){
                case "text":
                    //返回类型对象
                    message=dealMessage.textMessage(requestmap);
                    break;
                case "image":

                    break;
                case "voice":

                    break;
                case "video":

                    break;
                case "music":

                    break;
                case "news":

                    break;
            }
            //将实体类转化为xml文件
            XStream xStream=new XStream();
            xStream.processAnnotations(TextMessage.class);
            xml = xStream.toXML(message);
            //System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
