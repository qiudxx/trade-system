package com.ace.trade.common.protocol.goods;

/**
 * Created by carl.yu on 2017/2/21.
 */
public class ReduceGoodsNumberReq {

    private Integer goodsId;
    private Integer goodsNumber; //减多少库存
    private String orderId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
