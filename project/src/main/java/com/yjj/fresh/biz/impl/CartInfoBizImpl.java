package com.yjj.fresh.biz.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjj.fresh.biz.ICartInfoBiz;
import com.yjj.fresh.enity.CartInfo;
import com.yjj.fresh.enity.GoodsInfo;
import com.yjj.fresh.mapper.ICartInfoMapper;
import com.yjj.fresh.util.StringUtil;

@Service
public class CartInfoBizImpl implements ICartInfoBiz{

	@Autowired
	private ICartInfoMapper cartInfoMapper;

	@Override
	public int findCartNum(int mno) {
		if(mno<=0){
			return -2;
		}
		return cartInfoMapper.findCartNum(mno);
		
	}

	@Override
	public int add(CartInfo cartInfo) {
		if(StringUtil.checkNull(cartInfo.getGno(),cartInfo.getMno(),cartInfo.getNum())){
			return -2;
		}
		return cartInfoMapper.addCart(cartInfo);
	}

	@Override
	public List<GoodsInfo> finds(Integer mno) {
		if(mno<=0){
			Collections.emptyList();
		}
		return cartInfoMapper.finds(mno);
	}

	@Override
	public int delete(String cno) {
		if(StringUtil.checkNull(cno)){
			return -2;
		}
		return cartInfoMapper.delete(cno);
	}

	@Override
	public int update(CartInfo cart) {
		if(StringUtil.checkNull(cart.getGno(),cart.getMno(),cart.getNum())){
			return -3;
		}
		return cartInfoMapper.update(cart);
	}


}
