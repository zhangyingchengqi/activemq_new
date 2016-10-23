package com.yc.jms2;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.yc.bean.EmailBean;
import com.yc.utils.EmailUtil;
import com.yc.utils.Taskable;

/**
 * 采用临听器来实现
 * 
 * @author zy 运行原理：当jms服务器接收到新消息后，会自动回调注册 在这台jms服务
 *         器上的临听器，并自动回调这些临听器的onMessage方法，将新收到的消息传进去.
 *
 */
public class JmsListener implements MessageListener {

	private int index;

	public JmsListener( int index){
		this.index=index;
	}
	
	private Taskable taskable;

	/*
	 * 收到的消息
	 */
	@Override
	public void onMessage(Message message) {
		try {
			// System.out.println("收到的消息：" + ((TextMessage) message).getText());

			MapMessage mapMessage = (MapMessage) message;
			Map<String, String> map = (Map<String, String>) mapMessage.getObject("info");
			String uname = map.get("uname");
			String email = map.get("email");
			//String type=map.get("type");
			 //System.out.println( "第"+index+"个"+ uname+"\t"+ email);

			Object obj=null;
			// TODO:发邮件
			//  if(  判断一下消息类型  ){
			          taskable=new EmailUtil();
			          obj=new EmailBean(  "注册成功",  "祝贺您"+uname+":<br /> 您注册本岗站成功....", uname, email  );
			          
		    //}else if(   判断是否为短信){
			          // taskable=new SmsUtil();
			          // obj=new SmsBean();
		     //}
			          
			  taskable.doTask(  obj  );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
