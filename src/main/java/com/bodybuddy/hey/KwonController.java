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

import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class KwonController {

	private static final Logger logger = LoggerFactory.getLogger(KwonController.class);
	@Autowired
	MemberManagemant mm;
	
	@Autowired
	private JavaMailSender mailSender;
	
	ModelAndView mav;

	@RequestMapping(value = "/email")
	public String email(Locale locale, Model model) {

		return "email/emailCheckFrm";
	}
	
	@RequestMapping(value = "/company")
	public String company(Locale locale, Model model) {

		return "manage/company/companyMain";
	}
	
	@RequestMapping(value = "/memberlist")
	public String memberList(Locale locale, Model model) {

		return "manage/memberListC";
	}
	
	@RequestMapping(value = "/trainerlist")
	public String trainerList(Locale locale, Model model) {

		return "manage/trainerListC";
	}
	
	@RequestMapping(value = "/dailycheck")
	public String dailyCheck(Locale locale, Model model) {

		return "manage/dailyCheck";
	}
	
	@RequestMapping(value = "/memberlistc")
	public ModelAndView memberListC(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		
		mav = mm.getMemberList(id);
		
		return mav;
	}
	
	@RequestMapping(value = "/membersearch")
	public ModelAndView memberSearch(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		System.out.println(name);
		System.out.println(id);
		
		mav = mm.getMemberSearch(name,id);
		
		return mav;
	}
	
	@RequestMapping(value = "/trainerlistc")
	public ModelAndView trainerListC(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		
		mav = mm.getTrainerList(id);
		
		return mav;
	}
	
	@RequestMapping(value = "/trainersearch")
	public ModelAndView trainerSearch(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		System.out.println(name);
		System.out.println(id);
		
		mav = mm.getTrainerSearch(name,id);
		
		return mav;
	}

	// mailSending 코드
	@RequestMapping(value = "/sendEmail")
	public String mailSending(HttpServletRequest request) {

		String setfrom = "soonchul88@gmail.com";
		String tomail = request.getParameter("tomail"); // 받는 사람 이메일
		String title = request.getParameter("title"); // 제목
		String content = request.getParameter("content"); // 내용
		
		System.out.println(setfrom);
		System.out.println(tomail);
		System.out.println(title);
		System.out.println(content);
		
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/";
	}
}
