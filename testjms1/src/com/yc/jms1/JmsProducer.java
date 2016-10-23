package com.yc.jms1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

/**
 * 生产消息并以队列方式 将消息存到jms服务器上
 * @author Administrator
 *
 */
public class JmsProducer {
	
	
	
	public static void main(String[] args) {
		//局部变量
		ConnectionFactory connectionFactory=null;   // 连接工厂
		Connection connection = null; 	// 连接
		Session session=null;				// 会话，接收或者发送消息的线程
		Destination destination=null;	// 消息的目的地
		MessageProducer messageProducer=null;	// 消息生产者
		
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(    YcConstants.USERNAME, YcConstants.PASSWORD, YcConstants.BROKEURL);
		try {
			
			connection = connectionFactory.createConnection();		// 通过连接工厂获取连接
			connection.start();		// 启动连接
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);	// 创建会话
			
			destination = session.createQueue("FirstQueue1");	// 创建消息队列   ->   jndi
			
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
	 * 发送消息
	 * @param session
	 * @param messageProducer
	 * @throws JMSException 
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException{
		for(int i=0; i < YcConstants.SENDNUM; i++){
			//通过会话创建 消息
			TextMessage message = session.createTextMessage("ActiveMQ  发送的消息"+i);
			System.out.println("发送消息："+i);
			messageProducer.send(message);   //生产者发送信息
		}
	}
}
