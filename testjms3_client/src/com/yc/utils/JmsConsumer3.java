package com.yc.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletContext;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

/**
 * 运行原理： 先启动jms服务器，然后启动jmsconsumer消费者，将消费者注册到服务器中并临听某个主题，当然也可以是多个消费者启动，临听同一个主题。
 * 然后启动jmsproducer生产者，这时生产者产生消息，并将消息存到服务器的主题中，这时，服务器循环所有的消费者，将主题中的消息一个个通知消费者获取。
 * 
 * @author Administrator
 *
 */
public class JmsConsumer3 {

	// 局部变量
	private static ConnectionFactory connectionFactory = null; // 连接工厂
	private Connection connection = null; // 连接
	private Session session = null; // 会话，接收或者发送消息的线程
	private Destination destination = null; // 消息的目的地
	private MessageConsumer messageConsumer = null; // 消息生产者

	static {
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(YcConstants.USERNAME, YcConstants.PASSWORD,
				YcConstants.BROKEURL);
	}
	
	
	
	private ServletContext application;
	
	public JmsConsumer3(  ServletContext application){
		this.application=application;
	}

	public void consume() {

		try {
			connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
			connection.start(); // 启动连接
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 创建会话，不加事务
			// destination = session.createQueue("FirstQueue1"); // 创建消息队列
			destination = session.createTopic(YcConstants.MESSAGE_TOPIC); // 创建 主题
			messageConsumer = session.createConsumer(destination); // 创建消息消费者
			messageConsumer.setMessageListener(new JmsListener(    application)); // 注册消息监听
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
