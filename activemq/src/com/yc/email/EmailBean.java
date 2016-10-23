package com.yc.email;

import java.io.Serializable;
import java.util.Arrays;
/**
 * 邮件信息类
 */
public class EmailBean implements Serializable{

	private static final long serialVersionUID = -7535442441942270014L;

	private String title;//邮件标题
	private String contents;//邮件内容
	private String receiver;//收信人的号码
	private String contentsType="text/html;charset=UTF-8";//邮件内容格式
	
	public EmailBean() {
		super();
	}
	public EmailBean(String title, String contents, String receiver) {
		super();
		this.title = title;
		this.contents = contents;
		this.receiver = receiver;
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
	
	public String getContentsType() {
		return contentsType;
	}
	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	@Override
	public String toString() {
		return "EmailBean [title=" + title + ", contents=" + contents
				+ ", receiver=" + receiver + ", contentsType=" + contentsType
				+ "]";
	}
	
}