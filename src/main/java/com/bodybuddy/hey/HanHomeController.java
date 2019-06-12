package com.bodybuddy.hey;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class HanHomeController {
	
	@Autowired
	MemberManagemant mm;

	HttpSession session;

	ModelAndView mav;


	@ResponseBody
	@RequestMapping(value = "/checkid", method = RequestMethod.POST)
	public int checkId(String m_id) {
		System.out.println(" 아이디찾는다! ");
		int checkcon = mm.checkId(m_id);

		System.out.println();
		return checkcon;
	}

	@ResponseBody
	@RequestMapping(value = "/checkcompanynum", method = RequestMethod.POST)
	public int checkCompanyNum(String c_num) {

		int checknum = mm.checkCompanyNum(c_num);

		System.out.println();
		return checknum;
	}

	// @RequestMapping(value = "replyInsert", produces = "application/json;
	// charset=utf8")
	@RequestMapping(value = "/forgetidsearch", method = RequestMethod.POST)
	public ModelAndView forgetIdSearch(Member mb) {
		System.out.println(" 잊은아이디찾는다! ");
		mav = mm.forgetId(mb);

		return mav;
	}
	@RequestMapping(value = "/forgetpwsearch", method = RequestMethod.POST)
	public ModelAndView forgetPwSearch(Member mb) {
		System.out.println(" 잊은아이디찾는다! ");
		mav = mm.forgetPw(mb);
		
		return mav;
	}

	@RequestMapping(value = "/forgetid", method = RequestMethod.GET)
	public String forgetid(Model model) {
		System.out.println("아이디 찾기 믿고있었다고!");

		return "loginJoinFrm/forget";
	}
	

	@RequestMapping(value = "/forgetpw", method = RequestMethod.GET)
	public String forgetpw(Model model) {
		System.out.println("비번 찾기 라구!!");

		return "loginJoinFrm/forgetpw";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		session.invalidate();
		//mav.setViewName("home");
		return "loginJoinFrm/loginFrm";
	}



}
