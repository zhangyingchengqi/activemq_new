package com.yc.utils;

import javax.mail.MessagingException;

import com.yc.bean.EmailBean;

//任务
public interface Taskable {

	
	public  void doTask(Object obj) throws Exception;
}
