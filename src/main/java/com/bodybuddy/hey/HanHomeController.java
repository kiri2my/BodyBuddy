package com.bodybuddy.hey;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.service.MemberManagemant;

@Controller
public class HanHomeController {
	@Autowired
	MemberManagemant mm;
	@Autowired
	HttpSession session;
	@Autowired
	private JavaMailSender mailSender;

	ModelAndView mav;

	@ResponseBody
	@RequestMapping(value = "/checkid", method = RequestMethod.POST)
	public int checkId(String m_id) {
		System.out.println(" 아이디찾는다! ");
		int checkcon = mm.checkId(m_id);

		System.out.println();
		return checkcon;
	}
	@ResponseBody
	@RequestMapping(value = "/qa_num", method = RequestMethod.POST)
	public Question qaNum(String qa_num) {
		System.out.println("나만믿으라고!! ");

		Question qa = mm.qaNum(qa_num);
		session.setAttribute("qa", qa);
		System.out.println(qa.getQa_acontent());
		
		return qa;
	}
	@ResponseBody
	@RequestMapping(value = "/questionreplyfrm", method = RequestMethod.POST)
	public String questionReplyFrm(String qa_acontent) {
		System.out.println("나만믿으라고!! ");
		session.getAttribute("questiin");
		String answer = mm.questionReplyFrm(qa_acontent);
		
		
		System.out.println();
		return answer;
	}

	@ResponseBody
	@RequestMapping(value = "/checkcompanynum", method = RequestMethod.POST)
	public int checkCompanyNum(String c_num) {
		System.out.println("123123");

		int checknum = mm.checkCompanyNum(c_num);

		System.out.println();
		return checknum;
	}

	// @RequestMapping(value = "replyInsert", produces = "application/json;
	// charset=utf8")
	@RequestMapping(value = "/forgetidsearch", method = RequestMethod.POST)
	public ModelAndView forgetIdSearch(Member mb) {
		System.out.println(" 잊은아이디찾는다! ");
		mav = mm.forgetId(mb);

		return mav;
	}

	@RequestMapping(value = "/forgetpwsearch", method = RequestMethod.POST)
	public ModelAndView forgetPwSearch(Member mb) {
		System.out.println(" 잊은아이디찾는다! ");
		mav = mm.forgetPw(mb);

		return mav;
	}

	@RequestMapping(value = "/forgetid", method = RequestMethod.GET)
	public String forgetid(Model model) {
		System.out.println("아이디 찾기 믿고있었다고!");

		return "loginJoinFrm/forget";
	}

	@RequestMapping(value = "/forgetpw", method = RequestMethod.GET)
	public String forgetpw(Model model) {
		System.out.println("비번 찾기 라구!!");

		return "loginJoinFrm/forgetpw";
	}

	@RequestMapping(value = "/memberdeletereal", method = RequestMethod.GET)
	public ModelAndView memberDeleteReal(Model model) {
		System.out.println("탈퇴를 시작했다규!");
		mav = mm.memberDeleteReal();
		return mav;
	}

	// mailSending 코드
	@ResponseBody
	@RequestMapping(value = "/sendrndnum")
	public String sendRndNum() {

		System.out.println("메일 보내기");
		String setfrom = "soonchul88@gmail.com"; // 보내는 아이디
		String title = "BodyBuddy 인증번호"; // 제목

		String m_id = (String) session.getAttribute("m_id"); // 세션 가져오자 받는사람아이디
		String certification = mm.getRamdomPassword(10);// 내용 인증번호 디비에 저장
		System.out.println(m_id);
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(m_id); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText("인증번호 : " + certification); // 메일 내용,인증번호

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return certification;
	}


}
