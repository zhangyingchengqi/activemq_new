package com.yc.utils;

import java.io.Serializable;

public class JsonModel implements Serializable{
	/**
	 * JSON格式类
	 */
	private static final long serialVersionUID = -2168116602318482558L;

	private Integer code;
	private String msg;
	private Object obj;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
