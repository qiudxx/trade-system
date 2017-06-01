package com.ace.trade.order.api;

import com.ace.trade.common.api.IOrderApi;
import com.ace.trade.common.protocol.order.ConfirmOrderReq;
import com.ace.trade.common.protocol.order.ConfirmOrderResp;
import com.ace.trade.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by carl.yu on 2017/2/21.
 */
@Controller
public class OrderApiImpl implements IOrderApi {


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/confirmOrder", method = {RequestMethod.POST})
    public ConfirmOrderResp confirmOrder(@RequestBody ConfirmOrderReq req) {
        return orderService.confirmOrder(req);
    }
}
