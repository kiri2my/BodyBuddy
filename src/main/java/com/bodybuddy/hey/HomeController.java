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
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class HomeController {
//test111
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	MemberManagemant mm ;
	
	@Autowired
	HttpSession session;
	
	ModelAndView mav;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "main";
	}
	@RequestMapping(value = "/nomalmemberjoin", method = RequestMethod.POST)
	public ModelAndView nomalmemberjoin(Member mb) {

		mav = mm.nomalmemberjoin(mb);
		System.out.println(" 노말 조인 ");

		return mav;
	}

	
}
