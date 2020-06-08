package com.yjj.fresh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.IMemberInfoBiz;
import com.yjj.fresh.enity.MemberInfo;
import com.yjj.fresh.mapper.IMemberInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class MemberInfoBizImpl implements IMemberInfoBiz{

	@Autowired
	private IMemberInfoMapper memberInfoMapper;
	
	@Override
	public MemberInfo login(Map<String, Object> map) {
		if(StringUtil.checkNull(map.get("nickName"),map.get("pwd"))){
			return null;
		}
		return memberInfoMapper.login(map);
	}

	@Override
	public int reg(MemberInfo member) {
		if(StringUtil.checkNull(member.getNickName(),member.getPwd(),member.getEmail())){
			return -4;
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		String regDate=sdf.format(d);
		member.setRegDate(regDate);
		
		return memberInfoMapper.reg(member);
	}

	@Override
	public Map<String, Object> findAll(Integer page, Integer rows) {
		if(page<=0 || rows <=0){
			return null;
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rows", memberInfoMapper.findAll(param));
		result.put("total", memberInfoMapper.total());
		return result;
	}

	@Override
	public int fridden(Map<String, Object> map) {
		if(StringUtil.checkNull(map.get("mno"),map.get("status"))){
			return -2;
		}
		return memberInfoMapper.fridden(map);
	}

	@Override
	public int delete(Integer mno) {
		if(mno<=0){
			return -2;
		}
		return memberInfoMapper.delete(mno);
	}

	@Override
	public int finds(MemberInfo member) {
		if(StringUtil.checkNull(member.getNickName(),member.getEmail())){
			return -2;
		}
		return memberInfoMapper.finds(member);
	}

	@Override
	public int updatePwd(MemberInfo member) {
		if(StringUtil.checkNull(member.getPwd(),member.getNickName(),member.getEmail())){
			return -2;
		}
		return memberInfoMapper.updatePwd(member);
	}

}
