package com.ace.trade.common.api;

import com.ace.trade.common.protocol.goods.QueryGoodsReq;
import com.ace.trade.common.protocol.goods.QueryGoodsResp;
import com.ace.trade.common.protocol.goods.ReduceGoodsNumberReq;
import com.ace.trade.common.protocol.goods.ReduceGoodsNumberResp;

/**
 * Created by carl.yu on 2017/2/21.
 */
public interface IGoodsApi {

    QueryGoodsResp queryGoods(QueryGoodsReq req);

    ReduceGoodsNumberResp reduceGoodsNumber(ReduceGoodsNumberReq req);

}
