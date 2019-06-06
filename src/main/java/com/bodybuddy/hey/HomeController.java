package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.MemberDao;
import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class HomeController {
//test111
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private MemberManagemant mm;
	@Autowired
	HttpSession session;
	
	ModelAndView mav;


	
	
	@RequestMapping(value = "/normalmemberjoin", method = RequestMethod.POST)
	public ModelAndView normalMemberJoin(Member mb) {
		System.out.println(" 노말 조인 시작 ");
		
		mav = mm.normalMemberJoin(mb);
		System.out.println(" 노말 조인 ");
		
		return mav;
	}
	@RequestMapping(value = "/trainermemberjoin", method = RequestMethod.POST)
	public ModelAndView trainerMemberJoin(Member mb) {
		System.out.println(" 트레이너 조인 ");
		
		mav = mm.trainerMemberJoin(mb);
		System.out.println(" 트레이너 조인 끝 ");
		
		return mav;
	}
	@RequestMapping(value = "/companymemberjoin", method = RequestMethod.POST)
	public ModelAndView companyMemberJoin(Member mb) {
		System.out.println(" 업체 조인 ");
		
		mav = mm.companyMemberJoin(mb);
		System.out.println(" 업체 조인 끝 ");
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("로그인 폼이라구!");

		return "loginJoinFrm/loginFrm";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		System.out.println("조인폼 이라구!!");
		return "loginJoinFrm/join";
	}

	@RequestMapping(value = "/nomaljoin", method = RequestMethod.GET)
	public String nomaljoin(Model model) {
		System.out.println("노말조인폼 이라구 !!!");

		return "loginJoinFrm/normalJoinFrm";
	}

	@RequestMapping(value = "/companyjoin", method = RequestMethod.GET)
	public String companyjoin(Model model) {
		System.out.println("컴패니조인이라구");

		return "loginJoinFrm/companyJoinFrm";
	}

	@RequestMapping(value = "/trainerjoin", method = RequestMethod.GET)
	public String trainerjoin(Model model) {
		System.out.println("트레이너조인이라구");

		return "loginJoinFrm/trainerJoinFrm";
	}


	
}
