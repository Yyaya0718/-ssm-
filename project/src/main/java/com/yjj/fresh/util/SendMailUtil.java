package com.yjj.fresh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailUtil {
	private String sendEmail;//发件箱
	private String pwd;//发件箱的授权码
	
	
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * 发送html格式的邮件
	 * @param receiveEmail	收件箱
	 * @param name	称呼
	 * @param code	验证码
	 * @return
	 */
	public boolean sendHtmlMail(String receiveEmail,String name,String code){
		if(StringUtil.checkNull(receiveEmail,name,code)){
			return false;
		}
		
		try {
			JavaMailSenderImpl senderImpl=new JavaMailSenderImpl();
			
			//邮箱的主机  如果是qq邮箱就是smtp.qq.com
			senderImpl.setHost("smtp.163.com");
			//编码集
			senderImpl.setDefaultEncoding("utf-8");
			
			//建立邮件消息，我们需要发送的是html格式邮件
			MimeMessage mimeMessage=senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
			
			//设置收件人，寄件人
			messageHelper.setTo(receiveEmail);//收件人
			messageHelper.setFrom(sendEmail);//发件人
			messageHelper.setSubject("天天生鲜");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			String str="<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>"
					+"<p style='font-size:20px;font-weight:blod;'>尊敬的："+ name +",您好</p>"
					+"<p style='text-indent:2em;font-size:20px'>欢迎使用天天生鲜注册功能，您本次的注册码是"
					+"<span style='font-size:30px; font-weight:blod; color:red;'>"+ code +"</span>"
					+",3分钟之内有效，请尽快使用!</p><p style='text-align:right; padding-right:20px;'>"
					+"<a href='http://www.hyycinfo.com' style='font-size18px;'>衡阳市源辰信息技术有限公司技术部</a></p>"
					+"<span style='font-size:18px; float:right; margin-right:60px;'>"+ sdf.format(new Date()) +"</span></body></html>";
			
			//设置邮件的正文
			messageHelper.setText(str,true);
			
			//发件箱的名称
			senderImpl.setUsername(sendEmail);
			//发件箱的密码  状态码
			senderImpl.setPassword(pwd);
			
			Properties prop=new Properties();
			prop.put("mail.smtp.auth", "true");//让服务器去认证用户名和密码
			prop.put("mail.smtp.timeout", "2500");//连接超时时间
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(mimeMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
