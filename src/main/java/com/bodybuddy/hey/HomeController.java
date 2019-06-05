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


	@RequestMapping(value = "/", method = RequestMethod.GET) // method紐낆떆 �븞�븯硫� get,post �몮�떎 諛쏆쓬
	public ModelAndView home() {
		System.out.println("�떆�옉�븳�떎!!!!!!!!!!");
		mav = new ModelAndView();
		mav.setViewName("main"); // 濡쒓렇�씤 �럹�씠吏�濡� �씠�룞
		System.out.println("�떆�옉�뻽�떎!!!!!!!!!!");
		return mav;
	}
	
	@RequestMapping(value = "/normalmemberjoin", method = RequestMethod.POST)
	public ModelAndView normalMemberJoin(Member mb) {
		System.out.println(" �끂留� 議곗씤 �떆�옉 ");
		
		mav = mm.normalMemberJoin(mb);
		System.out.println(" �끂留� 議곗씤 ");
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("濡쒓렇�씤 �뤌�씠�씪援�!");

		return "loginJoinFrm/loginFrm";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		System.out.println("議곗씤�뤌 �씠�씪援�!!");
		return "loginJoinFrm/join";
	}

	@RequestMapping(value = "/nomaljoin", method = RequestMethod.GET)
	public String nomaljoin(Model model) {
		System.out.println("�끂留먯“�씤�뤌 �씠�씪援� !!!");

		return "loginJoinFrm/normalJoinFrm";
	}

	@RequestMapping(value = "/companyjoin", method = RequestMethod.GET)
	public String companyjoin(Model model) {
		System.out.println("而댄뙣�땲議곗씤�씠�씪援�");

		return "loginJoinFrm/companyJoinFrm";
	}

	@RequestMapping(value = "/trainerjoin", method = RequestMethod.GET)
	public String trainerjoin(Model model) {
		System.out.println("�듃�젅�씠�꼫議곗씤�씠�씪援�");

		return "loginJoinFrm/trainerJoinFrm";
	}


	
}
