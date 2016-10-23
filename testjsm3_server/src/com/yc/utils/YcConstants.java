package com.yc.utils;

import org.apache.activemq.ActiveMQConnection;

public class YcConstants {
	/**  默认的连接用户名  */
	public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;	
	/**  默认的连接密码  */
	public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;	
	/**  默认的连接地址 */
	//public static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; 
	public static final String BROKEURL = "tcp://192.168.15.197:61616";	
	
	/** 消息服务器中的邮件队列名  */
	public static final String EMAIL_QUEUE="email_queue";
	
	public static final String PWD_163=""; //TODO: 加入密码
	
	/**    主题的地址 */
	public static final String MESSAGE_TOPIC="message_topic";
		
}
