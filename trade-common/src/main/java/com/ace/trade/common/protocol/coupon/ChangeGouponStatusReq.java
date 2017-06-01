package com.ace.trade.common.protocol.coupon;

/**
 * Created by carl.yu on 2017/2/21.
 */
public class ChangeGouponStatusReq {
    private String orderId;

    private String isUsed;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
}
