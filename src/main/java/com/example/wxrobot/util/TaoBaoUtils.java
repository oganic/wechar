package com.example.wxrobot.util;

import com.example.wxrobot.common.ErrorEnums;
import com.example.wxrobot.common.exception.TaoBaoException;
import com.example.wxrobot.config.TaoBaoConfig;
import com.example.wxrobot.entity.po.TaoBao;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TaoBaoUtils {

    @Autowired
    public TaoBaoConfig taoBaoConfig;

    /**
     * 获取某件商品信息
     */
    public TaoBao taoBaoCoupons(String name, Map<String, String> requestmap) {

        long id = 0;
        try {
            id = getItemId(name);
        } catch (Exception e) {
            throw new TaoBaoException(ErrorEnums.LINK_ERROR,requestmap);
        }
        //获取商品标题
        String title = name.substring(name.indexOf("【") + 1, name.indexOf("】"));

        TaoBao taoBao = null;

        //拿到所有的商品
        List<TbkDgMaterialOptionalResponse.MapData> allGoods = null;
        try {
            allGoods = getAllGoods(title);
        } catch (ApiException e) {
            throw new TaoBaoException(ErrorEnums.TAOBAO_ERROR, requestmap);
        }
        taoBao = new TaoBao();
        for (TbkDgMaterialOptionalResponse.MapData good : allGoods) {
            if (!(id == good.getItemId().longValue())) {
                continue;
            }
            if (good.getCouponShareUrl() == null) {
                taoBao.setUrl("https:" + good.getUrl());
            } else {
                taoBao.setUrl("https:" + good.getCouponShareUrl());
                taoBao.setCouponInfo(good.getCouponInfo());
                taoBao.setCouponAmount(Double.valueOf(good.getCouponAmount()));
            }
            taoBao.setName(good.getTitle());
            taoBao.setItemId(good.getItemId());
            taoBao.setPrice(Double.valueOf(good.getZkFinalPrice()));
            break;
        }
        return taoBao;
    }

    /**
     * 根据截取请求页面获取商品id
     *
     * @param name
     * @return
     */
    private Long getItemId(String name) throws Exception {
        String https = name.substring(name.indexOf("https"), name.indexOf("https") + 35);
        Long id = null;

        //打开连接
        URL realUrl = new URL(null, https, new sun.net.www.protocol.https.Handler());
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) realUrl.openConnection();
        // 设置请求方式（GET/POST）
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "utf-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("url")) {
                id = Long.valueOf(line.substring(line.indexOf("i") + 1, line.indexOf(".htm")));
                break;
            }
        }
        bufferedReader.close();

        return id;
    }

    /**
     * 获取所有商品信息
     */
    public List<TbkDgMaterialOptionalResponse.MapData> getAllGoods(String name) throws ApiException {

        List<String> coupons = new ArrayList<String>();
        TaobaoClient client = new DefaultTaobaoClient(taoBaoConfig.getGw(), taoBaoConfig.getAppKey(), taoBaoConfig.getAppSecret());
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(100L);
        //用过名字搜索
        req.setQ(name);
        //推广位id
        req.setAdzoneId(110258550453L);
        TbkDgMaterialOptionalResponse response = client.execute(req);
        List<TbkDgMaterialOptionalResponse.MapData> resultList = response.getResultList();
        return resultList;
    }

    /**
     * 优惠券链接转化成淘口令
     */
    public String tranWord(TaoBao taoBao) {
        TaobaoClient client = new DefaultTaobaoClient(taoBaoConfig.getEcoTaoBao(), taoBaoConfig.getAppKey(), taoBaoConfig.getAppSecret());
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText(taoBao.getName());
        req.setUrl(taoBao.getUrl());
        TbkTpwdCreateResponse response = null;
        try {
            response = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        TbkTpwdCreateResponse.MapData data = response.getData();
        String rep = taoBao.getName()+"\n"
                + taoBao.getCouponInfo()+"\n"
                +"商品原价："+taoBao.getPrice()+"\n"
                +"用券后："+(taoBao.getPrice()-taoBao.getCouponAmount())
                + "\n\n"  +"复制字段信息淘宝打开\n" + data.getModel();
        System.out.println(rep);
        return rep;
    }
}
