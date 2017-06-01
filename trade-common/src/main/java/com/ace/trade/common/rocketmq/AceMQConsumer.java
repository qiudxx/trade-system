package com.ace.trade.common.rocketmq;

import com.ace.trade.common.exception.AceMQException;
import com.ace.trade.common.utils.Utils;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/20<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class AceMQConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(AceMQConsumer.class);

    /**
     * 组名
     */
    private String groupName;

    /**
     * 订阅的主题
     */
    private String topic;

    /**
     * 订阅tag，多个tag以||分隔
     */
    private String tag = "*";

    private String namesrvAddr;
    private int consumeThreadMin = 20;
    private int consumeThreadMax = 64;
    private DefaultMQPushConsumer consumer;

    private IMessageProcessor processor;

    public void init() throws AceMQException {
        Utils.checkNotBlank(this.groupName, "group name can't be blank!");
        Utils.checkNotBlank(this.topic, "topic can't be blank!");
        Utils.checkNotBlank(this.namesrvAddr, "namesrvAddr can't be blank!");

        this.consumer = new DefaultMQPushConsumer(groupName);
        this.consumer.setNamesrvAddr(this.namesrvAddr);
        this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        this.consumer.setConsumeThreadMax(this.consumeThreadMax);
        this.consumer.setConsumeThreadMin(this.consumeThreadMin);

        //注册listener
        AceMessageListener aceMessageListener = new AceMessageListener();
        aceMessageListener.setProcessor(this.processor);
        consumer.registerMessageListener(aceMessageListener);

        try {
            consumer.subscribe(this.topic, this.tag);
            consumer.start();
            LOGGER.info("consumer start success, groupName:{}, topic:{}, namesrvAddr:{}", this.groupName, this.topic, this.namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error("consumer start failed, groupName:{}, topic:{}, namesrvAddr:{}", this.groupName, this.topic, this.namesrvAddr, e);
            throw new AceMQException(e);
        }
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public void setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }

    public void setProcessor(IMessageProcessor processor) {
        this.processor = processor;
    }
}
