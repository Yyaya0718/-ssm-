package com.yjj.fresh.mapper;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.AdminInfo;

public interface IAdminInfoMapper {

	/**
	 * 管理员端的登录
	 * @param map
	 * @return
	 */
	public AdminInfo login(Map<String, Object> map);
	
	/**
	 * 查询所有管理员信息
	 */
	public List<AdminInfo> findAll();
	
	/**
	 * 管理员信息的添加
	 * @param member
	 * @return
	 */
	public int Add(AdminInfo admin);
	
	/**
	 * 更新密码
	 * @param aid
	 * @param pwd
	 * @return
	 */
	public int updatePwd(Map<String,Object> map);
	
	/**
	 * 删除某一个管理员
	 * @param aid
	 * @return
	 */
	public int del(String aid);

}
