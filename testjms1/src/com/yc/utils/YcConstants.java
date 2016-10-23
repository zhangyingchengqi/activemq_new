package com.yc.utils;

import org.apache.activemq.ActiveMQConnection;

public class YcConstants {
	/**  默认的连接用户名  */
	public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;	
	/**  默认的连接密码  */
	public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;	
	/**  默认的连接地址 */
	public static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; 
	//public static final String BROKEURL = "tcp://localhost:61616";	
	/** 发送的消息数量  */
	public static final int SENDNUM= 10;	
}
