package com.yc.jms1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.utils.YcConstants;

/**消息消费者 */
public class JmsConsumer {
	
	
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
			destination = session.createQueue("FirstQueue1");	// 创建消息队列
			
			
			messageConsumer = session.createConsumer(destination);		// 创建消息消费者
			//while(true)这种方式处理消费是不合适的,最好采用临听器模式完成
			while(true){
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);   //阻塞式方法， 当没有消息时，程序会在这里停下来
				
				//其它的阻塞 式方法:     Scanner  sc=>   sc.nextLine();
				//      网络:          Socket socket=serverSocket.accept();
				
				
				if(textMessage != null){
					System.out.println("收到的消息："+textMessage.getText());
				} else {
					break;
				}
			}
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
