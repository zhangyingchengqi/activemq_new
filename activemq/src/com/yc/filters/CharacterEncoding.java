package com.yc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 核心功能是解决所有的参数编码问题
 *	记得别忘了在web.xml中的配置
 */
public class CharacterEncoding implements Filter {
	
	private String encoding="utf-8";//编码集
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//将arg0 arg1转为http协议
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		//使用过滤器
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(arg0.getInitParameter("encoding")!=null){//如果有配置参数
			encoding=arg0.getInitParameter("encoding");
		}
	}
}
