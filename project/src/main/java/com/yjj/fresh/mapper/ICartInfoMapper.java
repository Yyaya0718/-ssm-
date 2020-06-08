package com.yjj.fresh.mapper;

import java.util.List;

import com.yjj.fresh.enity.CartInfo;
import com.yjj.fresh.enity.GoodsInfo;

public interface ICartInfoMapper {
	/**
	 * 根据会员号查询购物车商品数量
	 * @param mno
	 * @return
	 */
	public int findCartNum(int mno);
	
	/**
	 * 加入购物车
	 * @param cartInfo
	 * @return
	 */
	public int addCart(CartInfo cartInfo);
	
	
	/**
	 * 查询所有购物车的信息
	 * @return
	 */
	public List<GoodsInfo> finds(Integer mno);
	
	/**
	 * 根据cno删除购物车信息
	 * @param cno
	 * @return
	 */
	public int delete(String cno);
	
	/**
	 * 修改商品在购物车的数量
	 * @param map
	 * @return
	 */
	public int update(CartInfo cartInfo);
}
