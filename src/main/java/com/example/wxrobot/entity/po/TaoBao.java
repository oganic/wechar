package com.example.wxrobot.entity.po;

import lombok.Data;

@Data
public class TaoBao {
    public TaoBao(){
        this.couponInfo="此商品没有优惠券唷，复制打开来可能有惊喜噢";
    }
    /**
     * 商品的名字
     */
    private String name;

    /**
     *商品的链接
     */
    private String url;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 商品价格
     */
    private double price;

    /**
     * 优惠券信息
     */
    private String couponInfo;

    /**
     * 优惠券价格
     */
    private double couponAmount;
}
