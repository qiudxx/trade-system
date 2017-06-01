package com.ace.trade.common.client;

import com.ace.trade.common.contants.TradeEnums;
import com.ace.trade.common.protocol.user.QueryUserResp;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by carl.yu on 2017/2/21.
 */
public class RestClientProxy implements FactoryBean {

    private RestTemplate restTemplate = new RestTemplate();

    private Class<?> clz;

    private TradeEnums.RestServerEnum serverEnum;

    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clz},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String fullPath = serverEnum.getServerUrl() + "/" + method.getName();
                        QueryUserResp resp = (QueryUserResp) restTemplate.postForObject(fullPath, args[0], method.getReturnType());
                        return resp;
                    }
                }
        );
    }

    public Class<?> getObjectType() {
        return clz;
    }

    public boolean isSingleton() {
        return false;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public void setServerEnum(TradeEnums.RestServerEnum serverEnum) {
        this.serverEnum = serverEnum;
    }
}
