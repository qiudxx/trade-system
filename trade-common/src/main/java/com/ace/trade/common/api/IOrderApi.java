package com.ace.trade.common.api;

import com.ace.trade.common.protocol.order.ConfirmOrderReq;
import com.ace.trade.common.protocol.order.ConfirmOrderResp;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public interface IOrderApi {

    /**
     * <pre>
     *     确认下单接口
     * </pre>
     *
     * @param req 请求
     * @return
     */
    ConfirmOrderResp confirmOrder(ConfirmOrderReq req);

}
