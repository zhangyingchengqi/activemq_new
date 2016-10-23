package com.yc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.yc.activemq.Consumer;
import com.yc.activemq.process.ActiveMQConsumerProcess;
import com.yc.activemq.process.Impl.ActiveMQConsumerProcessEmail;

public class ActiveMQEmailListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				//创建 收到邮件后的逻辑处理类
				ActiveMQConsumerProcess emailProcess=new ActiveMQConsumerProcessEmail();
				//启动消费者,等待生成者的消息
				Consumer consumer=new Consumer(emailProcess);
				consumer.consumer();
			}
		}).start();
	}

}
