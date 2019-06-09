package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;

import com.bodybuddy.hey.service.KirimService;


@Controller
public class KirimController {

	private static final Logger logger = LoggerFactory.getLogger(KirimController.class);

	ModelAndView mav;

	@Autowired
	KirimService ks;

	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {
		System.out.println("url:/access");
		mav = ks.access(mb);
		return mav;
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
