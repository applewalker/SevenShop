package com.http.util;


import java.lang.reflect.Type;
import com.google.gson.Gson;
/**
 * Java�����JSON�ַ����໥ת��������
 * 
 * 
 */
public final class JsonUtil {
	
	public JsonUtil(){}
	
    /**
     * ����ת����json�ַ���
     * @param obj 
     * @return 
     */
    public String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json�ַ���ת�ɶ���
     * @param str  
     * @param type
     * @return 
     */
    public <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json�ַ���ת�ɶ���
     * @param str  
     * @param type 
     * @return 
     */
    public <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

}

