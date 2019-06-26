package com.bodybuddy.hey.service;

import java.io.File;
import java.util.ArrayList;
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
import com.bodybuddy.hey.bean.DailyCheck;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.bean.Sales;
import com.bodybuddy.hey.dao.KwonDao;
import com.bodybuddy.hey.dao.SalesDao;
import com.bodybuddy.hey.dao.YoonDao;
import com.google.gson.Gson;

@Service
public class KwonService {
	@Autowired
	private KwonDao ksDao;
	@Autowired
	private SalesDao sDao;
	@Autowired
	private YoonDao yDao;
	@Autowired
	private YoonService ys;
	@Autowired
	private HttpSession session; // request는 권장하지 않음

	Member m;
	DailyCheck dc;
	Company com;
	Sales s;

	ModelAndView mav;

	String view = null;

	public ModelAndView getMemberList(Member mb) {
		mav = new ModelAndView();

		String id = mb.getM_id();

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

	public ModelAndView getTrainerList(Member mb) {
		List<Member> tList = null;
		mav = new ModelAndView();

		String id = mb.getM_id();
		int num = 0;

		try {
			System.out.println("Trainer select in");
			tList = ksDao.getTrainerList(id);
			System.out.println("tList = " + tList.get(0).getM_name());
			System.out.println("tList size = " + tList.size());

			if (0 != tList.size()) {
				System.out.println("trainer list select success");
				view = "manage/trainerListC";
				mav.setViewName(view);
				mav.addObject("tList", tList);
			}

			num = ksDao.trainerRequest(id);
			System.out.println("trainerRequest = " + num);

			mav.addObject("num", num);

		} catch (Exception e) {
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
		ArrayList<HashMap<String, String>> mList1 = new ArrayList<HashMap<String, String>>();
		mav = new ModelAndView();
		String id = request.getParameter("id");

		List<Member> mList = null;
		System.out.println("getNormalMemberList mDao in");
		String code = ksDao.getNormalMemberListCode(id);
		mList1 = ksDao.getNormalMemberList(code);
		System.out.println("mList = " + mList1.get(0).get("M_ID"));
		System.out.println("mList size = " + mList1.size());

		if (0 != mList1.size()) {
			System.out.println("getNormalMemberList select success");
			view = "manage/company/normalDailyCheck";
			mav.setViewName(view);
			mav.addObject("mList", mList1);
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

		List<DailyCheck> chList = null;
		System.out.println("getAttended mDao in");
		chList = ksDao.getAttended(code);

		Gson gson = new Gson();
		String reuslt = gson.toJson(chList);

		if (0 != chList.size()) {
			System.out.println("getAttended success");
		} else {
			System.out.println("getAttended error");
		}

		return reuslt;
	}

	public ModelAndView normalCheckInsert(HttpServletRequest request) {
		String code = request.getParameter("code");

		mav = new ModelAndView();

		dc = new DailyCheck();

		boolean dci = false;
		int ps_date = 0;
		int ps_date1 = 0;
		int sd = 0;
		int check = 0;
		String da_code = null;
		String result = null;

		try {
			System.out.println("daily에 da_code 있는지 확인");
			da_code = ksDao.normalDailyCheckSelect(code);
			System.out.println("da_code1 = " + da_code);
			if (da_code != null) {
				System.out.println("da_code가 있으면 실행");
				dc = ksDao.normalDailyCheckSelect2(code);

				check = ksDao.normalDailyCheckSelect3(dc);
				System.out.println("check num = " + check);
				if (check >= 1) {
					result = "이미 출석 되었습니다.";
					mav.setViewName("manage/company/programDailyCheck");
					mav.addObject("result", result);
					return mav;
				}

				sd = Integer.parseInt(dc.getSd()); // 현재 날짜 예)
				String str = dc.getDa_opperiod(); // 출결조건 테이블에 실제 이용기간 예) 20190601~sysdate+13/20190618
				ps_date = Integer.parseInt(str.substring(0, 8)); // ~기준으로 앞뒤로 자름
				ps_date1 = Integer.parseInt(str.substring(9, 17));

				if (ps_date <= sd || ps_date1 >= sd) {
					dc.setStatus("이용중");
					ksDao.normalDailyCheckUpdate(dc);
					ksDao.normalDailyCheckInsert(dc);
				} else if (ps_date > sd) {
					dc.setStatus("대기중");
					ksDao.normalDailyCheckUpdate(dc);
				} else if (ps_date1 < sd) {
					dc.setStatus("만료됨");
					ksDao.normalDailyCheckUpdate(dc);
				}

			} else if (da_code == null) {
				System.out.println("da_code null");
				dc = ksDao.categoryCheck(code);
				dc.setPs_code(code);

				String category = dc.getAd_category();
				System.out.println("da_code null_category =" + category);

				if (category.equals("일반")) {
					dc = ksDao.normalDailyCheckSelect1(code);
					sd = Integer.parseInt(dc.getSd());
					ps_date = Integer.parseInt(dc.getPs_date()); // 시작날짜
					ps_date1 = Integer.parseInt(dc.getPs_date1()); // 종료날짜
					String str1 = Integer.toString(ps_date) + "~" + Integer.toString(ps_date1);
					System.out.println(str1);
					if (ps_date <= sd && ps_date1 >= sd) {
						System.out.println("이용중");
						dc.setDate(str1);
						dc.setStatus("이용중");
						ksDao.normalDailyCheckInsert1(dc);
						da_code = ksDao.normalDailyCheckSelect(code);
						dc.setDa_code(da_code);
						ksDao.normalDailyCheckInsert(dc);
					} else if (ps_date > sd) {
						System.out.println("대기중");
						dc.setDate(str1);
						dc.setStatus("대기중");
						ksDao.normalDailyCheckInsert1(dc);
					} else if (ps_date1 < sd) {
						dc.setDate(str1);
						dc.setStatus("만료됨");
						ksDao.normalDailyCheckInsert1(dc);
					}

				} else {
					dc = ksDao.programDailyCheckSelect(code);
					dc.setPs_code(code);
					String str1 = dc.getOp_period();
					sd = Integer.parseInt(dc.getSd());
					ps_date = Integer.parseInt(str1.substring(0, 8));
					ps_date1 = Integer.parseInt(str1.substring(9, 17));

					if (ps_date <= sd && ps_date1 >= sd) {
						System.out.println("이용중");
						dc.setStatus("이용중");
						System.out.println(dc.getPs_code() + dc.getStatus() + dc.getOp_period());
						ksDao.programDailyCheckInsert(dc);
						da_code = ksDao.normalDailyCheckSelect(code);
						dc.setDa_code(da_code);
						ksDao.normalDailyCheckInsert(dc);
					} else if (ps_date > sd) {
						System.out.println("대기중");
						dc.setStatus("대기중");
						ksDao.normalDailyCheckInsert1(dc);
					} else if (ps_date1 < sd) {
						dc.setStatus("만료됨");
						ksDao.normalDailyCheckInsert1(dc);
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
			result = "출석 입력 성공";

		} else {
			System.out.println("normalCheckInsert fail");
			result = "출석 입력 실패";
		}
		mav.setViewName("manage/company/programDailyCheck");
		mav.addObject("result", result);

		return mav;
	}

	public ModelAndView getInfomodifyC(String id) {
		mav = new ModelAndView();
		com = new Company();
		m = new Member();

		System.out.println("getInfomodifyC mDao in");
		com = ksDao.getInfomodifyC(id);
		m = ksDao.getinfoModifyImage(id);

		if (com != null) {
			System.out.println("getInfomodifyC success");
			view = "manage/infoModifyC";
			mav.setViewName(view);
			mav.addObject("com", com);
			mav.addObject("m", m);
		} else {
			System.out.println("getInfomodifyC error");
		}

		return mav;
	}

	public String infoModifyUpdate(MultipartHttpServletRequest multi) {
		Map<String, String> fMap = new HashMap<String, String>();
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mav = new ModelAndView();
		com = new Company();
		m = new Member();

		String root = multi.getSession().getServletContext().getRealPath("/");
		System.out.println("root=" + root);
		String path = root + "resources/upload/";
		// 2.폴더 생성을 꼭 할것...
		File dir = new File(path);
		if (!dir.isDirectory()) { // upload폴더 없다면
			dir.mkdirs(); // upload폴더 생성
		}

		String m_id = multi.getParameter("c_id");
		System.out.println("m_id 확인" + m_id);
		String m_phone = multi.getParameter("m_phone");
		System.out.println("m_phone 확인" + m_phone);
		String m_addr = multi.getParameter("m_addr");
		System.out.println("m_addr 확인" + m_addr);
		String m_exaddr = multi.getParameter("m_exaddr");
		System.out.println("m_exaddr 확인" + m_exaddr);
		String c_bphone = multi.getParameter("c_bphone");
		System.out.println("c_bphone 확인" + c_bphone);
		String m_pw = multi.getParameter("m_pw");
		System.out.println("m_pw 확인" + m_pw);
		String fileCheck = multi.getParameter("fileCheck").trim();
		System.out.println("fileCheck 확인" + fileCheck);
		int checkNum = Integer.parseInt(fileCheck);
		System.out.println("checkNum = " + checkNum);

		com.setM_id(m_id);
		com.setM_phone(m_phone);
		com.setM_addr(m_addr);
		com.setM_exaddr(m_exaddr);
		com.setC_bphone(c_bphone);

		try {
			if (m_pw != null) {
				System.out.println("pwd null이 아니면 실행");
				com.setM_pw(pwdEncoder.encode(m_pw));
				System.out.println("pwd update start");
				ksDao.infoModifyPwUpdate(com);
				System.out.println("pwd update success");
			}
		} catch (Exception e) {
			System.out.println("pwd update fail");
		}

		MultipartFile file = multi.getFile("pf_image");
		String oriFileName = null;
		String sysFileName = null;
		int cNum = 0;

		try {
			if (checkNum >= 1) {
				System.out.println("checkNum 파일 있음" + "," + checkNum);
				oriFileName = file.getOriginalFilename();
				sysFileName = System.currentTimeMillis() + "."
						+ oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
				System.out.println("oriFileName=" + oriFileName);
				System.out.println("sysFileName=" + sysFileName);
				fMap.put("pf_id", m_id);
				fMap.put("pf_image", sysFileName);
				System.out.println("sysfilename = " + sysFileName);

			}
			if (sysFileName != null) {
				file.transferTo(new File(path + sysFileName));

				cNum = ksDao.profilePhotoSelect(m_id);
				if (cNum >= 1) {
					ksDao.fileUpdate(fMap);
				} else if (cNum == 0) {
					ksDao.fileInsert(fMap);
				}

			}
		} catch (Exception e) {
			System.out.println("file Insert,Update fail");
		}

		try {
			System.out.println("infoModifyUpdate start");
			ksDao.infoModifyMemberUpdate(com);
			ksDao.infoModifyCompanyUpdate(com);
			System.out.println("infoModifyUpdate success");
		} catch (Exception e) {
			System.out.println("infoModifyUpdate fail");
		}

		System.out.println(
				"m_phone+c_bphone+m_addr+m_exaddr=" + m_phone + c_bphone + m_addr + m_exaddr + com.getM_pw() + m_id);
		Gson gson = new Gson();
		String ajGson = null;
		try {
			com = ksDao.getInfomodifyC(m_id);
			m = ksDao.getinfoModifyImage(m_id);
			/*
			 * mav.addObject(com); mav.addObject(m); view =
			 * "manage/company/company?m_id"+m_id; mav.setViewName(view);
			 */
			ajGson = gson.toJson(com) + gson.toJson(m);
		} catch (Exception e) {
			System.out.println("정보수정 실패");
		}

		/* mav.setViewName("redirect:infomodifyc"); */

		return ajGson;
	}

	public String getTrainerJoinList(HttpServletRequest request) {
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		Gson gson = new Gson();
		String str = null;

		mav = new ModelAndView();
		m = new Member();

		String id = request.getParameter("id");

		try {
			map = ksDao.getTrainerJoinList(id);
			str = gson.toJson(map);
			System.out.println(str);
			System.out.println("트레이너 요청 리스트 성공");
		} catch (Exception e) {
			System.out.println("트레이너 요청 리스트 실패");
		}

		return str;
	}

	public String trainerJoin(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> hMap = new ArrayList<HashMap<String, String>>();
		Gson gson = new Gson();
		String str = null;
		String state = request.getParameter("state");

		if (state.equals("1")) {
			state = "수락";
		} else if (state.equals("0")) {
			state = "거절";
		}

		String cid = request.getParameter("cid");
		String id = cid;
		String tid = request.getParameter("tid");
		map.put("state", state);
		map.put("cid", cid);
		map.put("tid", tid);

		try {
			if (state.equals("수락")) {
				ksDao.trainerJoinUpdate(map);
				ksDao.trainerJoinInsert(map);

				hMap = ksDao.getTrainerJoinList(id);
			} else if (state.equals("거절")) {
				ksDao.trainerJoinUpdate(map);

				hMap = ksDao.getTrainerJoinList(id);
			}

			str = gson.toJson(hMap);
			System.out.println(str);
			System.out.println("trainerJoin update,insert success");
		} catch (Exception e) {
			System.out.println("trainerJoin update,insert fail");
		}

		return str;
	}

	public String trainerDiscon(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> hMap = new ArrayList<HashMap<String, String>>();
		Gson gson = new Gson();
		String str = null;

		String cid = request.getParameter("cid");
		String id = cid;
		String tid = request.getParameter("tid");
		map.put("cid", cid);
		map.put("tid", tid);

		try {
			ksDao.trainerDiscon(map);
			ksDao.trainerDisconDelete(map);

			System.out.println("trainerDiscon success");
		} catch (Exception e) {
			System.out.println("trainerDiscon fail");
		}

		return "success";
	}

	public String changeState(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> hMap = new ArrayList<HashMap<String, String>>();
		Gson gson = new Gson();
		String str = null;

		String code = request.getParameter("code");
		String status = request.getParameter("status");
		map.put("code", code);
		map.put("status", status);

		try {
			ksDao.changeState(map);
			// ksDao.trainerDisconDelete(map);

			System.out.println("trainerDiscon success");
		} catch (Exception e) {
			System.out.println("trainerDiscon fail");
		}

		return "success";

	}

	public ModelAndView mainList(Member mb) {
		mav = new ModelAndView();
		String id = mb.getM_id();

		ArrayList<Member> mList = new ArrayList<Member>();
		ArrayList<Member> mList1 = new ArrayList<Member>();
		try {
			System.out.println("getMainMemberList mDao in");
			mList1 = ksDao.getMainMemberList(id);
			System.out.println("getMainMemberList mDao out" + mList1.size());

			if (0 != mList1.size()) {
				System.out.println("getMainMemberList if in");
				for (int i = 0; i < 5; i++) {
					System.out.println("getMainMemberList for in");
					mList.add(i, mList1.get(i));
					System.out.println(mList.get(i).getM_id());
				}
				System.out.println("getMainMemberList for out");

			}
			System.out.println("member list select success");
			mav.addObject("mList", mList);
		} catch (Exception e) {
			System.out.println("member list select error");
		}

		ArrayList<Sales> sList = new ArrayList<Sales>();
		ArrayList<Sales> sList1 = new ArrayList<Sales>();
		try {
			System.out.println("getSalesHistory mDao in");
			sList1 = sDao.getMainSalesHistory(id);

			if (0 != sList1.size()) {
				for (int i = 0; i < 5; i++) {
					sList.add(i, sList1.get(i));
					// sList.set(i, sList1.get(i));
					System.out.println(sList.get(i).getPs_code());
				}

			}
			System.out.println("getSalesHistory select success");
			mav.addObject("sList", sList);
		} catch (Exception e) {
			System.out.println("getSalesHistory list select error");
		}

		int j = 5;

		ArrayList<Question> aList = new ArrayList<Question>();
		ArrayList<Question> aList1 = new ArrayList<Question>();
		try {
			System.out.println("getMainAdvertise mDao in");
			aList1 = ksDao.getMainAdvertise(id);
			if (aList1.size() < 5) {
				j = aList1.size();
			}
			if (0 != aList1.size()) {
				for (int i = 0; i < j; i++) {
					aList.add(i, aList1.get(i));
					// sList.set(i, sList1.get(i));
					System.out.println(aList.get(i).getAd_code());
				}

			}
			////////////////////////////////
				List<Map<String, String>> getSalesList = null;
				List<Map<String, String>> getSalesAllList = null;
				List<Map<String, String>> getSalescList = null;
				List<Map<String, String>> getSalesAllc = null;
				Member sessionMb = (Member) session.getAttribute("mb");
				String m_id=sessionMb.getM_id();
				//Member mb=yDao.getCbname(m_id);
				//String cbname=mb.getC_bname();
				getSalesList = yDao.getsales(m_id);
				getSalesAllList = yDao.getsalesAll(m_id);
				getSalescList = yDao.getSalescList(m_id);
				getSalesAllc = yDao.getSalesAllcList(m_id);
				//getSalescList = yDao.getsalesAlla(m_id);
				String html = ys.makeHTMLsalesList(getSalesList);
				String html2 = ys.makeHTMLsalesAllList(getSalesAllList);
				String html3 = ys.makeHTMLsalescList(getSalescList);
				String html4 = ys.makeHTMLsalesAllcList(getSalesAllc);
				mav.addObject("getSalesList", html);
				mav.addObject("getSalesAllList", html2);
				mav.addObject("getSalescList", html3);
				mav.addObject("getSalesAllcList", html4);
			/////////////////////////////////////////////////////////
			
			System.out.println("getMainAdvertise select success");
			mav.addObject("aList", aList);
		} catch (Exception e) {
			System.out.println("getMainAdvertise list select error");
		}

		mav.setViewName("manage/company/companyMain");
		return mav;
	}

	public String getTrainerSales(HttpServletRequest request) {
		String date = new java.text.SimpleDateFormat("YYYYMM").format(new java.util.Date());
		String tid = request.getParameter("tid");
		String cid = request.getParameter("cid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("cid", cid);
		map.put("date", date);
		
		
		Gson gson = new Gson();
		String str = null;

		ArrayList<HashMap<String, String>> sList = new ArrayList<HashMap<String, String>>();

		try {
			sList = ksDao.getTrainerSales(map);

			System.out.println(sList.get(0));
			System.out.println("getTrainerSales success");
			
			if(sList.size() == 0) {
				return "0";
			}
			
			str = gson.toJson(sList);
		} catch (Exception e) {
			System.out.println("getTrainerSales fail");
			return "0";
		}

		return str;
	}

	public String getTrainerSalesSelect(HttpServletRequest request) {
		
		String tid = request.getParameter("tid");
		String cid = request.getParameter("cid");
		String ym = request.getParameter("ym");
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("cid", cid);
		map.put("ym", ym);
		
		Gson gson = new Gson();
		String str = null;

		ArrayList<HashMap<String, String>> sList = new ArrayList<HashMap<String, String>>();

		try {
			sList = ksDao.getTrainerSalesSelect(map);

			System.out.println(sList.get(0));
			System.out.println("getTrainerSalesSelect success");
			if(sList.size() == 0) {
				return "0";
			}
			
			str = gson.toJson(sList);
		} catch (Exception e) {
			System.out.println("getTrainerSalesSelect fail");
			return "0";
		}

		return str;
	}

}