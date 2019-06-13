package com.bodybuddy.hey.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.KwonDao;
import com.google.gson.Gson;

@Service
public class KwonService {
	@Autowired
	private KwonDao ksDao;

	private HttpSession session; // request는 권장하지 않음

	Member m;

	ModelAndView mav;

	String view = null;

	public ModelAndView getMemberList(String id) {
		mav = new ModelAndView();

		List<Member> mList = null;
		System.out.println("getMemberList mDao in");
		mList = ksDao.getMemberList(id);
		System.out.println("mList = " + mList.get(0).getM_name());
		System.out.println("mList size = " + mList.size());

		if (0 != mList.size()) {
			System.out.println("member list select success");
			view = "manage/memberListC";
			mav.setViewName(view);
			mav.addObject("mList", mList);
		} else {
			System.out.println("member list select error");
			view = "redirect:memberListC.jsp";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getMemberSearch(String name, String id) {
		mav = new ModelAndView();

		m = new Member();

		m.setM_name(name);
		m.setM_id(id);

		List<Member> mList = null;
		mList = ksDao.getMemberSearch(m);
		System.out.println("mList = " + mList.get(0).getM_name());
		System.out.println("mList size = " + mList.size());

		if (0 != mList.size()) {
			System.out.println("member search select success");
			view = "manage/memberListC";
			mav.setViewName(view);
			mav.addObject("mList", mList);
		} else {
			System.out.println("member search select error");
			view = "redirect:memberListC.jsp";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getTrainerList(String id) {
		mav = new ModelAndView();

		List<Member> tList = null;
		System.out.println("Trainer select in");
		tList = ksDao.getTrainerList(id);
		System.out.println("tList = " + tList.get(0).getM_name());
		System.out.println("tList size = " + tList.size());

		if (0 != tList.size()) {
			System.out.println("trainer list select success");
			view = "manage/trainerListC";
			mav.setViewName(view);
			mav.addObject("tList", tList);
		} else {
			System.out.println("trainer list select error");
			view = "manage/trainerListC";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getTrainerSearch(String name, String id) {
		mav = new ModelAndView();

		m = new Member();
		m.setM_name(name);
		m.setM_id(id);

		List<Member> tList = null;
		tList = ksDao.getTrainerSearch(m);
		System.out.println("tList = " + tList.get(0).getM_name());
		System.out.println("tList size = " + tList.size());

		if (0 != tList.size()) {
			System.out.println("tainer search success");
			view = "manage/trainerListC";
			mav.setViewName(view);
			mav.addObject("tList", tList);
		} else {
			System.out.println("tainer search error");
			view = "manage/trainerListC";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getNormalMemberList(String id) {
		mav = new ModelAndView();
		String view = null;

		List<Member> mList = null;
		System.out.println("getNormalMemberList mDao in");
		mList = ksDao.getNormalMemberList(id);
		System.out.println("mList = " + mList.get(0).getM_name());
		System.out.println("mList size = " + mList.size());

		if (0 != mList.size()) {
			System.out.println("getNormalMemberList select success");
			view = "manage/company/normalDailyCheck";
			mav.setViewName(view);
			mav.addObject("mList", mList);
		} else {
			System.out.println("member list select error");
			view = "redirect:memberListC.jsp";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getProgramMemberList(String id) {
		mav = new ModelAndView();
		String view = null;

		List<Member> pList = null;
		System.out.println("getProgramMemberList mDao in");
		pList = ksDao.getProgramMemberList(id);
		System.out.println("pList = " + pList.get(0).getM_name());
		System.out.println("pList size = " + pList.size());

		if (0 != pList.size()) {
			System.out.println("getProgramMemberList select success");
			view = "manage/company/programDailyCheck";
			mav.setViewName(view);
			mav.addObject("pList", pList);
		} else {
			System.out.println("getProgramMemberList select error");
			view = "manage/company/programDailyCheck";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView getTrainerMemberList(String id) {
		mav = new ModelAndView();
		String view = null;

		List<Member> tList = null;
		System.out.println("getTrainerMemberList mDao in");
		tList = ksDao.getTrainerMemberList(id);
		System.out.println("tList = " + tList.get(0).getM_name());
		System.out.println("tList size = " + tList.size());

		if (0 != tList.size()) {
			System.out.println("getTrainerMemberList select success");
			view = "manage/company/trainerDailyCheck";
			mav.setViewName(view);
			mav.addObject("tList", tList);
		} else {
			System.out.println("getTrainerMemberList select error");
			view = "manage/company/trainerDailyCheck";
			mav.setViewName(view);
		}

		return mav;
	}

	public ModelAndView dailyCheckInsert(HttpServletRequest request) {
		mav = new ModelAndView();

		m = new Member();
		m.setDt_tid(request.getParameter("tid"));
		m.setDt_cid(request.getParameter("cid"));
		m.setDt_status(request.getParameter("status"));

		boolean dci = false;

		try {
			dci = ksDao.dailyCheckInsert(m);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			dci = ksDao.dailyCheckUpdate(m);
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
		tList = ksDao.getworkingAttitude(m);

		Gson gson = new Gson();
		String reuslt = gson.toJson(tList);

		if (0 != tList.size()) {
			System.out.println("getworkingAttitude success");
		} else {
			System.out.println("ggetworkingAttitude error");
		}

		return reuslt;
	}

	public String getProgramMember(HttpServletRequest request) {
		mav = new ModelAndView();
		String view = null;

		String code = request.getParameter("code");

		List<Member> pmList = null;
		System.out.println("getProgramMember mDao in");
		pmList = ksDao.getProgramMember(code);

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
			dci = ksDao.programCheckInsert(code);
			String da_code = ksDao.programCheckSelect(code);
			dci = ksDao.programcheckInsert2(da_code);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			String da_code = ksDao.programCheckSelect(code);
			dci = ksDao.programcheckInsert2(da_code);
			mav.addObject("msg", "insert");
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

	public String getAttended(HttpServletRequest request) {
		mav = new ModelAndView();
		String view = null;

		String code = request.getParameter("code");

		List<Member> mList = null;
		System.out.println("getAttended mDao in");
		mList = ksDao.getAttended(code);

		Gson gson = new Gson();
		String reuslt = gson.toJson(mList);

		if (0 != mList.size()) {
			System.out.println("getAttended success");
		} else {
			System.out.println("getAttended error");
		}

		return reuslt;
	}

	public ModelAndView normalCheckInsert(HttpServletRequest request) {
		mav = new ModelAndView();

		String code = request.getParameter("code");
		boolean dci = false;

		try {
			dci = ksDao.programCheckInsert(code);
			String da_code = ksDao.programCheckSelect(code);
			dci = ksDao.programcheckInsert2(da_code);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			String da_code = ksDao.programCheckSelect(code);
			dci = ksDao.programcheckInsert2(da_code);
			mav.addObject("msg", "insert");
		}

		if (dci) {
			System.out.println("normalCheckInsert success");
			view = "manage/company/normalDailyCheck";
			mav.setViewName(view);

		} else {
			System.out.println("normalCheckInsert fail");
			view = "manage/company/normalDailyCheck";
			mav.setViewName(view);
		}

		return mav;
	}

	/*
	 * public ModelAndView getInfomodifyC(String id) { mav = new ModelAndView();
	 * String view = null;
	 * 
	 * List<Member> mList = null; System.out.println("getAttended mDao in"); mList =
	 * ksDao.getAttended(id);
	 * 
	 * Gson gson = new Gson(); String reuslt = gson.toJson(mList);
	 * 
	 * if (0 != mList.size()) { System.out.println("getAttended success"); } else {
	 * System.out.println("getAttended error"); }
	 * 
	 * return mav; }
	 */

}