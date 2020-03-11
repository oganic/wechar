package com.example.wxrobot.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Text {

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager=new PoolingHttpClientConnectionManager();

    public static void main(String[] args) {
        Text text=new Text();
       // text.put("http://wx.jqgcs123.com/s/comb/n-java&s-feedtime1");
        text.put("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=5&s=181&click=0");
        text.put("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=7&s=181&click=0");

    }


    public void put(String  url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();

        //创建httpget请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(RequestConfig.custom()
                .setConnectTimeout(1000) //创建时间最长时间
                .setConnectionRequestTimeout(1000)//获取连接最长时间
                .setSocketTimeout(1000)//获取传输最长时间
                .build()
        );

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                //判断是否为空
                if (response.getEntity() != null) {
                    String sethtml = EntityUtils.toString(response.getEntity(), "utf-8");
                    System.out.println(sethtml);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
