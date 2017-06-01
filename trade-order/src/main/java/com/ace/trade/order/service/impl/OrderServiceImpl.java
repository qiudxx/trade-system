package com.ace.trade.order.service.impl;

import com.ace.trade.common.api.ICouponApi;
import com.ace.trade.common.api.IGoodsApi;
import com.ace.trade.common.api.IUserApi;
import com.ace.trade.common.contants.TradeEnums;
import com.ace.trade.common.exception.AceOrderException;
import com.ace.trade.common.protocol.coupon.QueryCouponReq;
import com.ace.trade.common.protocol.coupon.QueryCouponResp;
import com.ace.trade.common.protocol.goods.QueryGoodsReq;
import com.ace.trade.common.protocol.goods.QueryGoodsResp;
import com.ace.trade.common.protocol.order.ConfirmOrderReq;
import com.ace.trade.common.protocol.order.ConfirmOrderResp;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.utils.Utils;
import com.ace.trade.entity.TradeOrder;
import com.ace.trade.mapper.TradeOrderMapper;
import com.ace.trade.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by carl.yu on 2017/2/21.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private IGoodsApi goodsApi;

    @Autowired
    private ICouponApi couponApi;

    @Autowired
    private IUserApi userApi;

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    public ConfirmOrderResp confirmOrder(ConfirmOrderReq req) {
        ConfirmOrderResp resp = new ConfirmOrderResp();
        //1. 检查校验
        checkConfirmOrderReq(req);
        QueryGoodsResp goodsResp = queryGoods(req);
        checkOrderAndGoods(req, goodsResp);
        //2. 创建不可见订单
        saveUnconfirmedOrder(req);

        //3. 调用远程服务，如果成功，如果失败
        return null;
    }

    /**
     * <pre>
     *     创建不可见订单
     * </pre>
     *
     * @param req
     */
    private void saveUnconfirmedOrder(ConfirmOrderReq req) {
        TradeOrder order = new TradeOrder();
        order.setOrderId(Utils.uuid());
        order.setUserId(req.getUserId());
        order.setOrderStatus(TradeEnums.OrderStatusEnum.UN_CONFIRMED.getStatusCode());
        order.setPayStatus(TradeEnums.PayStatusEnum.UN_PAID.getStatusCode());
        order.setShippngStatus(TradeEnums.ShippingStatusEnum.UN_SHIPPED.getStatusCode());
        order.setAddress(req.getAddress());
        order.setConsignee(req.getConsignee());
        order.setGoodsId(req.getGoodsId());
        order.setGoodsNumber(req.getGoodsNumber());
        order.setGoodsPrice(req.getGoodsPrice());
        BigDecimal goodAmount = req.getGoodsPrice().multiply(new BigDecimal(req.getGoodsNumber()));
        order.setGoodsAmount(goodAmount);

        BigDecimal shippingFee = goodAmount.doubleValue() > 100.00 ? BigDecimal.ZERO : new BigDecimal("10");
        order.setShippingFee(shippingFee);
        checkState(req.getShippingFee().compareTo(shippingFee) == 0, "快递费不正确");

        BigDecimal orderAmount = goodAmount.add(shippingFee);
        checkState(req.getOrderAmount().compareTo(orderAmount) == 0, "订单总价异常");
        order.setOrderAmount(orderAmount);

        //如果优惠券不为空
        String couponId = req.getCouponId();
        if (StringUtils.isNotBlank(couponId)) {
            QueryCouponResp couponResp = queryCoupon(req);
            checkState(couponResp != null && TradeEnums.RetEnum.SUCCESS.getCode().equals(couponResp.getRetCode()),
                    "优惠券非法");
            checkState(TradeEnums.YesNoEnum.NO.getCode().equals(couponResp.getIsUsed())
                    , "优惠券已经使用");
            order.setCouponId(couponId);
            order.setCouponPaid(couponResp.getCouponPrice());

        }

        // 余额支付，如果需要余额支付的话


    }

    private QueryCouponResp queryCoupon(ConfirmOrderReq req) {
        QueryCouponReq couponReq = new QueryCouponReq();
        couponReq.setCouponId(req.getCouponId());

        QueryCouponResp resp = couponApi.queryCoupon(couponReq);
        return resp;
    }


    /**
     * <pre>
     *     远程获取到库存信息后再和订单信息进行对比
     * </pre>
     *
     * @param req       订单信息
     * @param goodsResp 库存信息
     */
    private void checkOrderAndGoods(ConfirmOrderReq req, QueryGoodsResp goodsResp) {
        checkOrderAndGoods(req, goodsResp);
        checkState(goodsResp != null && TradeEnums.RetEnum.SUCCESS.getCode().equals(goodsResp.getRetCode()),
                String.format("未查询到该商品[%s]", req.getGoodsId()));

        checkState(goodsResp.getGoodsNumber() >= req.getGoodsNumber(),
                String.format("商品库存不足"));

        checkState(goodsResp.getGoodsPrice().compareTo(req.getGoodsPrice()) == 0,
                "当前商品价格有变，请重新下单");
        if (req.getShippingFee() == null) {
            req.setShippingFee(BigDecimal.ZERO);
        }

        if (req.getOrderAmount() == null) {
            req.setOrderAmount(BigDecimal.ZERO);
        }

    }


    /**
     * 通过远程服务查询goods
     *
     * @param req 订单信息
     * @return
     */
    private QueryGoodsResp queryGoods(ConfirmOrderReq req) {
        QueryGoodsReq goodsReq = new QueryGoodsReq();
        goodsReq.setGoodsId(req.getGoodsId());
        return goodsApi.queryGoods(goodsReq);
    }

    private static void checkState(boolean state, String errorMsg) {
        if (!state) {
            throw new AceOrderException(errorMsg);
        }
    }

    private static void checkNotNull(Object target, String errorMsg) {
        if (target == null) {
            throw new AceOrderException(errorMsg);
        }
    }

    private void checkConfirmOrderReq(ConfirmOrderReq req) {

        checkNotNull(req, "下单消息不能为空");
        checkNotNull(req.getUserId(), "会员账号不能为空");
        checkNotNull(req.getGoodsId(), "商品编号不能为空");
        checkState(req.getGoodsNumber() == null || req.getGoodsNumber() < 0,
                "购买数量不能<0");
        checkNotNull(req.getAddress(), "收货地址不能为空");
    }
}
