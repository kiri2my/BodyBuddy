package com.bodybuddy.hey;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.service.KwonService;
import com.bodybuddy.hey.service.SalesService;

@Controller
public class KwonController {

	private static final Logger logger = LoggerFactory.getLogger(KwonController.class);
	@Autowired
	SalesService ss;

	@Autowired
	KwonService ks;

	@Autowired
	HttpSession session;

	ModelAndView mav;

	@RequestMapping(value = "/email")
	public String email(Locale locale, Model model) {
		return "email/emailCheckFrm";
	}

	@RequestMapping(value = "/company")
	public ModelAndView company(HttpServletRequest request) {
		mav = new ModelAndView();
		Member mb = (Member) session.getAttribute("mb");
		session.setAttribute("mb", mb);
		mav.addObject("session_id", mb.getM_id());
		
		System.out.println("mb.getM_id() = "+mb.getM_id());
		System.out.println("mb.getM_kind() = "+mb.getM_kind());
		System.out.println("mb.getM_name() = "+mb.getM_name());
		
		mav = ks.mainList(mb);
		
		
		
		return mav;
	}

	@RequestMapping(value = "/question")
	public String question() {
		
		
		
		return "manage/question/questionList";
	}

	@RequestMapping(value = "/memberlistc")
	public ModelAndView memberListC(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		
		mav = ks.getMemberList(mb);

		return mav;
	}

	@RequestMapping(value = "/membersearch")
	public ModelAndView memberSearch(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		System.out.println(name);
		System.out.println(id);

		mav = ks.getMemberSearch(name, id);

		return mav;
	}

	@RequestMapping(value = "/trainerlistc")
	public ModelAndView trainerListC(HttpServletRequest request) {
		Member mb = (Member) session.getAttribute("mb");
		session.setAttribute("mb", mb);

		mav = ks.getTrainerList(mb);

		return mav;
	}

	@RequestMapping(value = "/trainersearch")
	public ModelAndView trainerSearch(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		System.out.println(name);
		System.out.println(id);

		mav = ks.getTrainerSearch(name, id);

		return mav;
	}

	@RequestMapping(value = "/normaldailycheck")
	public ModelAndView normalDailyCheck(HttpServletRequest request) {

		mav = ks.getNormalMemberList(request);

		return mav;
	}

	@RequestMapping(value = "/programdailycheck")
	public ModelAndView programDailyCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = ks.getProgramMemberList(id);

		return mav;
	}

	@RequestMapping(value = "/trainerdailycheck")
	public ModelAndView trainerDailyCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);

		mav = ks.getTrainerMemberList(id);

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
		Member mb = (Member) session.getAttribute("mb");
		System.out.println(mb);

		mav = ss.getSalesHistory(mb);

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
		String id = request.getParameter("id");
		mav = ks.getInfomodifyC(id);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/infomodifyupdate" ,method = RequestMethod.POST)
	public String infoModifyUpdate(MultipartHttpServletRequest multi) {
		
		//mav = ks.infoModifyUpdate(multi);
		String gson = ks.infoModifyUpdate(multi);
		System.out.println(gson);

		return gson;
	}
	@RequestMapping(value = "/trainerjoinlist",produces = "application/text; charset=utf8")
	public @ResponseBody String trainerJoinList(HttpServletRequest request) {
		System.out.println("trainerjoinlist id = "+request.getParameter("id") );
		
		String gson = ks.getTrainerJoinList(request);
		

		return gson;
	}
	
	@RequestMapping(value = "/trainerjoin",produces = "application/text; charset=utf8")
	public @ResponseBody String trainerJoin(HttpServletRequest request) {
		//System.out.println("trainerjoinlist state = "+request.getParameter("state") );
		//System.out.println("trainerjoinlist cid = "+request.getParameter("cid") );
		//System.out.println("trainerjoinlist tid = "+request.getParameter("tid") );
		
		String gson = ks.trainerJoin(request);
		

		return gson;
	}
	
	@RequestMapping(value = "/trainerdiscon",produces = "application/text; charset=utf8")
	public @ResponseBody String trainerDiscon(HttpServletRequest request) {
		//System.out.println("trainerjoinlist state = "+request.getParameter("state") );
		//System.out.println("trainerjoinlist cid = "+request.getParameter("cid") );
		//System.out.println("trainerjoinlist tid = "+request.getParameter("tid") );
		
		String gson = ks.trainerDiscon(request);
		

		return gson;
	}
	
	@RequestMapping(value = "/changestate",produces = "application/text; charset=utf8")
	public @ResponseBody String changeState(HttpServletRequest request) {
		
		String gson = ks.changeState(request);
		
		return gson;
	}
	
	@RequestMapping(value = "/trainersales",produces = "application/text; charset=utf8")
	public @ResponseBody String trainerSales(HttpServletRequest request) {
		
		String gson = ks.getTrainerSales(request);
		
		return gson;
	}
	
	@RequestMapping(value = "/trainersalesselect",produces = "application/text; charset=utf8")
	public @ResponseBody String trainerSalesSelect(HttpServletRequest request) {
		
		String gson = ks.getTrainerSalesSelect(request);
			
		return gson;
	}

}