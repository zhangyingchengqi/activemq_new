package com.yc.activemq.process.Impl;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.yc.activemq.process.ActiveMQProducerProcess;

/**
 * activeMQ 关于邮件的生产者逻辑实现代码
 */
public class ActiveMQProducerProcessEmail implements ActiveMQProducerProcess{

	@Override
	public void sendMessage(Session session, MessageProducer producer,String jsonParams) throws JMSException {
        TextMessage message = session.createTextMessage(jsonParams);//json格式的文本消息
//		ObjectMessage message =session.createObjectMessage( params);//对象消息 会有关于序列化的问题
        // 发送消息到目的地方
        producer.send(message);
	}

}
