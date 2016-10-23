package com.yc.activemq.process.Impl;

import java.lang.reflect.Type;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.mail.MessagingException;

import com.google.gson.reflect.TypeToken;
import com.yc.activemq.process.ActiveMQConsumerProcess;
import com.yc.email.EmailBean;
import com.yc.email.EmailUtil;
import com.yc.utils.GsonUtil;
/**
 * activeMQ 关于邮件的消费者逻辑实现代码
 */
public class ActiveMQConsumerProcessEmail implements ActiveMQConsumerProcess{

	@Override
	public boolean doProcess(MessageConsumer consumer) throws JMSException {
		TextMessage message = (TextMessage) consumer.receive();
        if (null != message) {
        	//这里放收到数据后的逻辑代码
        	String json=message.getText();
        	Type objectType = new TypeToken<EmailBean>(){}.getType();
        	EmailBean email=(EmailBean) GsonUtil.JsonToObject(json,objectType);
    		try {
				EmailUtil.sendEmail(email);//发送邮件
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
        } else {
        	return false;
        }
		return true;
	}

}
