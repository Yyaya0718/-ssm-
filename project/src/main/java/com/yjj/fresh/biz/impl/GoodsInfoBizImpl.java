package com.yjj.fresh.biz.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.IGoodsInfoBiz;
import com.yjj.fresh.enity.GoodsInfo;
import com.yjj.fresh.mapper.IGoodsInfoMapper;
import com.yjj.fresh.mapper.ITypeInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class GoodsInfoBizImpl implements IGoodsInfoBiz {

	@Autowired
	private IGoodsInfoMapper goodsInfoMapper;
	
	@Autowired
	private ITypeInfoMapper typeInfoMapper;
	
	@Override
	public Map<String,Object> findAll() {
		Map<String,Object> result= new HashMap<String,Object>();
		result.put("goods", goodsInfoMapper.findAll());
		result.put("types", typeInfoMapper.findAll());
		
		return result;
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		if(StringUtil.checkNull(map.get("tno"),map.get("page"))){
			return Collections.emptyMap();
		}
		
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> param=new HashMap<String,Object>();
		int page=Integer.parseInt(map.get("page").toString());
		int rows=10;
		param.put("tno", map.get("tno"));
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		
		//获取分页查询的商品结果
		List<GoodsInfo> goods=goodsInfoMapper.findByPage(param);
		//获取总记录数量
		int pages=goodsInfoMapper.findByTno(String.valueOf(map.get("tno")));
		pages=pages%rows>0 ? (pages/rows)+1 : pages/rows;
		
		result.put("goodsInfo", goods);
		result.put("pages", pages);
		
		return result;
	}

	//后台管理员
	
	
	@Override
	public int add(Map<String, Object> map) {
		if(StringUtil.checkNull(map.get("gname"),map.get("tno"),map.get("intro"))){
			return -3;//说明有空值的属性
		}
		
		return goodsInfoMapper.add(map);
	}

	@Override
	public GoodsInfo fingByGno(String gno) {
		if(StringUtil.checkNull(gno)){
			return null;
		}
		
		return goodsInfoMapper.fingByGno(gno);
	}

	@Override
	public Map<String, Object> findByPage(Integer page, Integer rows) {
		if(page<=0 || rows<=0){
			return null;
		}
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("page", (page-1)*rows);
		param.put("rows", rows);
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rows", goodsInfoMapper.finds(param));
		result.put("total", goodsInfoMapper.total());
		return result;
	}

	@Override
	public int delete(Integer gno) {
		if(gno<=0){
			return -2;
		}
		return goodsInfoMapper.delete(gno);
	}

	@Override
	public int update(Map<String,Object> map) {
		return goodsInfoMapper.update(map);
	}

	@Override
	public List<GoodsInfo> findNew() {
		return goodsInfoMapper.findNew();
	}

	@Override
	public List<GoodsInfo> findTno(String tno) {
		if(StringUtil.checkNull(tno)){
			return null;
		}
		return goodsInfoMapper.findTno(tno);
	}

}
