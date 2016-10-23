package com.yc.jms2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

/**
 * 1. 临听器模式： 原理： 将一个临听器注册服务中，一个消费者一个临听器，当服务收到消息时，则循环临听器列表，调用 临听器的onMessage() 2.
 * 要先将临听器注册到服务器，服务器才能通知消费者。
 * 
 * @author Administrator
 *
 */
public class JmsConsumer2 {
	private static ConnectionFactory connectionFactory; // 连接工厂
	private Connection connection = null; // 连接
	private Session session; // 会话，接收或者发送消息的线程
	private Destination destination; // 消息的目的地
	private MessageConsumer messageConsumer; // 消息生产者
	
	private int index;
	
	public JmsConsumer2( int index){
		this.index=index;
	}

	static {
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(YcConstants.USERNAME, YcConstants.PASSWORD,
				YcConstants.BROKEURL);
	}

	public void consumer() {

		try {
			connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
			connection.start(); // 启动连接
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 创建会话，不加事务
			destination = session.createQueue(    YcConstants.EMAIL_QUEUE  ); // 创建消息队列
			messageConsumer = session.createConsumer(destination); // 创建消息消费者

			
			messageConsumer.setMessageListener(new JmsListener( index)); //

			

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
