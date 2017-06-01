package com.ace.trade.common.rocketmq;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class TestProcessorB implements IMessageProcessor {


    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("B收到消息:" + messageExt.toString());
        return true;
    }
}
