package com.ace.trade.order.service;

import com.ace.trade.common.protocol.order.ConfirmOrderReq;
import com.ace.trade.common.protocol.order.ConfirmOrderResp;

/**
 * Created by carl.yu on 2017/2/21.
 */
public interface OrderService {
    ConfirmOrderResp confirmOrder(ConfirmOrderReq req);
}
