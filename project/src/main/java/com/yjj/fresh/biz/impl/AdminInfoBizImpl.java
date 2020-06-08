package com.yjj.fresh.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.IAdminInfoBiz;
import com.yjj.fresh.enity.AdminInfo;
import com.yjj.fresh.mapper.IAdminInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class AdminInfoBizImpl implements IAdminInfoBiz{

	@Autowired
	private IAdminInfoMapper adminInfo;
	
	@Override
	public AdminInfo login(Map<String, Object> map) {
		if(StringUtil.checkNull(map.get("uname"),map.get("pwd"))){
			return null;
		}
		
		return adminInfo.login(map);
	}

	@Override
	public List<AdminInfo> findAll() {
		
		return adminInfo.findAll();
	}

	@Override
	public int Add(AdminInfo admin) {
		if(StringUtil.checkNull(admin.getAname(),admin.getPwd(),admin.getTel())){
			return -2;
		}
		return adminInfo.Add(admin);
	}

	@Override
	public int updatePwd(Map<String,Object> map) {
		if(StringUtil.checkNull(map.get("aid"),map.get("pwd"))){
			return -2;
		}
		return adminInfo.updatePwd(map);
	}

	@Override
	public int del(String aid) {
		if(StringUtil.checkNull(aid)){
			return -2;
		}
		return adminInfo.del(aid);
	}

}
