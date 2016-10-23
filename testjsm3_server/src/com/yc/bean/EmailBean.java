package com.yc.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 邮件信息类
 */
public class EmailBean implements Serializable {

	private static final long serialVersionUID = -7535442441942270014L;

	private String title;// 邮件标题
	private String contents;// 邮件内容
	private String uname; // 收信人名
	private String email;// 收信人邮箱

	public EmailBean() {
		super();
	}
	
	

	



	public EmailBean(String title, String contents, String uname, String email) {
		super();
		this.title = title;
		this.contents = contents;
		this.uname = uname;
		this.email = email;
	}







	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}