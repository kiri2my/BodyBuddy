package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	ModelAndView mav;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	//soonchul add
	//soonchul add2
	//junggun add
	//junggun add2
	//soonchul add3
	//gilim add
	
<<<<<<< HEAD
	

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) // method명시 안하면
	 * get,post 둘다 받음 public ModelAndView login() { mav = new ModelAndView();
	 * mav.setViewName("loginFrm"); // 로그인 페이지로 이동
	 * 
	 * return mav; }
	 */
=======
<<<<<<< HEAD
	//sanggi add
=======
>>>>>>> 277d1d63fce567148667e56fb0f9327a5a12f47c
>>>>>>> 32ab8de8163ab961f674186f034b9a3d2002b56f
	
}
