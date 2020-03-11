package com.example.wxrobot.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import java.io.IOException;



@Component
public class HttpClientFind {

    private static final Integer maxTotal=100;

    private static final Integer maxPerRoute=10;

    private static final Integer setConnectTimeout=2000;

    private static final Integer setConnectionRequestTimeout=1000;

    private static final Integer setSocketTimeout=10000;

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    public HttpClientFind(){
        this.poolingHttpClientConnectionManager=new PoolingHttpClientConnectionManager();
        this.poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        this.poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
    }

    /**
     * 根据发送的名称进行返回一个html文件
     *
     */
    public String search(String url) {
        //使用连接池
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.poolingHttpClientConnectionManager).build();

        //创建httpget请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(RequestConfig.custom()
                                    .setConnectTimeout(setConnectTimeout) //创建时间最长时间
                                    .setConnectionRequestTimeout(setConnectionRequestTimeout)//获取连接最长时间
                                    .setSocketTimeout(setSocketTimeout)//获取传输最长时间
                                    .build()
        );

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                //判断是否为空
                if (response.getEntity() != null) {
                    String sethtml = EntityUtils.toString(response.getEntity(), "utf-8");
                    return sethtml;
                }
                else {
                    return "";
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
        return "";
    }
}
