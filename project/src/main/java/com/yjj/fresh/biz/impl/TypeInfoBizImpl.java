package com.yjj.fresh.biz.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.ITypeInfoBiz;
import com.yjj.fresh.enity.TypeInfo;
import com.yjj.fresh.mapper.ITypeInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class TypeInfoBizImpl implements ITypeInfoBiz {

	@Autowired
	private ITypeInfoMapper typeInfoMapper;
	
	@Override
	public Map<String,Object> findAll(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("page"),map.get("rows"))){
			return Collections.emptyMap();
		}
		Map<String,Object> param=new HashMap<String,Object>();
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		int rows=Integer.parseInt(map.get("rows").toString());
		int page= Integer.parseInt(map.get("page").toString());
		
		int pages= typeInfoMapper.total();
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		
		result.put("rows", typeInfoMapper.findByPage(param));
		result.put("total",pages);//total是总记录的条数
		
		return result;
	}

	@Override
	public List<TypeInfo> finds() {
		
		return typeInfoMapper.finds();
	}

}
