package com.yjj.fresh.mapper;

import java.util.List;
import java.util.Map;

import com.yjj.fresh.enity.OrderInfo;
import com.yjj.fresh.enity.OrderItemInfo;

public interface IOrderInfoMapper {
	
	/**
	 * 根据cno从购物车中删除商品
	 * @param array
	 * @return
	 */
	public int deleteCart(String[] array);
	
	/**
	 * 更新商品的库存数量
	 * @param list
	 * @return
	 */
	public int updateGoodsNum(List<Map<String,Object>> list);
	
	/**
	 * 添加到订单表
	 * @param map
	 * @return
	 */
	public int addOrderInfo(Map<String,Object> map);
	
	/**
	 * 添加到订单详情表
	 * @param map
	 * @return
	 */
	public int addOrderItem(List<Map<String,Object>> list);
	
	/**
	 * 查询用户购买商品的编号
	 * @param mno
	 * @return
	 */
	public List<Integer>  findgno(Integer mno);
	
	/**
	 * 查询购买的商品信息
	 * @param map
	 * @return
	 */
	public  List<OrderInfo> findOrderItem(Map<String,Object> map);
	
	/**
	 * 根据订单号修状态
	 * 0，未支付
	 * 1，已支付
	 * 2，已发货
	 * @param ono
	 * @return
	 */
	public int update(Map<String,Object> param);
	
	/**
	 * 根据日期查询当天的销售报告
	 * @param date
	 * @return
	 */
	public List<OrderItemInfo> findDate(String date);
	
	/**
	 * 根据日期查询月的销售报告
	 * @param date
	 * @return
	 */
	public List<OrderItemInfo> findMonth(Map<String,Object> map);
	
	/**
	 * 查询某个商品的日月营销额
	 * @param map
	 * @return
	 */
	public List<OrderItemInfo> findSale(Map<String, Object> map);
}
