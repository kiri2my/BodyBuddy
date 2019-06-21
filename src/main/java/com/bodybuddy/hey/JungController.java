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
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	public ModelAndView trainerListC(String id) {
		Member mb = (Member)session.getAttribute("mb");
		id = mb.getM_id();
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
	public ModelAndView memberList(Locale locale, Model model) {
		Member mb = (Member)session.getAttribute("mb");
		Question qu = new Question();
		System.out.println("�뿉�뱶踰� 硫붿떆吏�");
		String id = mb.getM_id();
		System.out.println("�뿉�뱶肄붾뱶"+mb.getAd_code());
		System.out.println(id);
		mav=js.getAdvertisemanage(id);
		System.out.println("�뵒鍮꾩퐫�뱶 "+mb.getAd_code());
		System.out.println("�뵒鍮꾩퐫�뱶qu "+qu.getAd_code());
		System.out.println("advertisemanage controller");
		
		return mav;
		/* return "manage/advertisemanage"; */
	}
	
	@RequestMapping(value = "/advertisewritefrm", method = RequestMethod.GET)
	public ModelAndView advertisewirtefrm() {
		System.out.println("advertisewritefrm controller");
		Member mb = (Member) session.getAttribute("mb");
		mav=js.advertisewriterfrm(mb);
		
		return mav;
	}
	
	@RequestMapping(value = "/advertisemodifyfrm", method = RequestMethod.GET)
	public String advertisemodifyfrm(Locale locale, Model model) {
		System.out.println("advertisemodifyfrm controller");
		return "manage/advertisemodifyfrm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/profileModifyT" ,method = RequestMethod.GET)
	public ModelAndView profileModifyT() {
		System.out.println("/profileModifyT �떆�옉");
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("李띻린�쟾");
		System.out.println(mb.getM_id());
		System.out.println("李띻린�썑");
		String m_id = mb.getM_id();
	
		System.out.println(m_id);
		System.out.println("profileModifyT controller");
		mav = js.getProfileList(m_id);
		System.out.println("�걹");
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
	public ModelAndView acceptrequest(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		String cName =request.getParameter("cName");
		
		
		System.out.println("cName : "+cName);
		
		ModelAndView html = js.acceptrequest(mb,cName);
		
		return html;
	}
	/* , MultipartHttpServletRequest multi */
	
	@RequestMapping(value = "/adinsert",produces = "application/json; charset=utf8")
	public ModelAndView adinsert(Question adadd,HttpServletRequest request) {
		System.out.println("HI INSERT CONTROLLER");
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("adinsert = "+mb.getM_id());
		String op_trainer =request.getParameter("op_trainer");
		System.out.println("12323"+op_trainer);
		adadd.setAd_name(mb.getM_id());
		String[] day = request.getParameterValues("day");
		mav=js.adinsert(adadd,day,op_trainer);
		
		return mav;
	}
	

	
	@RequestMapping(value = "/profileComplete")
	public ModelAndView profileComplete(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		String t_career=request.getParameter("t_career");
		
		mav = js.profileComplete(mb,t_career);
		
		return mav;
	}
	
	
	
}
