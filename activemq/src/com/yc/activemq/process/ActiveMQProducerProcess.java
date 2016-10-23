package com.yc.activemq.process;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * 在activemq中生产者接 发送数据的逻辑代码接口
 */
public interface ActiveMQProducerProcess {

	/**
	 * @param session
	 * @param producer
	 * @param obj 参数类,必须序列化
	 * @throws JMSException
	 */
	public void sendMessage(Session session, MessageProducer producer,String jsonParams) throws JMSException;

}
