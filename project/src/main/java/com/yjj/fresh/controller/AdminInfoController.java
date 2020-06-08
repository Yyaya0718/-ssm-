package com.yjj.fresh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjj.fresh.biz.IAdminInfoBiz;
import com.yjj.fresh.enity.AdminInfo;



@Controller
@RequestMapping("/admin")
public class AdminInfoController {
	
	@Autowired
	private IAdminInfoBiz adminInfoBiz;
	
	@RequestMapping("/login")
	@ResponseBody
	public int Login(@RequestParam Map<String,Object> map,HttpSession session){
		AdminInfo admin=adminInfoBiz.login(map);
		
		if(admin == null){
			return -1;
		}else{
			session.setAttribute("adminLogin", admin);
			return 1;
		}
	}
	
	@RequestMapping("/backs")
	public String toIndex(HttpSession session){
		return "forward:/WEB-INF/backs/index.html";
	}
	
	
	
	@RequestMapping("/name")
	@ResponseBody
	public AdminInfo FindName(HttpSession session){
		AdminInfo admin=(AdminInfo) session.getAttribute("adminLogin");
		
		if(admin!=null){
			return admin;
		}
		
		return null;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<AdminInfo> findAll(){
		return adminInfoBiz.findAll();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public int Add(AdminInfo admin){
		return adminInfoBiz.Add(admin);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int updatePwd(@RequestParam Map<String,Object> map){
		return adminInfoBiz.updatePwd(map);
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(String aid){
		return adminInfoBiz.del(aid);
	}
	
}
