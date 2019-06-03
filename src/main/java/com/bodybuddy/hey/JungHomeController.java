package com.bodybuddy.hey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JungHomeController {
	
		ModelAndView mav;
		
		@RequestMapping(value = "/main", method = RequestMethod.GET)
		public String main(Model model) {
		
			return "main";
		}
		@RequestMapping(value = "/main2", method = RequestMethod.GET)
		public String main2(Model model) {
		
			return "main2";
		}
		
		

	}
