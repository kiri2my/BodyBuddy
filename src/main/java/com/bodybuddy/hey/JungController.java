package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JungController {
	@RequestMapping(value = "/trainer", method = RequestMethod.GET)
	public String trainer(Locale locale, Model model) {
		
		System.out.println("good");
		return "manage/trainer/trainer";
	}
	@RequestMapping(value = "/advertisemanage", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model) {
		

		System.out.println("advertisemanage controller");
		return "manage/advertisemanage";
	}
	
	@RequestMapping(value = "/advertisewritefrm", method = RequestMethod.GET)
	public String advertisewirtefrm(Locale locale, Model model) {
		

		System.out.println("advertisewritefrm controller");
		return "manage/advertisewritefrm";
	}
	
	@RequestMapping(value = "/advertisemodifyfrm", method = RequestMethod.GET)
	public String advertisemodifyfrm(Locale locale, Model model) {
		

		System.out.println("advertisemodifyfrm controller");
		return "manage/advertisemodifyfrm";
	}
}
