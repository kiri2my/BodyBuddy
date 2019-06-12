package com.bodybuddy.hey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Sales;
import com.bodybuddy.hey.dao.MemberDao;
import com.google.gson.Gson;

@Service
public class KwonService {
	@Autowired
	private MemberDao mDao;

	private HttpSession session; // request는 권장하지 않음

	Member m;

	ModelAndView mav;

	String view = null;

	public ModelAndView dailyCheckInsert(HttpServletRequest request) {
		mav = new ModelAndView();

		m = new Member();
		m.setDt_tid(request.getParameter("tid"));
		m.setDt_cid(request.getParameter("cid"));
		m.setDt_status(request.getParameter("status"));

		boolean dci = false;

		try {
			dci = mDao.dailyCheckInsert(m);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			dci = mDao.dailyCheckUpdate(m);
			mav.addObject("msg", "update");
		}

		if (dci) {
			System.out.println("dailyCheckInsert success");
			view = "manage/company/trainerDailyCheck";
			mav.setViewName(view);

		} else {
			System.out.println("dailyCheckInsert fail");
			view = "manage/company/trainerDailyCheck";
			mav.setViewName(view);
		}

		return mav;
	}

	// trainerDailyCheck
	public String getworkingAttitude(HttpServletRequest request) {
		mav = new ModelAndView();
		String view = null;

		m = new Member();
		m.setDt_cid(request.getParameter("cid"));
		m.setDt_tid(request.getParameter("tid"));

		List<Member> tList = null;
		System.out.println("getworkingAttitude mDao in");
		tList = mDao.getworkingAttitude(m);

		Gson gson = new Gson();
		String reuslt = gson.toJson(tList);

		if (0 != tList.size()) {
			System.out.println("getworkingAttitude success");
		} else {
			System.out.println("ggetworkingAttitude error");
			view = "manage/company/company";
			mav.setViewName(view);
		}

		return reuslt;
	}

	public String getProgramMember(HttpServletRequest request) {
		mav = new ModelAndView();
		String view = null;

		String code = request.getParameter("code");

		List<Member> pmList = null;
		System.out.println("getProgramMember mDao in");
		pmList = mDao.getProgramMember(code);

		Gson gson = new Gson();
		String reuslt = gson.toJson(pmList);

		if (0 != pmList.size()) {
			System.out.println("getProgramMember success");
		} else {
			System.out.println("getProgramMember error");
		}

		return reuslt;
	}

	public ModelAndView programCheckInsert(HttpServletRequest request) {
		mav = new ModelAndView();

		String code = request.getParameter("code");
		boolean dci = false;

		try {
			dci = mDao.programCheckInsert(code);
			String da_code = mDao.programCheckSelect(code);
			dci = mDao.programcheckInsert2(da_code);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			System.out.println("programCheckInsert fail");
		}

		if (dci) {
			System.out.println("programCheckInsert success");
			view = "manage/company/programDailyCheck";
			mav.setViewName(view);

		} else {
			System.out.println("programCheckInsert fail");
			view = "manage/company/programDailyCheck";
			mav.setViewName(view);

		}

		return mav;

	}

}