package com.example.wxrobot.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaiDuCloudUtils {

    private static final String url="http://wx.jqgcs123.com/s/comb/n-";

    @Autowired
    private HttpClientFind httpClientFind;


    public  String  pares(String name){

        String sethtml = httpClientFind.search(url+name+"&s-feedtime1");

        Document html = Jsoup.parse(sethtml);
        Elements elements = html.select("div#content >ul >li");
        if (elements.isEmpty()){
            return "查询不到数据";
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<=9;i++){
            sb.append(elements.get(i).select("a").first().attr("title")+"\n");

            sb.append("http://wx.jqgcs123.com"+elements.get(i).select("a").first().attr("href")+"\n");
        }
        return sb.toString();
    }

}
