package com.yc.jms2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

public class JmsProducer {
	// 局部变量
	private static ConnectionFactory connectionFactory = null; // 连接工厂
	private Connection connection = null; // 连接
	private Session session = null; // 会话，接收或者发送消息的线程
	private Destination destination = null; // 消息的目的地
	private MessageProducer messageProducer = null; // 消息生产者

	static {
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(YcConstants.USERNAME, YcConstants.PASSWORD,
				YcConstants.BROKEURL);
	}

	public void sendMessage(String text) {

		try {
			connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
			connection.start(); // 启动连接
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); // 创建会话
			// destination = session.createQueue("FirstQueue1"); // 创建消息队列
			destination = session.createTopic(YcConstants.MESSAGE_TOPIC); // TODO:注意，这里创建
																// 的是主题
																// createTopic
			messageProducer = session.createProducer(destination); // 创建消息生产者
			// 发送消息
			// sendMessage(session, messageProducer);

			// 发送消息
			TextMessage m = session.createTextMessage();

			m.setText(text);

			messageProducer.send(m); // 生产者发送信息

			// 正式提交发送消息的操作
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
