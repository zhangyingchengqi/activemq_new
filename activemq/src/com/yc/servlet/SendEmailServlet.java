package com.yc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.activemq.Producer;
import com.yc.activemq.process.ActiveMQProducerProcess;
import com.yc.activemq.process.Impl.ActiveMQProducerProcessEmail;
import com.yc.email.EmailBean;
import com.yc.utils.GsonUtil;
import com.yc.utils.JsonModel;

public class SendEmailServlet extends CommonServlet {

	private static final long serialVersionUID = -5696359559047319326L;
	private JsonModel jm;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("sendEamil")){
			sendEamilOp(request,response);
		}
	}

	//发送邮件
	private void sendEamilOp(HttpServletRequest request,
			HttpServletResponse response) {
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		String[] receivers=request.getParameterValues("receiver");//多个收信人
		
		boolean errorFlag=false;//发送邮件失败的标记
		for( String receiver:receivers){
			if(receiver!=null && receiver!=""){//容错处理
				EmailBean email=new EmailBean(title,contents,receiver );
				try {
					//创建一个生产者
					ActiveMQProducerProcess processEmail=new ActiveMQProducerProcessEmail();
					Producer producer=new Producer(processEmail);
					String jsonParams=GsonUtil.getJsonString(email);
					producer.producer(jsonParams);
//					EmailUtil.sendEmail(email);//发送邮件
				} catch (Exception e) {
					errorFlag=true;
					e.printStackTrace();
				}
			}
		}
		jm=new JsonModel();
		if(errorFlag){
			jm.setCode(400);
			jm.setMsg("邮件发送失败...");
		}else{
			jm.setCode(200);
		}
		
		super.outData(response, jm);
	}

}
