package com.bodybuddy.hey.service;

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
	MemberDao mDao;
	@Autowired
	private HttpSession session; //request는 권장하지 않음
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
		System.out.println("exaddr = " + mb.getM_exaddr());
		System.out.println("kind = " + mb.getM_kind());
		
			
		if (mDao.normalMemberJoin(mb)) {
			System.out.println("얍얍얍");
			view = "loginJoinFrm/loginFrm";
		} else {
			view = "loginJoinFrm/join";
		}
		mav.setViewName(view);
		System.out.println("매니지맨트 종료");

		return mav;
	}
}