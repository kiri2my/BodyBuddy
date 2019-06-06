package com.bodybuddy.hey;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.service.MemberManagemant;

//test12344
/**
 * Handles requests for the application home page.
 */
@Controller

public class HanHomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	MemberManagemant mm;
	
	HttpSession session;

	ModelAndView mav;

	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public String login(Model model) {

		return "loginJoinFrm/forget";
	}

	@RequestMapping(value = "/checkid", method = RequestMethod.POST)
	@ResponseBody
	public int checkId(@RequestBody String m_id) {
		System.out.println(" 아이디찾는다! ");
		int checkcon = mm.checkId(m_id);
		System.out.println();
		return checkcon;
	}
	@RequestMapping(value = "/forgetid", method = RequestMethod.POST)
	public ModelAndView forgetId(Member mb) {
		System.out.println(" 잊은아이디찾는다! ");
		mav = mm.forgetId(mb);
		
		return mav;
	}


}
