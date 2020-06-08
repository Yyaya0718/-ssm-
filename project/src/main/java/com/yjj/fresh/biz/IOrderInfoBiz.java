package com.yjj.fresh.biz;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.OrderInfo;

public interface IOrderInfoBiz {
 
	/**
	 * 查询商品已经收货地址
	 * @param gnos
	 * @return
	 */
	public Map<String,Object> findAll(String gnos,Integer mno);
	
	/**
	 * 提交订单
	 * @param map
	 * @return
	 */
	public int orderPay(Map<String,Object> map);

	/**
	 * 分页查询所有订单信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderInfo> findByPage(Integer mno);
	
	/**
	 * 根据订单号修状态
	 * 0，未支付
	 * 1，已支付
	 * 2，已发货
	 * @param ono
	 * @return
	 */
	public int update(String ono);

	/**
	 * 查询当天的销售报告
	 * @param date
	 * @return
	 */
	public Map<String, Object> findDate(String date);

	/**
	 * 查询月销售额
	 * @param map
	 * @return
	 */
	public Map<String, Object> findMonth(Map<String, Object> map);

	/**
	 * 查询某个商品的日月营销额
	 * @param map
	 * @return
	 */
	public Map<String, Object> findSale(Map<String, Object> map);


}
