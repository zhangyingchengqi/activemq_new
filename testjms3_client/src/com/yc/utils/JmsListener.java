package com.yc.utils;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.ServletContext;

/**
 * 采用临听器来实现
 * 
 * @author zy 运行原理：当jms服务器接收到新消息后，会自动回调注册 在这台jms服务
 *         器上的临听器，并自动回调这些临听器的onMessage方法，将新收到的消息传进去.
 *
 */
public class JmsListener implements MessageListener {
	
	private ServletContext application;
	public JmsListener(   ServletContext application){
		this.application=application;
	}

	/*
	 * 收到的消息
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("收到的消息：" + ((TextMessage) message).getText());
			
			//存到一个  set.
			Set<String> set=new HashSet<String>();
			if(   application.getAttribute("set")!=null){
				set=  (Set<String>) application.getAttribute(  "set");
			}
			
			set.add(     ((TextMessage) message).getText()  );
			this.application.setAttribute("set", set);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
