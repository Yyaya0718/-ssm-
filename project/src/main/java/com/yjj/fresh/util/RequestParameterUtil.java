package com.yjj.fresh.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParameterUtil {
	public static Map<String, Object> getRequestParameter(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Enumeration<String> params = request.getParameterNames();
		String key = null;
		while(params.hasMoreElements()){
			key = params.nextElement();
			map.put(key, request.getParameter(key));
		}
		return map;
	}
	
	public static Map<String, Object> pageParam(Integer page, Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
}
