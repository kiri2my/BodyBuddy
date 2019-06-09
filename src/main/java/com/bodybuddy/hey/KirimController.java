package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.service.KirimService;


//로그아웃시 (SessionStatus status){status.setComplete();}사용

@Controller
public class KirimController {
	@Autowired
	KirimService ks;
	ModelAndView mav;
	String html;
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {
		System.out.println("url:/access");
		mav = ks.access(mb);
		return mav;
	}
	
	@RequestMapping(value = "/detailpage", method = RequestMethod.GET)
	public ModelAndView detailPage(String ad_code) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		mav = ks.detailPage(ad_code, request);
		return mav;
	}
	
	@RequestMapping(value = "/purchsingle", method = RequestMethod.POST) 
	@ResponseBody
	public String purchSingle(Payment ph) { 
		System.out.println("URL : /purchsingle");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		html = ks.purchSingle(ph, request); 
		return html; 
	}
	
	@RequestMapping(value = "/dibsadd", method = RequestMethod.GET)
	@ResponseBody
	public String dibsAdd(@RequestParam("d_adcode") String d_adcode) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		html = ks.dibsAdd(d_adcode, request);
		return html;
	}
	
	@RequestMapping(value = "/dibsdelete", method = RequestMethod.GET)
	@ResponseBody
	public String dibsDelete(@RequestParam("d_adcode") String d_adcode) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		html = ks.dibsDelete(d_adcode, request);
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
