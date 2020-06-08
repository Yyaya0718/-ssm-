package com.yjj.fresh.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.OrderItemInfo;
import com.yjj.fresh.enity.TypeInfo;

public class OrderUtil {						//80-2,80-2
	public static Map<String,Object> getMap(List<OrderItemInfo> list,List<TypeInfo> typeInfo){
		Integer[] types=new Integer[typeInfo.size()+1];
		for(int i=0;i<=typeInfo.size();i++){
			types[i]=0;
		}
		
		String[] temp;
		for(int i=0;i<list.size();i++){
			
			temp=list.get(i).getPrice().split("-");
			//什么索引下表代表这个类型的总的消费金额
			types[Integer.parseInt(temp[1])-1]+=Integer.parseInt(temp[0]);
		}
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("price", types);
		result.put("type", typeInfo);
		return result;
	}
}
