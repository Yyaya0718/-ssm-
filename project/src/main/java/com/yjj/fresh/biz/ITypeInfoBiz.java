package com.yjj.fresh.biz;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.TypeInfo;


public interface ITypeInfoBiz {

	/**
	 * 管理员段查询所有类型
	 * @return
	 */
	public Map<String,Object> findAll(Map<String,Object> map);
	
	/**
	 * 查询所有类型
	 * @return
	 */
	public List<TypeInfo> finds();
}
