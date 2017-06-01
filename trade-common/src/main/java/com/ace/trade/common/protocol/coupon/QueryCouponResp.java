package com.ace.trade.common.protocol.coupon;

import com.ace.trade.common.protocol.BaseResp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by carl.yu on 2017/2/21.
 */
public class QueryCouponResp extends BaseResp {

    private String couponId;

    private BigDecimal couponPrice;

    private String orderId;

    private String isUsed;

    private Date usedTime;

    private Integer userId;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

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

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
