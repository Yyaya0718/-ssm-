package com.yjj.fresh.biz;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.GoodsInfo;

public interface IGoodsInfoBiz {
	/**
	 * 查询所有的商品
	 */
	public Map<String,Object> findAll(); 
	
	/**
	 * 根据类型分页查询商品
	 */
	public Map<String,Object> findByPage(Map<String,Object> maps);
	/**
	 * 根据商品编号查询商品的详细信息
	 * @param gno
	 * @return
	 */
	public GoodsInfo fingByGno(String gno);
	
	/**
	 * 查找最新的两个单品
	 * @return
	 */
	public List<GoodsInfo> findNew();
	
	//后台功能
	
	/**
	 * 后台商品的添加
	 * @param map
	 * @return
	 */
	public int add(Map<String,Object> map);
	
	/**
	 * 分页查询所有的商品信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String,Object> findByPage(Integer page,Integer rows);

	/**
	 * 删除某个商品
	 * @param gno
	 * @return
	 */
	public int delete(Integer gno);

	/**
	 * 商品的修改
	 * @param goods
	 * @return
	 */
	public int update(Map<String,Object> map);

	/**
	 * 根据类别查询所有商品
	 * @param tno
	 * @return
	 */
	public List<GoodsInfo> findTno(String tno);

	
}
