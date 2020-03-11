package com.example.wxrobot.util;

import com.example.wxrobot.entity.HttpDTO;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CheckHelp {

    @Value("${system.token}")
    private String token;

    public boolean checkout(HttpDTO dto){
       String[] strs=new String[]{token,dto.getTimestamp(),dto.getNonce()};
        Arrays.sort(strs);
        String str=strs[0]+strs[1]+strs[2];
        //处理加密结果
        String mysig=encryp(str);
        if (mysig=="无"){
            return false;
        }
        System.out.println(dto.getSignature());
        return mysig.equals(dto.getSignature());
    }

    //处理加密结果
    public String encryp(String str) {
        try {
            //获取加密对象
            MessageDigest md=MessageDigest.getInstance("sha1");
            //加密
            byte[] digest=md.digest(str.getBytes());
            char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb=new StringBuilder();
            //处理加密结果
            for (byte b:digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "无";
    }


    /**
     * 解析发送过来的xml数据
     */
    public Map<String, String> parseRequest(ServletInputStream inputStream) {

        Map<String,String> context=new HashMap<>();
        SAXReader saxReader=new SAXReader();
        try {
            //读取传过来的值
            Document document=saxReader.read(inputStream);
            //获取根节点
            Element root =document.getRootElement();
            //获取子节点
            List<Element> elements = root.elements();

            //获得里面的内容
            for (Element element:elements){
                context.put(element.getName(),element.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return context;
    }
}
