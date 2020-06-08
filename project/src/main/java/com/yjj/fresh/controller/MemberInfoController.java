package com.yjj.fresh.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjj.fresh.biz.ICartInfoBiz;
import com.yjj.fresh.biz.IMemberInfoBiz;
import com.yjj.fresh.enity.MemberInfo;
import com.yjj.fresh.util.SendMailUtil;


@Controller
@RequestMapping("/member")
public class MemberInfoController{
	
	@Autowired
	private IMemberInfoBiz memberInfoBiz;
	
	@Autowired
	private ICartInfoBiz cartInfoBiz;
	
	@Autowired
	private SendMailUtil sendEmail;
	
	@RequestMapping("/login")
	@ResponseBody
	public int Login(@RequestParam Map<String,Object> map,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		int result=-1;
		MemberInfo member=memberInfoBiz.login(map);
		
		if(member!=null){
			//如果登录成功就要根据这个人的用户mno去查询购物车是否有记录
			int num=cartInfoBiz.findCartNum(member.getMno());
			if(num>=0){
				session.setAttribute("cartNum", num);
			}
			
			if(member.getStatus()==1){
				session.setAttribute("loginUser", member);
				result=2;
				
				//登录成功   判断是否要存cookie
				String loginInfo=map.get("nickName")+"-"+map.get("md");
				Cookie userCookie = new Cookie("LoginInfo",loginInfo);
				URLEncoder.encode(loginInfo,"utf-8");
				if("1".equals(map.get("flag"))){//说明勾选了记住我
					userCookie.setMaxAge(60*60*24*7);//7天
					userCookie.setPath("/");
					
					response.addCookie(userCookie);
				}else{
					userCookie.setMaxAge(0);
					userCookie.setPath("/");
					response.addCookie(userCookie);
				}
			}else{
				result=-3;
			}
			
		}
		return result;
	}

	@RequestMapping("/sendCode")
	@ResponseBody
	public int sendCode(@RequestParam Map<String,Object> map,HttpSession session){
		int result=-1;
		
		//生成验证码
		String code="";
		Random r=new Random();
		for(int i=0;i<6;i++){
			code+=r.nextInt(10);
		}
		
		String uname=map.get("uname").toString();
		String email=map.get("email").toString();
		if("".equals(uname) || "".equals(email)){
			result=-2;
		}else{
			//发送验证码成功
			if(sendEmail.sendHtmlMail(email, uname, code)){
				session.setAttribute("GetCode", code);
				
				//定时器存起来  三分钟之后清除
				TimerTask time=new TimerTask(){

					@Override
					public void run() {
						session.removeAttribute("GetCode");
					}
					
				};
				
				Timer timer=new Timer();
				timer.schedule(time, 180000);//三分之后执行这个任务
				
				result=Integer.parseInt(code);
			}else{
				result=-3;
			}
		}
		
		
		return result;
	}
	
	@RequestMapping("/reg")
	@ResponseBody
	public int reg(HttpSession session ,MemberInfo member){
		//取出验证码，判断验证码是否正确
		String code=session.getAttribute("GetCode").toString();
		if("".equals(code) || code==""){
			return -2;//验证码过期
		}
		
		if(!member.getCode().equals(code)){
			return -3;//验证码错误
		}
		
		return memberInfoBiz.reg(member);
	}

	
	@RequestMapping("/findUname")
	@ResponseBody
	public Map<String,Object> getUname(HttpSession session){
		MemberInfo member=(MemberInfo) session.getAttribute("loginUser");
		int num=Integer.parseInt(session.getAttribute("cartNum").toString());
		if(member==null){
			return null;
		}
		if(num<0){
			return null;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("nickName", member.getNickName());
		map.put("cartNum", num);
		map.put("mno",member.getMno());
		return map; 
	}
	
	
	@RequestMapping("/index")
	public String getIndex(HttpSession session){
		
		return "forward:/WEB-INF/front/index.html";
	}
	
	@RequestMapping("/finds")
	@ResponseBody
	public Map<String,Object> findAll(Integer page,Integer rows){
		return memberInfoBiz.findAll(page,rows);
	}
	
	
	@RequestMapping("/frid")
	@ResponseBody
	public int fridden(@RequestParam Map<String,Object> map){
		return memberInfoBiz.fridden(map);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public int delete(Integer mno){
		return memberInfoBiz.delete(mno);
	}
	
	@RequestMapping("/findByEmail")
	@ResponseBody
	public int finds(MemberInfo member){
		return memberInfoBiz.finds(member);
	}
	
	
	@RequestMapping("/updatePwd")
	@ResponseBody
	public int updatePwd(MemberInfo member){
		return memberInfoBiz.updatePwd(member);
	}
}
