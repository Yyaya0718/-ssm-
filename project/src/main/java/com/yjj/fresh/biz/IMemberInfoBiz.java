package com.yjj.fresh.biz;

import java.util.Map;

import com.yjj.fresh.enity.MemberInfo;

public interface IMemberInfoBiz {
	/**
	 * 登录操作
	 * @param map
	 * @return
	 */
	public MemberInfo login(Map<String,Object> map);
	
	/**
	 * 注册
	 */
	public int reg(MemberInfo member);
	
	
	//后台管理员
	
	/**
	 * 查询所有的会员信息
	 * @return
	 */
	public Map<String,Object> findAll(Integer page,Integer rows);
	
	/**
	 * 封禁与解封操作
	 * @param map
	 * @return
	 */
	public int fridden(Map<String,Object> map);

	/**
	 * 删除会员信息
	 * @param mno
	 * @return
	 */
	public int delete(Integer mno);

	/**
	 * 根据姓名和邮箱查询
	 * @param member
	 * @return
	 */
	public int finds(MemberInfo member);

	/**
	 * 找回密码
	 * @param member
	 * @return
	 */
	public int updatePwd(MemberInfo member);
}
