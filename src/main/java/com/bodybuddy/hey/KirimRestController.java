package com.bodybuddy.hey;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.service.KirimService;

@RestController // REST방식이라 ResponseBody, RequestBody 안써도 된다는 컨트롤러
@RequestMapping(value = "/detail")//consumes="application/json"
public class KirimRestController { // Restful 방식
	@Autowired
	private KirimService ks; 

	ModelAndView mav;

	
	 
	 /*@RequestMapping(value = "ajax/replyInsert", produces = "application/json; charset=UTF-8") // 한글 깨짐방지
	 @ResponseBody public String replyInserte(@RequestBody Reply r) { // @ResponseBody:응답하는곳에다가찍는다. 
		 String json = bm.replyInsert(r); return json; 
	 }*/
	

	/*
	@RequestMapping(value = "/page/{adcode}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable String adcode) { // @ResponseBody생략가능 @PathVariable쓰면 url에서 데이터받음
		System.out.println("URL : /detail/page");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		System.out.println("adcodeadcodeadcodeadcode="+adcode);
		mav = ks.detailPage(adcode, request);
		return mav;
	}
	
	@RequestMapping(value = "/qa", method = RequestMethod.GET)
	public ModelAndView detailQa(String qa_adcode) {
		System.out.println("URL : /detail/qa");
		System.out.println("222222222"+qa_adcode);
		mav = ks.detailQa(qa_adcode);
		return mav; 
	}
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView detailReview(String rv_adcode) {
		System.out.println("url:/detail/review");
		System.out.println("111111111"+rv_adcode);
		mav = ks.detailReview(rv_adcode);
		return mav;
	}
	*/
	
	/*@RequestMapping(value = "{adcode}/purchsingle", method = RequestMethod.POST) 
	public String purchSingle(Payment ph, HttpSession session) { 
		System.out.println("URL : /purchsingle");
		
		String json = ks.purchSingle(ph, session); 
		return json; 
	}*/
	
}
