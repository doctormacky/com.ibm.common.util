package com.ibm.common.utils;

import java.io.IOException;
import java.io.InputStream;

import com.ibm.json.java.JSON;
import com.ibm.json.java.JSONObject;

/**
 * 注意事项一：
 * 目前暂时只打算支持ibm json4j(要对公司忠诚，哼哼)
 * 好像写了两个方法写不下去了. api支持的已经很好了，不用再封装.
 * 
 * @author macky
 *
 */
public class JsonUtil {
	
	public static JSONObject stringToJson(String string) throws
		NullPointerException, IOException {
		JSONObject jsonObj = (JSONObject) JSON.parse(string);
		return jsonObj;
	}
	
	public static JSONObject inputStreamToJson(InputStream is) throws 
		NullPointerException, IOException {
		JSONObject jsonObj = (JSONObject)JSON.parse(is);
		return jsonObj;
	}
	
	
}
