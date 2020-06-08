package com.yjj.fresh.biz;

import com.yjj.fresh.enity.AddrInfo;

public interface IAddrInfoBiz {

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
