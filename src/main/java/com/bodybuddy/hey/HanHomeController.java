package com.bodybuddy.hey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//test12344
/**
 * Handles requests for the application home page.
 */
@Controller
public class HanHomeController {
	
	
	ModelAndView mav;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
	
		return "loginJoinFrm/loginFrm";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		
		return "loginJoinFrm/join";
	}
	@RequestMapping(value = "/nomaljoin", method = RequestMethod.GET)
	public String nomaljoin(Model model) {
		
		return "loginJoinFrm/normalJoinFrm";
	}
	@RequestMapping(value = "/companyjoin", method = RequestMethod.GET)
	public String companyjoin(Model model) {
		
		return "loginJoinFrm/companyJoinFrm";
	}
	@RequestMapping(value = "/trainerjoin", method = RequestMethod.GET)
	public String trainerjoin(Model model) {
		
		return "loginJoinFrm/trainerJoinFrm";
	}
	

}
