package com.bodybuddy.hey.userClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터 preHandler");
		session=request.getSession();
		String sId = session.getId();
		boolean isnew = session.isNew();
		System.out.println(isnew);
		System.out.println(sId);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		//if(session.getAttribute("mb")==null) {
			//response.sendRedirect("./");//home("/")
			//return false;//비로그인 상태
		//}
		return true;//로그인 상태
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("인터셉터 postHandler");
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
