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
	private MemberDao mDao;
	@Autowired
	private HttpSession session; //request는 권장하지 않음
	
	ModelAndView mav;

	public ModelAndView nomalmemberjoin(Member mb) {
		mav = new ModelAndView();
		String view = null;
		//비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		
		if(mDao.nomalmemberjoin(mb)) {
			view = "loginFrm";
			mav.addObject("check", 1); //회원가입 성공
		}else {
			view = "join";
		}
		mav.setViewName(view);
		
		return mav;
	}


}
