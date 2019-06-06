package com.bodybuddy.hey.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.MemberDao;

@Service
public class MemberManagemant {
	@Autowired
	private MemberDao mDao;
	
	private HttpSession session; //request는 권장하지 않음
	
	Member m;
	
	ModelAndView mav;
	
	public ModelAndView normalMemberJoin(Member mb) {
		System.out.println("맴버 매니지맨트 시작");
		mav = new ModelAndView();
		String view = null;
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		System.out.println("암호화");
		System.out.println("id = " + mb.getM_id());
		System.out.println("pw = " + mb.getM_pw());
		System.out.println("name = " + mb.getM_name());
		System.out.println("phone = " + mb.getM_phone());
		System.out.println("birth = " + mb.getM_birth());
		System.out.println("addr = " + mb.getM_addr());
		System.out.println("addr2 = " + mb.getM_addr2());
		System.out.println("kind = " + mb.getM_kind());
		
		mb.setM_addr(mb.getM_addr() +" "+ mb.getM_addr2()); 
		System.out.println("addr = " + mb.getM_addr());
			
		if (mDao.normalMemberJoin(mb)) {
			view = "loginJoinFrm/loginFrm";
		} else {
			view = "loginJoinFrm/join";
		}
		mav.setViewName(view);
		System.out.println("매니지맨트 종료");

		return mav;
	}

	public ModelAndView getMemberList(String id) {
		mav = new ModelAndView();
		String view = null;
		
		List<Member> mList = null;
		System.out.println("getMemberList mDao in");
		mList = mDao.getMemberList(id);
		System.out.println("mList = " +  mList.get(0).getM_name());
		System.out.println("mList size = " + mList.size());
		
		if(0!=mList.size()) {
			System.out.println("member list select success");
			view = "manage/memberListC";
			mav.setViewName(view);
			mav.addObject("mList", mList);
		}else {
			System.out.println("member list select error");
			view = "redirect:memberListC.jsp";
			mav.setViewName(view);
		}
		
		return mav;
	}

	public ModelAndView getMemberSearch(String name, String id) {
		mav = new ModelAndView();
		String view = null;
		
		m = new Member();
		
		m.setM_name(name);
		m.setM_id(id);
		
		
		List<Member> mList = null;
		mList = mDao.getMemberSearch(m);
		System.out.println("mList = " +  mList.get(0).getM_name());
		System.out.println("mList size = " + mList.size());
		
		if(0!=mList.size()) {
			System.out.println("member search select success");
			view = "manage/memberListC";
			mav.setViewName(view);
			mav.addObject("mList", mList);
		}else {
			System.out.println("member search select error");
			view = "redirect:memberListC.jsp";
			mav.setViewName(view);
		}
		
		return mav;
	}

	public ModelAndView getTrainerList(String id) {
		mav = new ModelAndView();
		String view = null;
		
		List<Member> tList = null;
		System.out.println("Trainer select in");
		tList = mDao.getTrainerList(id);
		System.out.println("tList = " +  tList.get(0).getM_name());
		System.out.println("tList size = " + tList.size());
		
		if(0!=tList.size()) {
			System.out.println("trainer list select success");
			view = "manage/trainerListC";
			mav.setViewName(view);
			mav.addObject("tList", tList);
		}else {
			System.out.println("trainer list select error");
			view = "manage/trainerListC";
			mav.setViewName(view);
		}
		
		return mav;
	}

	public ModelAndView getTrainerSearch(String name, String id) {
		mav = new ModelAndView();
		String view = null;
		
		m = new Member();
		m.setM_name(name);
		m.setM_id(id);
		
		List<Member> tList = null;
		tList = mDao.getTrainerSearch(m);
		System.out.println("tList = " +  tList.get(0).getM_name());
		System.out.println("tList size = " + tList.size());
		
		if(0!=tList.size()) {
			System.out.println("tainer search success");
			view = "manage/trainerListC";
			mav.setViewName(view);
			mav.addObject("tList", tList);
		}else {
			System.out.println("tainer search error");
			view = "manage/trainerListC";
			mav.setViewName(view);
		}
		
		return mav;
	}
}