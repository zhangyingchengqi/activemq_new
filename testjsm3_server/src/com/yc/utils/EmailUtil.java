package com.yc.utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.yc.bean.EmailBean;

/**
 * 发邮件工具类
 */
public class EmailUtil implements Taskable ,Serializable{
	
	private static final long serialVersionUID = 7581726239444453363L;

	@Override
	public void doTask(Object obj) throws Exception {
		if( obj==null){
			return;
		}
		EmailBean email=(EmailBean)obj;
		
		// 配置发送邮件的环境属性
        final Properties props = new Properties();   //  Map<String,String>
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");    //
        props.put("mail.smtp.host", "smtp.163.com");    // smtp:简单邮件传输协议
        // 发件人的账号
        props.put("mail.user","zhangyingchengqi@163.com");   //与smtp保持一致
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", YcConstants.PWD_163);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);   
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));   //"zhangyingchengqi@163.com"
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(email.getEmail());
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
		message.setSubject(email.getTitle());
        // 设置邮件的内容体
        message.setContent(email.getContents(),"text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
	}
	
	public static void main(String[] args) throws Exception{
		EmailBean eb=new EmailBean();
		eb.setContents("注册成功");
		eb.setEmail("1014789568@qq.com");
		eb.setTitle("注册成功)");
		eb.setUname("lxd");
		EmailUtil eu=new EmailUtil();
		eu.doTask(eb);
	}
	

	
}
