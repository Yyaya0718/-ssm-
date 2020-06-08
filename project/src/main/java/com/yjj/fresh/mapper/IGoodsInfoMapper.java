package com.yjj.fresh.mapper;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.GoodsInfo;

public interface IGoodsInfoMapper {
	/**
	 * 查询所有的商品
	 */
	public List<GoodsInfo> findAll(); 
	
	/**
	 * 根据类型分页查询商品
	 */
	public List<GoodsInfo> findByPage(Map<String,Object> map);
	
	/**
	 * 查询某种类型的数据条数
	 */
	public int findByTno(String tno);
	
	/**
	 * 根据商品编号查询商品的详细信息
	 * @param gno
	 * @return
	 */
	public GoodsInfo fingByGno(String gno);
	
	
	public List<GoodsInfo> find(Map<String,Object> map);
	
	/**
	 * 根据订单表中的gnos查询商品信息
	 * @param array
	 * @return
	 */
	public List<GoodsInfo> findOrder(String[] array);
	
	/**
	 * 查找最新的两个单品
	 * @return
	 */
	public List<GoodsInfo> findNew();
	
	//管理员端
	
	/**
	 * 后台管理员的添加商品
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
	public List<GoodsInfo> finds(Map<String,Object> map);
	
	/**
	 * 查询所有的记录条数
	 * @return
	 */
	public int total();
	
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
