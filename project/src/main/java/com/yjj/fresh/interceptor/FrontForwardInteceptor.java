package com.yjj.fresh.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 后台页面跳转
 * zzm
 * @author zzm
 * @date 2020年3月15日
 */
public class FrontForwardInteceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if(request.getSession().getAttribute("loginUser") == null){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('请先登录');location.href='../../login.html'</script>");
			out.flush();
			return false;
		}
		String path = request.getServletPath();;
		request.getRequestDispatcher("/WEB-INF/" + path).forward(request, response);
		return false;
	}
}
