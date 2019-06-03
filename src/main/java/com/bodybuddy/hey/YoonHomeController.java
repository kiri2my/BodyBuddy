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
//test12344
/**
 * Handles requests for the application home page.
 */
@Controller
public class YoonHomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(YoonHomeController.class);
	
	ModelAndView mav;
	
	@RequestMapping(value = "/infomodifyfrmn", method = RequestMethod.GET)
	public String login(Model model) {
	
		return "manage/infoModifyN";
	}

	

}
