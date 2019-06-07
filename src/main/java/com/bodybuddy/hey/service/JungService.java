package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.MemberDao;
import com.google.gson.Gson;

@Service
public class JungService {
	@Autowired
	private MemberDao mDao;
	
	private HttpSession session; 
	
	Member m;
	
	ModelAndView mav;
	public ModelAndView getProfileList(String id) {
		System.out.println("정 서비스");
		mav = new ModelAndView();
		String view = null;
		
		m= new Member();
		m = mDao.getProfileList(id);
		System.out.println("이거다");
		if(null!=m) {
			System.out.println("profile list select success");
			view = "manage/profileModifyT";
			mav.setViewName(view);
			mav.addObject("m", m);
		}else {
			System.out.println("profile list select error");
			view = "redirect:profileModifyT.jsp";
			mav.setViewName(view);
		}
		
		return mav;
	}
	public String getTfindC(String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		
		m = new Member();
		
		m.setC_bname(name);
		
		System.out.println("name="+name);
		
		List<Member> mList = new ArrayList<Member>();
		mList = mDao.getTfindC(m);
		Gson gStr=new Gson();
		String result=gStr.toJson(mList);
		System.out.println("DB 다녀왔어요!");
		if(0!=mList.size()) {
			System.out.println("company search select success");
			/*
			 * view = "manage/profileModifyT"; mav.setViewName(view);
			 */
			mav.addObject("mList", mList);
		}else {
			System.out.println("company search select error");
			/* view = "redirect:profileModifyT.jsp"; */
			mav.setViewName(view);
		}
		
		return result;
		
	}
	public ModelAndView questionList(String id) {
		System.out.println("정 서비스");
		mav = new ModelAndView();
		String view = null;
		
		m= new Member();
		m = mDao.getProfileList(id);
		System.out.println("이거다");
		if(null!=m) {
			System.out.println("profile list select success");
			view = "manage/profileModifyT";
			mav.setViewName(view);
			mav.addObject("m", m);
		}else {
			System.out.println("profile list select error");
			view = "redirect:profileModifyT.jsp";
			mav.setViewName(view);
		}
		return mav;
	}
}