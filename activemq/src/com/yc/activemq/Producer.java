package com.yc.activemq;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.activemq.process.ActiveMQProducerProcess;
/**
 * 生产者
 */
public class Producer {
	
    public ActiveMQProducerProcess activeMQProducerProcess;//发送消息的逻辑代码
    private String QueueName;//队列名称

	/**
	 * @param params 用于传递参数的类
 	 */
	public void producer(String jsonParams) {
    	if(activeMQProducerProcess==null){
    		throw new RuntimeException("请先设置ActiveMQProducerProcess");
    	}
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConfig.HOST);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            if(this.QueueName==null){//如果没有设置,则采用默认配置
            	destination = session.createQueue(ActiveMQConfig.QueueName);
            }else{
            	destination = session.createQueue(this.QueueName);
            }
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 逻辑处理代码
            activeMQProducerProcess.sendMessage(session, producer, jsonParams);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
	
	public void setActiveMQProducerProcess(
			ActiveMQProducerProcess activeMQProducerProcess) {
		this.activeMQProducerProcess = activeMQProducerProcess;
	}
	public Producer(ActiveMQProducerProcess activeMQProducerProcess) {
		super();
		this.activeMQProducerProcess = activeMQProducerProcess;
	}
	public String getQueueName() {
		return QueueName;
	}
	public void setQueueName(String queueName) {
		QueueName = queueName;
	}
}