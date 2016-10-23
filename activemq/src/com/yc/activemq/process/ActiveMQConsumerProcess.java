package com.yc.activemq.process;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;

/**
 * 在activemq中消费者发送数据的逻辑代码接口
 */
public interface ActiveMQConsumerProcess {

	public boolean doProcess(MessageConsumer consumer)throws JMSException ;
}
