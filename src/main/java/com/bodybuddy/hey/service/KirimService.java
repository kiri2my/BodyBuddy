package com.bodybuddy.hey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


import com.bodybuddy.hey.bean.Member;

import com.bodybuddy.hey.dao.KirimDao;
import com.bodybuddy.hey.dao.MemberDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class KirimService {

	 KirimDao kDao;

	@Autowired
	private HttpSession session;
	ModelAndView mav;

	public ModelAndView access(Member mb) {
		System.out.println("access 서비스 왔다");
		System.out.println("1="+mb.getM_id());
		mav=new ModelAndView();
		String view=null;
	
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		//해당 아이디의 암호화된 비번을 가져옴
		String pwdEncode=kDao.getSecurityPwd(mb.getM_id());
		
			System.out.println("2="+pwdEncode);
		if(pwdEncode!=null) { //암호화된 비번이 존재한다면:아이디가 존재
			if(pwdEncoder.matches(mb.getM_pw(), pwdEncode)) {
				session.setAttribute("id", mb.getM_id());
				System.out.println("getmid="+mb.getM_id());
				//로그인 후 회원정보를 3종류로 나눠 화면에 출력하기 위해
				String kind = kDao.getMemberKind(mb.getM_id());
				System.out.println("kind="+kind);
				System.out.println("getid="+mb.getM_id());
				switch(kind) {
				case "n":
					mb = kDao.getNormalInfo(mb.getM_id());
					break;
				case "t":
					mb = kDao.getTrainerInfo(mb.getM_id());
					break;
				case "c":
					mb = kDao.getCompanyInfo(mb.getM_id());
					break;	
				}				
							
				mav.addObject("mb", mb);
				session.setAttribute("mb", mb);
				System.out.println("mb="+mb);
				//forward:url, POST-POST, GET-GET끼리만 가능
				//view="forward:/board";
				//redirect:url, POST-GET 둘다 GET방식만 가능
				view="main";
			}else {//비번오류
				view="loginJoinFrm/loginFrm";
				mav.addObject("loginCheck", "비번오류");
			}
		}else {//아이디오류
			view="loginJoinFrm/loginFrm";
			mav.addObject("loginCheck", "아이디오류");
		}
		mav.setViewName(view);
		return mav;

	}

	/*
	 * public String purchSingle(PaymentHistory ph) { String str="";
	 * ph.setPs_mid((String)session.getAttribute("id"));
	 * 
	 * if(kDao.payInsert(ph)) { Gson gson = new Gson(); String
	 * jsonStr=gson.toJson(str); str="결제성공"; System.out.println("payinsert 성공");
	 * 
	 * }else { System.out.println("payinsert 실패"); str="결제실패"; }
	 * 
	 * 
	 * return str;
	 * 
	 * }
	 */

}
