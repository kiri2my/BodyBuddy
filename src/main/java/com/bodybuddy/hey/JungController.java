package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.service.JungService;
import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class JungController {
	@Autowired
	JungService js;
	@Autowired
	HttpSession session;
	
	ModelAndView mav;
	
	@RequestMapping(value = "/questionlist")
	public ModelAndView trainerListC(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		
		mav = js.getQuestionList(id);
		
		return mav;
	}
	
	@RequestMapping(value = "/trainer", method = RequestMethod.GET)
	public String trainer(Locale locale, Model model) {
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("mb.getM_id() = "+mb.getM_id());
		System.out.println("mb.getM_kind() = "+mb.getM_kind());
		System.out.println("mb.getM_name() = "+mb.getM_name());
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
		Member mb = (Member) session.getAttribute("mb");
		mav=js.advertisewriterfrm(mb);
		
		return "manage/advertisewritefrm";
	}
	
	@RequestMapping(value = "/advertisemodifyfrm", method = RequestMethod.GET)
	public String advertisemodifyfrm(Locale locale, Model model) {
		System.out.println("advertisemodifyfrm controller");
		return "manage/advertisemodifyfrm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/profileModifyT" ,method = RequestMethod.GET)
	public ModelAndView profileModifyT() {
		System.out.println("/profileModifyT 시작");
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("찍기전");
		System.out.println(mb.getM_id());
		System.out.println("찍기후");
		String m_id = mb.getM_id();
		

		System.out.println(m_id);
		System.out.println("profileModifyT controller");
		mav = js.getProfileList(m_id);
		System.out.println("끝");
		return mav;
	}
	
	@RequestMapping(value = "/TfindC",produces = "application/json; charset=utf8")
	@ResponseBody
	public String TfindC(HttpServletRequest request) {
		String cName =request.getParameter("cName");
		System.out.println("cName : "+cName);
		
		
		
		String html = js.getTfindC(cName);
		
		return html;
	}
	
	
	@RequestMapping(value = "/cancel",produces = "application/json; charset=utf8")
	public @ResponseBody String cancel(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		/* String cName =request.getParameter("cName"); */
		
		String html = js.cancel(mb);
		
		return html;
	}
	
	@RequestMapping(value = "/acceptrequest",produces = "application/json; charset=utf8")
	@ResponseBody
	public String acceptrequest(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		String cName =request.getParameter("cName");
		System.out.println("cName : "+cName);
		
		String html = js.acceptrequest(mb,cName);
		
		return html;
	}
	
	@RequestMapping(value = "/adinsert",produces = "application/json; charset=utf8")
	public ModelAndView adinsert(Question adadd,HttpServletRequest request) {
		
		String[] day = request.getParameterValues("day");
		String[] op_content = request.getParameterValues("op_content");
		mav=js.adinsert(adadd,day,op_content);
		
		return mav;
	}
	

	
	@RequestMapping(value = "/profileComplete")
	public ModelAndView profileComplete(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		String name = request.getParameter("name");
		
		//mav = js.profileComplete(MB);
		
		return mav;
	}
	
	
	
}
