package com.ace.trade.common.client;

import com.ace.trade.common.contants.TradeEnums;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.protocol.user.QueryUserResp;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
public class RestClient {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        QueryUserReq req = new QueryUserReq();
        req.setUserId(1);
        QueryUserResp resp = restTemplate.postForObject(TradeEnums.RestServerEnum.USERS.getServerUrl() + "/queryByUserId", req, QueryUserResp.class);
        System.out.println(resp.getUserId());
    }

}
