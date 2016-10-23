package com.yc.utils;

import java.lang.reflect.Type;
import com.google.gson.Gson;

public class GsonUtil {

	private static Gson gson=new Gson();
	
	//将一个对象转为json格式的数据
	public static String getJsonString(Object obj){
		return gson.toJson(obj);
	}
	
	//将一个json格式的数据转为对象
	public static <T> Object JsonToObject(String jsonString ,Type objectType){
		return gson.fromJson(jsonString, objectType);
	}
}
