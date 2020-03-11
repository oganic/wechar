package com.example.wxrobot;

import com.example.wxrobot.util.HttpClientFind;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxrobotApplicationTests {
   /* @Autowired
    private HttpClientFind httpClientFind;


    @Test
    void contextLoads() {

            String sethtml = httpClientFind.search("Java");

            Document html = Jsoup.parse(sethtml);
            Elements elements = html.select("div#content >ul >li");
            for (
                    Element element:elements){
                System.out.println(element.select("a").first().attr("title"));
                System.out.println(element.select("a").first().attr("href"));
                System.out.println(element.select("div.list-content").text());
            }

    }*/

}
