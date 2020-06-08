package com.yjj.fresh.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.IAddrInfoBiz;
import com.yjj.fresh.enity.AddrInfo;
import com.yjj.fresh.mapper.IAddrInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class AddrInfoBizImpl implements IAddrInfoBiz{

	@Autowired
	private IAddrInfoMapper addrInfoMapper;
	
	@Override
	public int add(AddrInfo addr) {
		if(StringUtil.checkNull(addr.getName(),addr.getTel(),addr.getAddr(),addr.getMno(),addr.getProvince(),addr.getCity(),addr.getArea())){
			return -2;
		}
		return addrInfoMapper.add(addr);
	}

	@Override
	public int setDefault(String ano) {
		if(StringUtil.checkNull(ano)){
			return -2;
		}
		return addrInfoMapper.setDefault(ano);
	}

	
}
