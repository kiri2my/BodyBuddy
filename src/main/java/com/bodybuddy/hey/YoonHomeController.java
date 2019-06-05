package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.service.YoonService;

@Controller
public class YoonHomeController {
	
	@Autowired
	private YoonService ys; //게시판 서비스 클래스(Model),비지니스 로직
	
	ModelAndView mav;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView mainList() { //int pageNum 게시판페이징
		mav=ys.mainList();
		return mav;
	}
	
	@RequestMapping(value = "/infomodifyfrmn")
	public String login(Model model) {
		//일반 회원 내 정보수정
		return "manage/infoModifyN";
	}

	@RequestMapping(value = "/infomodifyfrmt", method = RequestMethod.GET)
	public String login2(Model model) {
		//트레이너 내 정보수정
		return "manage/infoModifyT";
	}
	@RequestMapping(value = "/infomodifyfrmc", method = RequestMethod.GET)
	public String login3(Model model) {
		//업체 내 정보수정
		return "manage/infoModifyC";
	}
	@RequestMapping(value = "/profilemodifytfrm", method = RequestMethod.GET)
	public String login4(Model model) {
		//트레이너 내 프로필수정
		return "manage/profileModifyT";
	}
	@RequestMapping(value = "/selectmodifyt", method = RequestMethod.GET)
	public String login5(Model model) {
		//트레이너 프로필,내정보수정선택
		return "manage/selectModifyT";
	}

}
