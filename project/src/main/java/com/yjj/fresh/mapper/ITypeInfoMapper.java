package com.yjj.fresh.mapper;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.TypeInfo;

public interface ITypeInfoMapper {
	
	/**
	 * 查询所有类型信息
	 * @return
	 */
	public List<TypeInfo> findAll();
	
	/**
	 * 管理员段分页查询所有类型
	 * @return
	 */
	public List<TypeInfo> findByPage(Map<String,Object> map);
	/**
	 * 分页查询的总记录数
	 */
	public int total();
	
	/**
	 * 查询所有类型
	 * @return
	 */
	public List<TypeInfo> finds();
}
