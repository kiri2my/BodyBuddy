package com.bodybuddy.hey;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.service.MemberManagemant;
import com.bodybuddy.hey.service.YoonService;
import com.google.gson.Gson;

@Controller
public class HanHomeController {
	@Autowired
	MemberManagemant mm;
	@Autowired
	YoonService ys;
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
	@ResponseBody
	@RequestMapping(value = "/qanum", method = RequestMethod.POST)
	public List<Question> qaNum(String qa_num) {
		List<Question> qList = new ArrayList<Question>();
		Question qau = mm.qaNum(qa_num);
		Question qa = (Question) session.getAttribute("qa");
		qList.add(qa);
		return qList;
	}
	@ResponseBody
	@RequestMapping(value = "/questionreply", method = RequestMethod.POST)
	public boolean questionReply(String qa_acontent,String qa_num) {
		System.out.println("답변 등록 중!! ");
		System.out.println(qa_acontent);
		System.out.println("home,qa 번호"+qa_num);
		boolean answer = mm.questionReply(qa_acontent,qa_num);
		
		System.out.println("갔다오기 성공");
		return answer;
	}
	@ResponseBody
	@RequestMapping(value = "/qnacheck", method = RequestMethod.POST)
	public List<Question> qNaCheck(String qa_num) {
		System.out.println("답변 확인 중!! ");
		ArrayList<Question> qList = new ArrayList<Question>();
		System.out.println("home,qa 번호"+qa_num);
		Question qa = mm.qNaCheck(qa_num);
		qList.add(qa);
		
		System.out.println("갔다오기 성공");
		return qList;
	}
	@ResponseBody
	@RequestMapping(value = "/trainerModifyT" ,method = RequestMethod.GET)
	public ModelAndView trainerModifyT() {
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("trainerModifyT세션 아이디"+mb.getM_id());
		String m_id = mb.getM_id();

		System.out.println("trainerModifyT세션 아이디 변수 m_id"+m_id);
		mav = mm.trainerModifyT(m_id);
		return mav;
	}
	@RequestMapping(value = "/infomodify" ,method = RequestMethod.POST)
	public ModelAndView infoModify(MultipartHttpServletRequest multi) {
		System.out.println("infomodify시작한다");
		mav=ys.infomodify(multi);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value = "/deletead" ,method = RequestMethod.POST)
	public String deleteAd(String ad_code) {
		System.out.println("deletead시작한다");
		System.out.println("deletead시작한다"+ad_code+"          아니이");
		
		String str =mm.deleteAd(ad_code);
		
		System.out.println("ㅇㅇㅇㅇ");
		return str;
	}
	@ResponseBody
	@RequestMapping(value = "/advertisemodifyfrm", method = RequestMethod.POST)
	public ModelAndView advertiseModifyFrm(String ad_code) {
		System.out.println("수정 폼입니다" + ad_code);
		
		mav = mm.advertiseModifyFrm(ad_code);
		
		return mav;
	}

	@RequestMapping(value = "/admodify", method = RequestMethod.POST)
	public ModelAndView adeModify(MultipartHttpServletRequest multi) {
		System.out.println("수정 폼입니다" + multi);
		
		mav = mm.adModify(multi);
		
		return mav;
	}
	
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/advertisetrlist", method = RequestMethod.POST)
	 * public ModelAndView advertisetrlist() { Member mb =
	 * (Member)session.getAttribute("mb");
	 * 
	 * mav = mm.advertisetrlist(mb);
	 * 
	 * return mav; }
	 */
}
