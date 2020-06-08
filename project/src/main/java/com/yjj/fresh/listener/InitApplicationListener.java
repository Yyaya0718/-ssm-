package com.yjj.fresh.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yjj.fresh.util.StringUtil;


/**
 * 用来创建文件上传所需要的目录
 * zzm
 * @author zzm
 * @date 2020年3月15日
 */
@WebListener
public class InitApplicationListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = "pics";
		
		String temp = sce.getServletContext().getInitParameter("uploadpath");
		if(!StringUtil.checkNull(temp)){
			path = temp;
		}
		
		String basePath = sce.getServletContext().getRealPath("/");
		File fl = new File(basePath, "../" + path);
		if(!fl.exists()){
			fl.mkdirs();
		}
	}
}
