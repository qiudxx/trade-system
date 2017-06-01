package com.ace.trade.common.rocketmq;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public interface IMessageProcessor {


    /**
     * <pre>
     *     处理消息接口
     * </pre>
     * @param messageExt 单个消息
     * @return
     */
    boolean handleMessage(MessageExt messageExt);

}
