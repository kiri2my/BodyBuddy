package com.bodybuddy.hey;

import java.util.Locale;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.service.KwonService;
import com.bodybuddy.hey.service.MemberManagemant;
import com.bodybuddy.hey.service.SalesService;

@Controller
public class KwonController {

	private static final Logger logger = LoggerFactory.getLogger(KwonController.class);
	@Autowired
	MemberManagemant mm;

	@Autowired
	SalesService ss;

	@Autowired
	KwonService ks;

	@Autowired
	HttpSession session;
	
	@Autowired
	private JavaMailSender mailSender;

	ModelAndView mav;


	@RequestMapping(value = "/email")
	public String email(Locale locale, Model model) {
		return "email/emailCheckFrm";
	}

	@RequestMapping(value = "/company")
	public ModelAndView company(HttpServletRequest request) {
		mav = new ModelAndView();
		mav.setViewName("manage/company/companyMain");
		mav.addObject("m_id", request.getParameter("m_id"));
		return mav;
	}

	@RequestMapping(value = "/question")
	public String question(Locale locale, Model model) {
		return "manage/question/questionList";
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

		mav = mm.getMemberSearch(name, id);

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

		mav = mm.getTrainerSearch(name, id);

		return mav;
	}

	@RequestMapping(value = "/normaldailycheck")
	public ModelAndView normalDailyCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = mm.getNormalMemberList(id);

		return mav;
	}

	@RequestMapping(value = "/programdailycheck")
	public ModelAndView programDailyCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = mm.getProgramMemberList(id);

		return mav;
	}

	@RequestMapping(value = "/trainerdailycheck")
	public ModelAndView trainerDailyCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = mm.getTrainerMemberList(id);

		return mav;
	}

	@RequestMapping(value = "/dailycheckinsert")
	public ModelAndView dailyCheckInsert(HttpServletRequest request) {
		System.out.println(request.getParameter("tid"));
		System.out.println(request.getParameter("cid"));
		System.out.println(request.getParameter("status"));

		mav = ks.dailyCheckInsert(request);

		return mav;
	}

	@RequestMapping(value = "/saleshistory")
	public ModelAndView salesHistory(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = ss.getSalesHistory(id);

		return mav;
	}

	@RequestMapping(value = "/workingattitude", produces = "application/text; charset=utf8")
	public @ResponseBody String workingAttitude(HttpServletRequest request) {

		System.out.println("workingAttitude tid =" + request.getParameter("tid"));
		System.out.println("workingAttitude cid =" + request.getParameter("cid"));

		String gson = ks.getworkingAttitude(request);
		System.out.println(gson);
		System.out.println("여기까지");

		return gson;
	}

	@RequestMapping(value = "/programmember", produces = "application/text; charset=utf8")
	public @ResponseBody String programMember(HttpServletRequest request) {

		String gson = ks.getProgramMember(request);
		System.out.println(gson);

		return gson;
	}

	@RequestMapping(value = "/programcheck")
	public ModelAndView programCheckInsert(HttpServletRequest request) {

		mav = ks.programCheckInsert(request);

		return mav;
	}

	@RequestMapping(value = "/normalcheck")
	public ModelAndView normalCheckInsert(HttpServletRequest request) {

		mav = ks.normalCheckInsert(request);

		return mav;
	}

	@RequestMapping(value = "/attended", produces = "application/text; charset=utf8")
	public @ResponseBody String attended(HttpServletRequest request) {

		System.out.println("workingAttitude tid =" + request.getParameter("id"));
		System.out.println("workingAttitude cid =" + request.getParameter("code"));

		String gson = ks.getAttended(request);
		System.out.println(gson);

		return gson;
	}

	@RequestMapping(value = "/infomodifyc")
	public ModelAndView infoModifyC(HttpServletRequest request) {
		mav = new ModelAndView();
		mav.setViewName("infoModifyC");
		String id = request.getParameter("id");
		mav = ks.getInfomodifyC(id);
		return mav;
	}

	// mailSending 코드


		@RequestMapping(value="/sendEmail1")
		public String mailSending() {
			System.out.println("메일 보내기");
			String setfrom = "soonchul88@gmail.com"; //보내는 아이디
			String title = "BodyBuddy 인증번호"; // 제목
			Member mb = (Member) session.getAttribute("mb"); //세션 가져오자
			String m_id = mb.getM_id(); // 받는사람 아이디
			String certification = mm.getRamdomPassword(10);//내용 인증번호 디비에 저장
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(m_id); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText("인증번호 : "+certification); // 메일 내용,인증번호

				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}

			return "redirect:/";

		}
}
