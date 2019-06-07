package com.bodybuddy.hey;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.bodybuddy.hey.service.KirimService;

@RestController // REST방식이라 ResponseBody, RequestBody 안써도 된다는 컨트롤러
@RequestMapping(value = "/detail")//consumes="application/json"
public class KirimRestController { // Restful 방식
	@Autowired
	private KirimService ks; 

	ModelAndView mav;

	
	 /*
	 @RequestMapping(value = "ajax/replyInsert", produces = "application/json; charset=UTF-8") // 한글 깨짐방지
	 @ResponseBody public String replyInserte(@RequestBody Reply r) { // @ResponseBody:응답하는곳에다가찍는다. 
		 String json = bm.replyInsert(r); return json; 
	 }*/
	

	@RequestMapping(value = "/page/{adcode}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable String adcode) { // @ResponseBody생략가능
		mav = ks.detailPage(adcode);
		return mav;
	}

}
