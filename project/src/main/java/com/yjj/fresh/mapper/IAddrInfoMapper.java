package com.yjj.fresh.mapper;

import java.util.List;

import com.yjj.fresh.enity.AddrInfo;

public interface IAddrInfoMapper {
	
	/**
	 * 根据mno查询这个人的收货地址
	 * @return
	 */
	public List<AddrInfo> findAll(Integer mno);

	/**
	 * 添加收货地址
	 * @param addr
	 * @return
	 */
	public int add(AddrInfo addr);
	
	/**
	 * 设置为默认地址
	 * @param ano
	 * @return
	 */
	public int setDefault(String ano);
}
