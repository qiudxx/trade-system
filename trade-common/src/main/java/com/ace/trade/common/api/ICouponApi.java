package com.ace.trade.common.api;

import com.ace.trade.common.protocol.coupon.ChangeGouponStatusReq;
import com.ace.trade.common.protocol.coupon.ChangeGouponStatusResp;
import com.ace.trade.common.protocol.coupon.QueryCouponReq;
import com.ace.trade.common.protocol.coupon.QueryCouponResp;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public interface ICouponApi {

    /**
     * <pre>
     *     查询优惠券
     * </pre>
     * @param req
     * @return
     */
    public QueryCouponResp queryCoupon(QueryCouponReq req);

    public ChangeGouponStatusResp changeGouponStatus(ChangeGouponStatusReq req);
}
