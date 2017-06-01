package com.ace.trade.user.api;

import com.ace.trade.common.api.IUserApi;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.protocol.user.QueryUserResp;
import com.ace.trade.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
@Controller
public class UserApiImpl implements IUserApi {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/queryByUserId", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public QueryUserResp queryByUserId(@RequestBody QueryUserReq req) {
        return userService.queryByUserId(req);
    }
}