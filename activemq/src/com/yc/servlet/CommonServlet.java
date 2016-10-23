package com.yc.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * 这个类用于增强servlet的功能,这个类中加入所有的servlet的功能
 * 1.输出json数据的功能
 *	设计模式:适配器
 */

public class CommonServlet extends HttpServlet {

	private static final long serialVersionUID = 6892375450419011816L;

	/**
	 * 以JSON的格式向页面输出数据
	 * @param response
	 * @param jm
	 */
	public void outData(HttpServletResponse response,Object jm){
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
			//将jsonmodel的对象转换成json格式
			//对象 ： {键 :值,键:值};
			Gson gson=new Gson();
			String returnvalue=gson.toJson(jm);
			out.println(returnvalue);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}
