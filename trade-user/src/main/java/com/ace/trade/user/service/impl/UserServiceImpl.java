package com.ace.trade.user.service.impl;

import com.ace.trade.common.contants.TradeEnums;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.protocol.user.QueryUserResp;
import com.ace.trade.entity.TradeUser;
import com.ace.trade.mapper.TradeUserMapper;
import com.ace.trade.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    public QueryUserResp queryByUserId(QueryUserReq queryUserReq) {
        QueryUserResp resp = new QueryUserResp();
        resp.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        resp.setRetCode(TradeEnums.RetEnum.SUCCESS.getDesc());

        try {
            if (queryUserReq == null || queryUserReq.getUserId() == null) {
                throw new RuntimeException("请求参数不正确");
            }

            TradeUser user = tradeUserMapper.selectByPrimaryKey(queryUserReq.getUserId());
            if (user != null) {
                BeanUtils.copyProperties(user, resp);
            } else {
                throw new RuntimeException("未查询到该用户");
            }
        } catch (Exception e) {
            resp.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            resp.setRetCode(TradeEnums.RetEnum.FAIL.getDesc());
        }

        return resp;
    }

}
