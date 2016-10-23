package com.yc.jms3.pubAndSub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.jms2.JmsListener;
import com.yc.utils.YcConstants;

/**
 * 运行原理：
 * 先启动jms服务器，然后启动jmsconsumer消费者，将消费者注册到服务器中并临听某个主题，当然也可以是多个消费者启动，临听同一个主题。
 * 然后启动jmsproducer生产者，这时生产者产生消息，并将消息存到服务器的主题中，这时，服务器循环所有的消费者，将主题中的消息一个个通知消费者获取。 
 * @author Administrator
 *
 */
public class JmsConsumer3 {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;   // 连接工厂
		Connection connection = null; 	// 连接
		Session session;				// 会话，接收或者发送消息的线程
		Destination destination;	// 消息的目的地
		MessageConsumer messageConsumer;	// 消息生产者
		
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(YcConstants.USERNAME, YcConstants.PASSWORD, YcConstants.BROKEURL);
		try {
			connection = connectionFactory.createConnection();		// 通过连接工厂获取连接
			connection.start();		// 启动连接
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);	// 创建会话，不加事务
			//destination = session.createQueue("FirstQueue1");	// 创建消息队列
			destination = session.createTopic("FirstTopic1");   //创建 主题
			messageConsumer = session.createConsumer(destination);		// 创建消息消费者
			messageConsumer.setMessageListener(new JmsListener());			// 注册消息监听
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}
}
