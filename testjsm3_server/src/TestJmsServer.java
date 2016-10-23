import java.util.Scanner;

import com.yc.jms2.JmsConsumer2;
import com.yc.jms2.JmsProducer;

public class TestJmsServer {
	
	private static int num=10;

	public static void main(String[] args) {
		for( int i=0;i<num;i++){
		   JmsConsumer2 jc=new JmsConsumer2(i);
		   jc.consumer();
		}
		
		JmsProducer jp=new JmsProducer();
		
		
		Scanner sc=new Scanner( System.in);
		String content=null;
		do{
			System.out.println("管理员，请输入你要群发的消息:");
			content=sc.nextLine();
			jp.sendMessage(content);
		}while(  content!=null||   !"".equals(content) );
		
		
		

	}

}
