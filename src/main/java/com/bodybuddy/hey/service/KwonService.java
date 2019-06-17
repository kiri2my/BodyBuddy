package com.bodybuddy.hey.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Company;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.KwonDao;
import com.google.gson.Gson;

@Service
public class KwonService {
	@Autowired
	private KwonDao ksDao;

	private HttpSession session; // request는 권장하지 않음

	Member m;

	Company com;

	ModelAndView mav;

	String view = null;

	public ModelAndView getMemberList(HttpServletRequest request) {
		mav = new ModelAndView();
		String id = request.getParameter("id");

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
			view = "manage/company?m_id=" + id;
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

	public ModelAndView getNormalMemberList(HttpServletRequest request) {
		mav = new ModelAndView();
		String id = request.getParameter("id");

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
			dci = ksDao.trainerDailyCheckInsert(m);
			mav.addObject("msg", "insert");
		} catch (Exception e) {
			dci = ksDao.trainerDailyCheckUpdate(m);
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
		HashMap<String, String> map = new HashMap<String, String>();

		String code = request.getParameter("code");
		boolean dci = false;
		String da_code = null;
		int ps_date = 0;
		int ps_date1 = 0;
		int sd = 0;
		

		try {
			da_code = ksDao.normalDailyCheckSelect(code);
			if (da_code != null) {
				map = ksDao.normalDailyCheckSelect1(code);
				map.put("code", code);
				String str = map.get("ps_period");
				ps_date = Integer.parseInt( str.substring(0, 8));
				ps_date1 = Integer.parseInt( str.substring(9, 17));
				if (ps_date <= sd || ps_date1 >= sd) {
					map.put("status", "이용중");
					ksDao.normalDailyCheckUpdate(map);
					ksDao.normalDailyCheckInsert(da_code);
				} else if (ps_date > sd) {
					map.put("status", "대기중");
					ksDao.normalDailyCheckUpdate(map);
				} else if (ps_date1 < sd) {
					map.put("status", "만료됨");
					ksDao.normalDailyCheckUpdate(map);
				}
				
				
			} else if (da_code == null) {
				map = ksDao.normalDailyCheckSelect1(code);
				String category = map.get("ad_category");
				map.put("code", code);
				sd = Integer.parseInt(map.get("sd"));
				if (category.equals("일반")) {
					ps_date = Integer.parseInt(map.get("ps_date"));
					ps_date1 = Integer.parseInt(map.get("ps_date1"));
					if (ps_date <= sd || ps_date1 >= sd) {
						map.put("status", "이용중");
						ksDao.normalDailyCheckInsert1(map);
						da_code = ksDao.normalDailyCheckSelect(code);
						ksDao.normalDailyCheckInsert(da_code);
					} else if (ps_date > sd) {
						map.put("status", "대기중");
						ksDao.normalDailyCheckInsert1(map);
					} else if (ps_date1 < sd) {
						map.put("status", "만료됨");
						ksDao.normalDailyCheckInsert1(map);
					}

					
					
				} else {
					String str = map.get("ps_period");
					ps_date = Integer.parseInt( str.substring(0, 8));
					ps_date1 = Integer.parseInt( str.substring(9, 17));
					if (ps_date <= sd || ps_date1 >= sd) {
						map.put("status", "이용중");
						ksDao.normalDailyCheckInsert2(map);
						da_code = ksDao.normalDailyCheckSelect(code);
						ksDao.normalDailyCheckInsert(da_code);
					} else if (ps_date > sd) {
						map.put("status", "대기중");
						ksDao.normalDailyCheckInsert2(map);
					} else if (ps_date1 < sd) {
						map.put("status", "만료됨");
						ksDao.normalDailyCheckInsert2(map);
					}
				}
			}
			dci = true;
			System.out.println("normalDailyCheckInsert success");
		} catch (Exception e) {
			System.out.println("normalDailyCheckInsert fail");
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

	public ModelAndView getInfomodifyC(String id) {
		mav = new ModelAndView();

		Company com = new Company();
		System.out.println("getInfomodifyC mDao in");
		com = ksDao.getInfomodifyC(id);

		if (com != null) {
			System.out.println("getInfomodifyC success");
			view = "manage/infoModifyC";
			mav.setViewName(view);
			mav.addObject("com", com);
		} else {
			System.out.println("getInfomodifyC error");
		}

		return mav;
	}

	public ModelAndView infoModifyUpdate(MultipartHttpServletRequest multi) {
		mav = new ModelAndView();
		com = new Company();
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

		String root = multi.getSession().getServletContext().getRealPath("/");
		System.out.println("root=" + root);
		String path = root + "resources/upload/";
		// 2.폴더 생성을 꼭 할것...
		File dir = new File(path);
		if (!dir.isDirectory()) { // upload폴더 없다면
			dir.mkdirs(); // upload폴더 생성
		}

		MultipartFile file = multi.getFile("pf_image");
		String oriFileName = null;
		String sysFileName = null;
		if (file != null) {
			oriFileName = file.getOriginalFilename();
			sysFileName = System.currentTimeMillis() + "." + oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
			System.out.println("oriFileName=" + oriFileName);
			System.out.println("sysFileName=" + sysFileName);
		}

		Map<String, String> fMap = new HashMap<String, String>();

		String m_pw = null;
		if (multi.getParameter("m_pw") != null) {
			m_pw = multi.getParameter("m_pw");
			com.setM_pw(pwdEncoder.encode(m_pw));
		}
		String m_id = multi.getParameter("c_id");
		String m_phone = multi.getParameter("m_phone");
		String m_addr = multi.getParameter("m_addr");
		String m_exaddr = multi.getParameter("m_exaddr");
		String c_bphone = multi.getParameter("c_bphone");

		com.setM_id(m_id);
		com.setM_phone(m_phone);
		com.setM_addr(m_addr);
		com.setM_exaddr(m_exaddr);
		com.setC_bphone(c_bphone);

		try {
			if (m_pw != null) {
				System.out.println("pwd update start");
				ksDao.infoModifyPwUpdate(com);
				System.out.println("pwd update success");
			}
			System.out.println("infoModifyUpdate start");
			ksDao.infoModifyMemberUpdate(com);
			ksDao.infoModifyCompanyUpdate(com);
			System.out.println("infoModifyUpdate success");
		} catch (Exception e) {
			System.out.println("infoModifyUpdate fail");
		}

		System.out.println(
				"m_phone+c_bphone+m_addr+m_exaddr=" + m_phone + c_bphone + m_addr + m_exaddr + com.getM_pw() + m_id);

		fMap.put("pf_id", m_id);
		fMap.put("pf_image", sysFileName);

		try {
			if (sysFileName != null) {
				file.transferTo(new File(path + sysFileName));
				ksDao.fileInsert(fMap);
			}
		} catch (Exception e) {
			ksDao.fileUpdate(fMap);
		}

		mav.setViewName("redirect:/infoModifyC.do");

		return mav;
	}

}