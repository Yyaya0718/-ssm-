package com.yjj.fresh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjj.fresh.biz.ICartInfoBiz;
import com.yjj.fresh.enity.CartInfo;
import com.yjj.fresh.enity.GoodsInfo;
import com.yjj.fresh.enity.MemberInfo;

@RestController
@RequestMapping("/cart")
public class CartInfoController {
	
	@Autowired
	private ICartInfoBiz cartInfoBiz;
	
	@RequestMapping("/addCart")
	public int add(CartInfo cart,HttpSession session){
		int result=-1;
		
		//取出用户的mno,从session里面
		MemberInfo member=(MemberInfo) session.getAttribute("loginUser");
		int mno=member.getMno();
		if(mno<=0){
			result=-3;
		}else{
			cart.setMno(mno);
			result=cartInfoBiz.add(cart);
			if(result>0){
				//修改存在session里面的cartNum
				//先获取原先的session中的值
				session.setAttribute("cartNum", cartInfoBiz.findCartNum(mno));
			}
		}
		
		return result;
	}
	
	@RequestMapping("/finds")
	public List<GoodsInfo> findAll(HttpSession session){
		MemberInfo member=(MemberInfo) session.getAttribute("loginUser");
		int mno=member.getMno();
		return cartInfoBiz.finds(mno);
	}
	
	@RequestMapping("/del")
	public int delete(String cno){
		return cartInfoBiz.delete(cno);
	}
	
	@RequestMapping("/update")
	public int uodateNum(CartInfo cart,HttpSession session){
		int result =-1;
		MemberInfo member=(MemberInfo) session.getAttribute("loginUser");
		if(member==null){
			result=-2;
		}else{
			cart.setMno(member.getMno());
			result=cartInfoBiz.update(cart);
		}
		return result;
	}
	
	
}
