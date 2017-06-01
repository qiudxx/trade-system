package com.ace.trade.common.rocketmq;

import com.ace.trade.common.exception.AceMQException;
import com.ace.trade.common.utils.Utils;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/20<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class AceMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceMQProducer.class);

    private DefaultMQProducer producer;

    private String groupName;

    private String namesrvAddr;

    /**
     * 最大消息长度
     */
    private int maxMessageSize = 1024 * 1024 * 4; // 4M

    /**
     * 发送消息时长
     */
    private int sendMsgTimeout = 10000;

    /**
     * <pre>
     *     方法说明： spring初始化单例producer
     * </pre>
     */
    public void init() throws AceMQException {

        Utils.checkNotBlank(this.groupName, "groupName is blank");
        Utils.checkNotBlank(this.namesrvAddr, "namesrvAddr is blank");

        this.producer = new DefaultMQProducer(this.groupName);
        this.producer.setNamesrvAddr(this.namesrvAddr);
        this.producer.setMaxMessageSize(this.maxMessageSize);
        this.producer.setSendMsgTimeout(this.sendMsgTimeout);


        try {
            this.producer.start();
            LOGGER.info(String.format("producer is start!groupName=[%s], namesrvAddr:[%s]", this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            LOGGER.error(String.format("producer error!groupName=[%s], namesrvAddr:[%s]", this.groupName, this.namesrvAddr), e);
            throw new AceMQException(e);
        }
    }

    public SendResult sendMsg(String topic, String tag, String key, String msgText) throws AceMQException {
        Utils.checkNotBlank(msgText, "msg can't be blank");
        return sendMsg(topic, tag, key, msgText.getBytes());
    }

    /**
     * @param topic 标题
     * @param tag   标签
     * @param key   唯一key
     * @param body  内容
     * @return
     */
    public SendResult sendMsg(String topic, String tag, String key, byte[] body) throws AceMQException {

        Utils.checkNotBlank(topic, "topic can't be blank!");

        Message message = new Message(topic, tag, key, body);
        SendResult sendResult = null;
        try {
            sendResult = this.producer.send(message);
            LOGGER.info("send msg success");
        } catch (Exception e) {
            LOGGER.error("send msg error", e.getMessage(), e);
            throw new AceMQException(e);
        }
        return sendResult;
    }


    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public void setSendMsgTimeout(int sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }
}
