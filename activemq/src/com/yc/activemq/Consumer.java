package com.yc.activemq;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.yc.activemq.process.ActiveMQConsumerProcess;

/**
 * 消费者
 */
public class Consumer {
	
	public ActiveMQConsumerProcess activeMQConsumerProcess;//接收到消息后 对应的处理逻辑代码
	private String QueueName;//队列名称

	public void consumer() {
		if(activeMQConsumerProcess==null){
			throw new RuntimeException("请先设置ActiveMQConsumerProcess!");
		}
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
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
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            if(this.QueueName==null){//如果没有设置,则采用默认配置
            	destination = session.createQueue(ActiveMQConfig.QueueName);
            }else{
            	destination = session.createQueue(this.QueueName);
            }
            
            consumer = session.createConsumer(destination);
            while (true) {
            	//这里放收到数据后的逻辑代码
            	boolean flag=activeMQConsumerProcess.doProcess(consumer);
            	//当接收的消息为null时,会返回false 则退出消费者
            	if( ! flag ){
            		break;
            	}
            }
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
	
	public void setActiveMQConsumerProcess(
			ActiveMQConsumerProcess activeMQConsumerProcess) {
		this.activeMQConsumerProcess = activeMQConsumerProcess;
	}
	
	public Consumer(ActiveMQConsumerProcess activeMQConsumerProcess) {
		super();
		this.activeMQConsumerProcess = activeMQConsumerProcess;
	}

	public String getQueueName() {
		return QueueName;
	}

	public void setQueueName(String queueName) {
		QueueName = queueName;
	}
}
