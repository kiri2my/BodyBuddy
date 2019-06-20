package com.bodybuddy.hey;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.bean.Qna;
import com.bodybuddy.hey.service.KirimService;


//로그아웃시 (SessionStatus status){status.setComplete();}사용

@Controller
public class KirimController {
	@Autowired
	KirimService ks;
	ModelAndView mav;
	String html;
	String json;
	
	
	//cron 표현식 보기 (시간조건) : https://aljjabaegi.tistory.com/400
	//자정이 되면 실행:"0 0 0 * * *"       
	//5분마다 실행:"0 0/5 * * * *"
	
	@Scheduled(cron="0 0/5 * * * *")   
	public void adExpirePeriod() {
		System.out.println("스케쥴러 : adExpirePeriod ");
		ks.adExpirePeriod();
	}
	
	
	
	
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {
		System.out.println("url:/access");
		mav = ks.access(mb);
		return mav;
	}
	
	@RequestMapping(value = "/profilepage",produces ="application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String profilePage(String m_id) {
		System.out.println("url:/profilepage");
		html = ks.profilePage(m_id);
		return html;
	}
	
	
	@RequestMapping(value = "/detailpage", method = RequestMethod.GET)
	public ModelAndView detailPage(String ad_code) {
		System.out.println("url:/detailpage");
		mav = ks.detailPage(ad_code);
		return mav;
	}
	
	@RequestMapping(value = "/purchsingle", method = RequestMethod.POST) 
	@ResponseBody
	public String purchSingle(Payment ph) { 
		System.out.println("URL : /purchsingle");
		html = ks.purchSingle(ph); 
		return html; 
	}
	
	@RequestMapping(value = "/dibsadd", method = RequestMethod.GET)
	@ResponseBody
	public String dibsAdd(@RequestParam("d_adcode") String d_adcode) {
		System.out.println("url:/dibsadd");
		html = ks.dibsAdd(d_adcode);
		return html;
	}
	
	@RequestMapping(value = "/dibsdelete", method = RequestMethod.GET)
	@ResponseBody
	public String dibsDelete(String d_adcode) {
		System.out.println("url:/dibsdelete");
		html = ks.dibsDelete(d_adcode);
		return html;
	}
	
	@RequestMapping(value = "/detailqawriteinsert",produces ="application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String detailQaWriteInsert(Qna qna) {
		System.out.println("url:/detailqawriteinsert");
		html = ks.detailQaWriteInsert(qna);
		return html;
	}
	
	
	
	

	
	
	/*
	 * @RequestMapping(value = "/purchsingle", produces =
	 * "application/json; charset=utf-8")
	 * 
	 * public String purchSingle(@RequestParam PaymentHistory ph) { String json =
	 * ks.purchSingle(ph); return json; }
	 */

	/*
	 * @RequestMapping(value = "/purchsingle",produces =
	 * "application/json; charset=utf-8", method = RequestMethod.POST)
	 * public @ResponseBody String purchSingle(@RequestBody String op_code) { ajax통신
	 * System.out.println("url:/purchsingle");
	 * 
	 * 
	 * String json = ks.purchSingle(op_code); return json; }
	 */

}
