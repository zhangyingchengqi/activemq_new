package com.yc.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.utils.JmsConsumer3;

@WebListener
public class ConsumerListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		//启动消费者
		
		JmsConsumer3 jc=new JmsConsumer3(    arg0.getServletContext()   );
		jc.consume();
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
