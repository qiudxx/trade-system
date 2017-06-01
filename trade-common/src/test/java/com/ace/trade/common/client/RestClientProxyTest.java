package com.ace.trade.common.client;

import com.ace.trade.common.api.IUserApi;
import com.ace.trade.common.protocol.user.QueryUserReq;
import com.ace.trade.common.protocol.user.QueryUserResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by carl.yu on 2017/2/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:xml/spring-rest-client.xml")
public class RestClientProxyTest {

    @Autowired
    private IUserApi userApi;


    @Test
    public void testQueryUser() throws Exception {
        QueryUserReq req = new QueryUserReq();
        req.setUserId(1);
        QueryUserResp resp = userApi.queryByUserId(req);
        System.out.println(resp.getUserId());
    }
}