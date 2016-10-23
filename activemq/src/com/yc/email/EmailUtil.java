package com.yc.email;

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

/**
 * 发邮件工具类
 */
public class EmailUtil implements Serializable{
	
	private static final long serialVersionUID = 7581726239444453363L;

	public static void sendEmail(EmailBean email) throws MessagingException{
		if(email.getReceiver()==null || "".equals(email.getReceiver()) ){
			throw new RuntimeException("收信人的地址不能为空!");
		}
		if(email.getContents()==null || "".equals(email.getContents()) ){
			throw new RuntimeException("邮件内容不能为空!");
		}
		if(email.getTitle()==null || "".equals(email.getTitle() ) ){
			throw new RuntimeException("邮件标题不能为空!");
		}
		
		// 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put(EmailConfig.emailAuthKey, EmailConfig.emailAuth);
        props.put(EmailConfig.emailHostKey, EmailConfig.emailHost);
        // 发件人的账号
        props.put(EmailConfig.emailUserKey,EmailConfig.emailUser);
        // 访问SMTP服务时需要提供的密码
        props.put(EmailConfig.emailPasswordKey, EmailConfig.emailPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty(EmailConfig.emailUserKey);
                String password = props.getProperty(EmailConfig.emailPasswordKey);
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty(EmailConfig.emailUserKey));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(email.getReceiver());
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
		message.setSubject(email.getTitle());
        // 设置邮件的内容体
        message.setContent(email.getContents(), email.getContentsType());
        // 发送邮件
        Transport.send(message);
	}
}
