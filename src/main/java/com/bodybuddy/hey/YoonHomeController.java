
package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Review;
import com.bodybuddy.hey.service.YoonService;

@Controller
public class YoonHomeController {
	
	@Autowired
	private YoonService ys; //게시판 서비스 클래스(Model),비지니스 로직
	
	ModelAndView mav;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		session.invalidate();
		String view = null;
		view="forward:/";
		mav.setViewName(view);
		return mav;
	
	}
	@RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView mainList(String sido, String sigungu, String extra) { //int pageNum 게시판페이징
		mav=ys.mainList(sido, sigungu, extra);
		return mav;
	}
	//manage/normal/normalMain
	@RequestMapping(value = "/infoprogramn")
	public ModelAndView getInfoProgramN(String m_id) {
		mav=ys.programListN(m_id);
		return mav;
	}
	@RequestMapping(value = "/reviewwritefrm")
	public ModelAndView reviewWriteFrm(String m_id, String ad_code)  {	
		mav=ys.reviewWriteFrm(m_id, ad_code);
		return mav;
	}
	@RequestMapping(value = "/reviewwriteinsert")
	public ModelAndView insertReview(Review rv) {
		mav=ys.insertReview(rv);
		return mav;
	}
	@RequestMapping(value = "/infomodifyfrmn")
	public ModelAndView modifyN(String m_id) {
		mav=ys.modifyN(m_id);
		return mav;
	}
	@RequestMapping(value = "/dibsn")
	public ModelAndView dibsList(String m_id) {
		System.out.println("mmmmmmmmmmmmmmm="+m_id);
		mav=ys.dibsList(m_id);
		return mav;
	}
	@RequestMapping(value = "/memberdelten")
	public String login4(Model model) {
		
		return "manage/payHistoryN.";
	}
	@RequestMapping(value = "/payhistoryn") 
	public ModelAndView payList(String m_id) {
		 mav=ys.payListN(m_id);
		return mav;
	}

}
