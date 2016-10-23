package com.yc.jms3.pubAndSub;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

public class JmsProducer {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;   // 连接工厂
		Connection connection = null; 	// 连接
		Session session;				// 会话，接收或者发送消息的线程
		Destination destination;	// 消息的目的地
		MessageProducer messageProducer;	// 消息生产者
		
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(YcConstants.USERNAME, YcConstants.PASSWORD, YcConstants.BROKEURL);
		try {
			connection = connectionFactory.createConnection();		// 通过连接工厂获取连接
			connection.start();		// 启动连接
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);	// 创建会话
			// destination = session.createQueue("FirstQueue1");	//      创建消息队列
			destination = session.createTopic("FirstTopic1");       //  TODO:注意，这里创建 的是主题   createTopic
			messageProducer = session.createProducer(destination);		// 创建消息生产者
			// 发送消息
			sendMessage(session, messageProducer);
		    // 正式提交发送消息的操作
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}  finally {
			// 关闭连接
			if(connection!=null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 发布的消息
	 * @param session
	 * @param messageProducer
	 * @throws JMSException 
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException{
		for(int i=0; i < YcConstants.SENDNUM; i++){
			TextMessage message = session.createTextMessage("ActiveMQ  发送的消息"+i);
			System.out.println("发送消息："+i);
			messageProducer.send(message);
		}
	}
}
