package com.http.util;


import java.lang.reflect.Type;
import com.google.gson.Gson;
/**
 * Java对象和JSON字符串相互转化工具类
 * 
 * 
 */
public final class JsonUtil {
	
	public JsonUtil(){}
	
    /**
     * 对象转换成json字符串
     * @param obj 
     * @return 
     */
    public String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type
     * @return 
     */
    public <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type 
     * @return 
     */
    public <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

}

