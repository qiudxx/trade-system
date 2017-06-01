package com.ace.trade.common.rocketmq;

import com.alibaba.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/20<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:xml/spring-rocketmq-producer.xml")
public class AceMQProducerTest {

    @Autowired
    private AceMQProducer producer;


    @Test
    public void testSend() throws Exception {
        SendResult result = this.producer.sendMsg(
                "TestTopic",
                "tagA",
                "12345678",
                "this is order msg2"
        );
        System.out.println(result);
    }

}