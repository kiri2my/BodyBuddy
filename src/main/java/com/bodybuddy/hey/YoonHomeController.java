
package com.bodybuddy.hey;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Review;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Review;
import com.bodybuddy.hey.service.YoonService;

@Controller
public class YoonHomeController {
	
	@Autowired
	private YoonService ys; //게시판 서비스 클래스(Model),비지니스 로직
	
	@Autowired
	HttpSession session;
	
	ModelAndView mav;
	String html;
	
	/*
	 * @RequestMapping(value = "/dailyCheck", produces =
	 * "application/json; charset=UTF-8")
	 * 
	 * @ResponseBody public String dailyCheck(String ps_code, String m_id) throws
	 * ParseException {
	 * 
	 * String html=null; html=ys.dailyCheck(ps_code,m_id); return html; }
	 */
	@RequestMapping(value = "/questionlistn", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView questionlistN(String m_id) {
		mav=ys.questionlistN(m_id);
		return mav;
	}
	
	
	@RequestMapping(value = "/chart",produces ="application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String chart(String t_cid) {
		html = ys.chart(t_cid);
		return html;
	}
	
	@RequestMapping(value = "/charttwo",produces ="application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String chart2(String t_cid) {
		html = ys.chart2(t_cid);
		return html;
	}
	
	@RequestMapping(value = "/sales")
	public ModelAndView sales() {
		mav=ys.sales();
		
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/calenderN")
	public ModelAndView calenderN(String ps_code) {
		mav=ys.calender(ps_code);
		return mav;
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		session.invalidate();
		String view = null;
		view="forward:/";
		mav.setViewName(view);
		return mav;
	
	}
	@RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView mainList(String sido, String sigungu, String extra, String cate) { //int pageNum 게시판페이징
		mav=ys.mainList(sido, sigungu, extra, cate);
		return mav;
	}
	
	//manage/normal/normalMain
	@RequestMapping(value = "/infoprogramn")
	public ModelAndView getInfoProgramN(String m_id) {
		mav=ys.programListN(m_id);
		return mav;
	}

	@RequestMapping(value = "/reviewwritefrm")
	public ModelAndView reviewWriteFrm(String m_id, String ps_code)  {	
		mav=ys.reviewWriteFrm(m_id, ps_code);
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
	public ModelAndView memberdelten() {
		mav=ys.memberdelten();
		return mav;
	}
	@RequestMapping(value = "/payhistoryn") 
	public ModelAndView payList(String m_id) {
		mav=ys.payListN(m_id);
		return mav;
	}
	@RequestMapping(value = "/counsellistn", method = {RequestMethod.POST,RequestMethod.GET}) 
	public ModelAndView counselList(String cs_opcode,String cs_mid) {
		mav=ys.counselListn(cs_opcode,cs_mid);
		return mav;
	}
	@RequestMapping(value = "/counseln") 
	public ModelAndView counseln(String cs_opcode,String cs_date) throws ParseException {
		mav=ys.counseln(cs_opcode,cs_date);
		return mav;

		}
	
	@RequestMapping(value = "/infomodifyn") 
	public ModelAndView infomodifyn(MultipartHttpServletRequest multi) {
		mav=ys.infomodifyn(multi);
		return mav;
		}
}