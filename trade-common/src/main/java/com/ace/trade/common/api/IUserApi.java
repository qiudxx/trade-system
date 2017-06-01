package com.ace.trade.common.api;

import com.ace.trade.common.protocol.user.ChangeUserMoneyResp;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.protocol.user.QueryUserResp;
import com.ace.trade.common.protocol.user.ChangeUserMoneyReq;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public interface IUserApi {
    QueryUserResp queryByUserId(QueryUserReq queryUserReq);

    ChangeUserMoneyResp changeUserMoney (ChangeUserMoneyReq req);
}
