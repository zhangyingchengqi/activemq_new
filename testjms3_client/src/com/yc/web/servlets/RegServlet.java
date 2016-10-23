package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.utils.MessageProducerUtil;


@WebServlet("/reg.action")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost( request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(  "reg".equals(op)){
			regOp( request, response);
		}
	}

	/**
	 * 注册: 接到用户注册的信息-》校验->向数据存信息(  用户名，密码，邮箱，状态) ->发邮件
											                             ->向jms服务器发送信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void regOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接信息
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		//Map<String,String[]> map=request.getParameterMap();   // op    uname   email
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("uname", uname);
		map.put("email", email);
		
		//省略校验
		
		//存数据库
		
		
		//发送消息到jms服务器   生产者
		MessageProducerUtil mpu=new MessageProducerUtil();
		mpu.sendMapMessage(map);
		
		/*
		response.sendRedirect("success.jsp");
		*/
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(  "ok"   );
		out.flush();
	}

}
