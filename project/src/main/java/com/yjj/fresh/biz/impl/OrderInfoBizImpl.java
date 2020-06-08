package com.yjj.fresh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjj.fresh.biz.IOrderInfoBiz;
import com.yjj.fresh.enity.AddrInfo;
import com.yjj.fresh.enity.GoodsInfo;
import com.yjj.fresh.enity.OrderInfo;
import com.yjj.fresh.enity.OrderItemInfo;
import com.yjj.fresh.enity.TypeInfo;
import com.yjj.fresh.mapper.IAddrInfoMapper;
import com.yjj.fresh.mapper.IGoodsInfoMapper;
import com.yjj.fresh.mapper.IOrderInfoMapper;
import com.yjj.fresh.mapper.ITypeInfoMapper;
import com.yjj.fresh.util.OrderUtil;
import com.yjj.fresh.util.StringUtil;

@Service
public class OrderInfoBizImpl implements IOrderInfoBiz{

	@Autowired
	private IAddrInfoMapper addrInfoMapper;
	
	@Autowired
	private IGoodsInfoMapper goodsInfoMapper;
	
	@Autowired
	private IOrderInfoMapper orderInfoMapper;
	
	@Autowired
	private ITypeInfoMapper typeInfoMapper;

	@Override
	public Map<String, Object> findAll(String gnos, Integer mno) {
		if(StringUtil.checkNull(gnos) || mno<=0){
			return null;
		}
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		//查询这个用户的所有地址信息
		List<AddrInfo> addr=addrInfoMapper.findAll(mno);
		result.put("addrInfo", addr);
		
		String[] array=gnos.split(",");
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("mno", mno);
		param.put("array", array);
		List<GoodsInfo> goodsInfo=goodsInfoMapper.find(param);
		result.put("goodsInfo", goodsInfo);
		
		return result;
	}

	/**
	 * 提交订单 使用事务
	 * {cno=2-3-4, gno=17,16,15, num=4,1,4, price=264,15,48, totalPrice=327, ano=1}
	 */
	@Transactional
	public int orderPay(Map<String, Object> map) {
		int result=-1;
		//1.首先去购物车删除此商品
		String[] cnoArray=map.get("cno").toString().split("-");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String odate=sdf.format(new Date());//下单时间
		result=orderInfoMapper.deleteCart(cnoArray);
		Random r=new Random();
		String ono=r.nextInt(20)+"-"+odate;
		
		//2.修改goodsinfo表中的库存
		List<Map<String,Object>> param=new ArrayList<Map<String,Object>>();
		Map<String,Object> maps;
		String[] gnos=map.get("gno").toString().split(",");
		String[] nums=map.get("num").toString().split(",");
		String[] prices=map.get("price").toString().split(",");
		for(int i=0;i<gnos.length;i++){
			maps=new HashMap<String,Object>();
			maps.put("gno", gnos[i]);
			maps.put("num", nums[i]);
			maps.put("price",prices[i]);
			maps.put("ono",ono);
			param.add(maps);
		}
		result=orderInfoMapper.updateGoodsNum(param);
		
		//3.添加信息值订单表
		
		map.put("odate", odate);
		map.put("ono", ono);
		result=orderInfoMapper.addOrderInfo(map);
		
		
		//4.添加信息到订单详情表
		result=orderInfoMapper.addOrderItem(param);
		
		return result;
	}

	@Override
	public List<OrderInfo> findByPage(Integer mno) {
		List<Integer> list =orderInfoMapper.findgno(mno);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("mno", mno);
		return orderInfoMapper.findOrderItem(map);
	}

	@Override
	public int update(String ono) {
		if(StringUtil.checkNull(ono)){
			return -2;
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("ono", ono);
		param.put("date", date);
		
		return orderInfoMapper.update(param);
	}

	@Override
	public Map<String, Object> findDate(String date) {
		List<OrderItemInfo> list=orderInfoMapper.findDate(date);//{[80-3],[10-1,15-1]}
		List<TypeInfo> typeInfo=typeInfoMapper.finds();
		
		Map<String,Object> result=OrderUtil.getMap(list, typeInfo);
		return result;
	}

	@Override
	public Map<String, Object> findMonth(Map<String, Object> map) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("startTime", map.get("startTime").toString()+" 00:00:00");
		param.put("endTime", map.get("endTime").toString()+" 00:00:00");
		
		List<OrderItemInfo> list=orderInfoMapper.findMonth(param);
		List<TypeInfo> typeInfo=typeInfoMapper.finds();
		
		Map<String,Object> result=OrderUtil.getMap(list, typeInfo);
		return result;
	}

	@Override
	public Map<String, Object> findSale(Map<String, Object> map) {
		List<OrderItemInfo> order=orderInfoMapper.findSale(map);
		int month=Integer.parseInt(map.get("month").toString().split("-")[1].replaceAll("0", ""));//获取到月份2020-04
		int year=Integer.parseInt(map.get("month").toString().split("-")[0]);
		
		String[] monthArray;//存月份的数组
		int length=0;
		if(month==4 || month==6 || month==9 || month==11){
			length=30;
		}else if(month==2 && year/4==0){
			length=29;
		}else if(month==2 && year/4!=0){
			length=28;
		}else{
			length=31;
		}
		monthArray=new String[length];
		Integer[] prices=new Integer[length];
		for(int i=0;i<length;i++){
			monthArray[i]=(i+1)+"日";
			prices[i]=0;
		}
		
		//开始赋值  price:80-2  40-3
		//odate:2020-04-25 10:18:55
		int price;
		int date;
		for(int i=0;i<order.size();i++){
			price=Integer.parseInt(order.get(i).getPrice().split("-")[0]);
			date=Integer.parseInt(order.get(i).getOdate().split(" ")[0].split("-")[2].replaceAll("0", ""));
			prices[date-1]+=price;
		}
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("price", prices);
		result.put("type", monthArray);
		return result;
	}

}
