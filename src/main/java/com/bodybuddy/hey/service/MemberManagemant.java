package com.bodybuddy.hey.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.bean.YesOrNo;
import com.bodybuddy.hey.dao.MemberDao;
import com.bodybuddy.hey.userClass.UploadFile;
import com.google.gson.Gson;

@Service
public class MemberManagemant {
	@Autowired
	MemberDao mDao;
	@Autowired
	HttpSession session; // request는 권장하지 않음
	/*
	 * @Autowired private UploadFile upload;
	 */
	Member m;

	ModelAndView mav;

	String view = null;

	public ModelAndView normalMemberJoin(Member mb) {
		System.out.println("맴버 매니지맨트 시작");
		mav = new ModelAndView();
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		System.out.println("asdasdsadasd    = " + mb.getM_phone());

		if (mDao.normalMemberJoin(mb)) {
			view = "loginJoinFrm/loginFrm";
		} else {
			view = "loginJoinFrm/join";
		}
		mav.setViewName(view);
		System.out.println("매니지맨트 종료");

		return mav;
	}

	public ModelAndView trainerMemberJoin(Member mb) {
		System.out.println("맴버 매니지맨트 시작");
		mav = new ModelAndView();
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		mb.setM_addr(mb.getM_addr() + " " + mb.getM_exaddr());
		System.out.println("addr = " + mb.getM_addr());

		if (mDao.normalMemberJoin(mb)) {
			if (mDao.trainerMemberJoin(mb))
				view = "loginJoinFrm/loginFrm";
		} else {
			view = "loginJoinFrm/join";
		}
		mav.setViewName(view);
		System.out.println("매니지맨트 종료");

		return mav;
	}

	public ModelAndView companyMemberJoin(Member mb) {
		System.out.println("맴버 매니지맨트 시작");
		mav = new ModelAndView();
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		mb.setM_addr(mb.getM_addr() + " " + mb.getM_exaddr());
		System.out.println("addr = " + mb.getM_addr());

		if (mDao.normalMemberJoin(mb)) {
			if (mDao.companyMemberJoin(mb))
				view = "loginJoinFrm/loginFrm";
		} else {
			view = "loginJoinFrm/join";
		}
		mav.setViewName(view);
		System.out.println("매니지맨트 종료");

		return mav;
	}

	public int checkId(String m_id) {
		int check = 0;
		String s = m_id;
		System.out.println(s + "                                   123");
		session.setAttribute("m_id", m_id);
		check = mDao.checkId(m_id);

		return check;
	}

	public ModelAndView forgetId(Member mb) {
		List<Member> tList = null;

		tList = mDao.forgetId(mb);
		mav = new ModelAndView();
		if (tList.size() == 0) {
			mav.addObject("m_id", "일치하는정보가 없습니다");
		} else {
			String m_id = "";
			for (int i = 0; i < tList.size(); i++) {
				System.out.println("반복문");
				m_id += tList.get(i).getM_id() + "<br>";
				System.out.println(m_id);
			}
			System.out.println("반복문 끝");
			System.out.println("m_idm_idm_id" + m_id);
			mav.addObject("m_id", m_id);
		} // else

		view = "loginJoinFrm/new";
		mav.setViewName(view);
		return mav;

	}

	public int checkCompanyNum(String c_num) {
		System.out.println("asdasd");
		int cnum = 0;
		cnum = mDao.checkCompanyNum(c_num);
		return cnum;
	}

	public ModelAndView forgetPw(Member mb) {
		List<Member> tList = null;

		tList = mDao.forgetPw(mb);
		mav = new ModelAndView();
		if (tList.size() == 0) {
			mav.addObject("m_pw", "일치하는정보가 없습니다");
		} else {

			mav = new ModelAndView();
			mb.setM_pw(getRamdomPassword(12));
			mav.addObject("m_pw", mb.getM_pw());
			// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
			BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
			mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
			System.out.println("                                  " + mb.getM_pw());
			mDao.temporaryPw(mb);
		}

		view = "loginJoinFrm/new";
		mav.setViewName(view);
		return mav;

	}

	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 소숫점제거)
			System.out.println("idx ::::" + idx);
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	public ModelAndView memberDeleteReal() {
		mav = new ModelAndView();
		String view = null;
		System.out.println("맴매트 라규!");
		Member mb = (Member) session.getAttribute("mb");
		String m_id = mb.getM_id();
		System.out.println("mb.getM_id()mb.getM_id()  " + mb.getM_id());
		System.out.println("m_id   " + m_id);
		if (mDao.memberDeleteReal(m_id)) {
			System.out.println("m_idm_idm_idm_id  = " + m_id);
			mDao.DeleteRealId(m_id);
			System.out.println("성공이라규!");
			session.invalidate();
			view = "main";

		} else {
			mav.addObject("msg", "회원탈퇴에 실패 했습니다  다시시도해주세요");
			view = "/hey";
		}
		mav.setViewName(view);
		return mav;
	}

	public Question qaNum(String qa_num) {
		System.out.println("맴버 매니지맨트");
		Question qa = mDao.qaNum(qa_num);
		session.setAttribute("qa", qa);

		return qa;
	}

	public boolean questionReply(@Param("qa_acontent") String qa_acontent, @Param("qa_num") String qa_num) {

		System.out.println("mm qa 번호" + qa_num);
		boolean answer = mDao.questionReply(qa_acontent, qa_num);
		System.out.println("성공");

		return answer;
	}

	public Question qNaCheck(String qa_num) {
		Question qa = mDao.qNaCheck(qa_num);
		return qa;

	}

	public ModelAndView trainerModifyT(String m_id) {
		mav = new ModelAndView();
		m = new Member();
		System.out.println("   " + m_id);
		m = mDao.trainerModifyT(m_id);
		String pf_image = mDao.pfimage(m_id);
		session.setAttribute("mb", m);
		mav.addObject("m_id", m.getM_id());
		mav.addObject("m_name", m.getM_name());
		mav.addObject("m_phone", m.getM_phone());
		mav.addObject("m_birth", m.getM_birth());
		mav.addObject("m_addr", m.getM_addr());
		mav.addObject("m_exaddr", m.getM_exaddr());
		mav.addObject("m_kind", m.getM_kind());
		mav.addObject("pf_image", m.getPf_image());
		view = "manage/infoModifyT";
		mav.setViewName(view);

		return mav;
	}

	public String deleteAd(String ad_code) {

		Integer success1 = mDao.deleteAdphoto(ad_code);
		Integer success = mDao.deleteAd(ad_code);

		Gson gson = new Gson();
		String str = gson.toString();
		return str;
	}

	public ModelAndView advertiseModifyFrm(String ad_code) {
		// int i = mDao.opcount(ad_code);
		mav = new ModelAndView();
		Member mb = (Member) session.getAttribute("mb");
		String id = mb.getM_id();
		System.out.println("메멘의 수정폼입니다");
		ArrayList<Question> opList = mDao.advertiseModifyFrm(ad_code);
		List<YesOrNo> yn = mDao.trinerlist(id);
		System.out.println("dao입니다");
		System.out.println("opList1" + opList.size());
		String makeopHtml = opHtml(opList, opList.size(), yn);

		Question list = new Question();
		System.out.println("list" + list.getOp_code());
		System.out.println(opList);

		mav.addObject("opHtml", makeopHtml);
		mav.addObject("opList", opList);
		mav.setViewName("manage/advertisemodifyfrm");
		System.out.println("메멘의 리턴입니다");
		return mav;
	}

	private String opHtml(ArrayList<Question> opList, int opSize, List<YesOrNo> yn) {
		StringBuilder sb = new StringBuilder();
		String str = "";
		String str2 = "";
		String str3 = "";
		String mon = "";
		String tue = "";
		String wed = "";
		String thu = "";
		String fri = "";
		String sat = "";
		String sun = "";
		String fitness = "";
		String yoga = "";
		String pt = "";
		String pilates = "";
		String normal = "";
		String home = "";
		String tstr1 = "";
		String cstr1 = "";
		System.out.println(opList.get(0).getOp_adcode());
		String[] op_period = opList.get(0).getOp_period().split("~");
		String[] op_day = opList.get(0).getOp_day().split(" ");
		String[] op_clock = opList.get(0).getOp_clock().split("~");
		String ad_content = opList.get(0).getAd_content();
		String ad_title = opList.get(0).getAd_title();
		String ad_category = opList.get(0).getAd_category();
		String ad_code = opList.get(0).getAd_code();
		Member m = (Member) session.getAttribute("mb");
		if (ad_category.equals("일반")) {
			fitness = "selected=\"selected\"";
		}
		if (ad_category.equals("요가")) {
			yoga = "selected=\"selected\"";
		}
		if (ad_category.equals("피트니스")) {
			pt = "selected=\"selected\"";
		}
		if (ad_category.equals("필라테스")) {
			pilates = "selected=\"selected\"";
		}
		if (ad_category.equals("홈트레이닝")) {
			home = "selected=\"selected\"";
		}

		str = ("<form id=\"frm\" action=\"adinsert\">\r\n" + "  <div class=\"container\">\r\n"
				+ "   <table id=\"recent-purchases-listing\" class=\"table\">\r\n" + "    <tbody>\r\n" + "     <tr>\r\n"
				+ "      <th style=\"width: 200px\">분류</th>\r\n" + "\r\n"
				+ "      <td style=\"vertical-align: middle\">\r\n"
				+ "      <select id=\"ex-select\" name=\"ad_category\" onchange=\"selectExercise()\" class=\"form-control\" style=\"width: 150px;\">\r\n"
				+ "        <option class=\"nothing\" value=\"nothing\" >선택해주세요</option> \r\n"
				+ "        <option class=\"normal\" value=\"normal\" " + normal + ">일반</option>\r\n"
				+ "        <option class=\"fitness\" value=\"pt\" " + fitness + ">피트니스-PT</option>\r\n"
				+ "        <option class=\"yoga\" value=\"yoga\" " + yoga + ">요가</option>\r\n"
				+ "        <option class=\"pt\" value=\"pt\" " + pt + ">개인PT</option>\r\n"
				+ "        <option class=\"pilates\" value=\"pilates\" " + pilates + ">필라테스</option>\r\n"
				+ "        <option class=\"home\" value=\"homeTraining\" " + home + ">홈트레이닝</option>\r\n"
				+ "      </select>\r\n" + "      </td>\r\n" + "     </tr>\r\n" + "     <tr>\r\n"
				+ "      <th>제목</th>\r\n" + "      <td style=\"vertical-align: middle\"><input type=\"text\" \r\n"
				+ "       name=\"ad_title\" style=\"height: 50px;\" value=" + ad_title + "></td>\r\n" + "     </tr>\r\n"
				+ "     <tr>\r\n" + "      <th>내용</th>\r\n"
				+ "      <td style=\"vertical-align: middle\"><input type=\"text\" class=\"reset\"\r\n"
				+ "       name=\"ad_content\" style=\"height: 300px; width: 500px\" value=" + ad_content + "></td>\r\n"
				+ "     </tr>\r\n" + "     \r\n" + "     \r\n" + "     \r\n" + "     <tr id=\"time\" >\r\n"
				+ "      <th>옵션</th>\r\n" + "      <td style=\"vertcal ical-align: middle\">\r\n"
				+ "       <div class=\"radio\">\r\n" + "        <div id=\"pre_set\" class=\"pre_set\">" + "<table>\r\n"
				+ "          <tr>\r\n" + "           <th>옵션명</th>\r\n" + "           <th>기간</th>\r\n"
				+ "           <th>시간</th>\r\n" + "           <th>요일</th>\r\n" + "           <th>횟수</th>\r\n"
				+ "           <th>인원</th>\r\n" + "           <th>가격</th>\r\n" + "           <th>담당자</th>\r\n"
				+ "           \r\n" + "          </tr>\r\n" + "          <tr>\r\n"
				+ "<input type=\"hidden\" name=\"ad_code\"  value=" + ad_code + ">");
		for (int i = 0; i < opSize; i++) {
			op_period = opList.get(i).getOp_period().split("~");
			op_day = opList.get(i).getOp_day().split(" ");
			op_clock = opList.get(i).getOp_clock().split("~");
			for (int s = 0; s < opSize; s++) {
				if (op_day[s].equals("월")) {
					mon = "checked";
				}
				if (op_day[s].equals("화")) {
					tue = "checked";
				}
				if (op_day[s].equals("수")) {
					wed = "checked";
				}
				if (op_day[s].equals("목")) {
					thu = "checked";
				}
				if (op_day[s].equals("금")) {
					fri = "checked";
				}
				if (op_day[s].equals("토")) {
					sat = "checked";
				}
				if (op_day[s].equals("일")) {
					sun = "checked";

				}
				System.out.println(i + "asdadasdasdasdasd");
				System.out.println("op_period[0]" + op_period[0]);
				System.out.println("op_period[1]" + op_period[1]);
				System.out.println("op_clock[0]" + op_clock[0]);
				System.out.println("op_clock[1]" + op_clock[1]);
				System.out.println("opList.get(" + i + ").getOp_times()" + opList.get(i).getOp_times());
				System.out.println("opList.get(" + i + ").getOp_personnel()" + opList.get(i).getOp_personnel());
				System.out.println("opList.get(" + i + ").getOp_price()" + opList.get(i).getOp_price());

				if (ad_category.equals("일반")) {
					str2 = "";
				} else {
					str2 = ("          <tr>\r\n"
							+ "           <td><input type=\"text\" name=\"op_content\"	 style=\"width: 100px\" placeholder=\"옵션명\"  class=\"reset\" value="
							+ opList.get(i).getOp_content() + "></td>\r\n" + "           <td >\r\n"
							+ "            <label for=\"dates\" class=\"normalOption\">일 수(Dates)</label><input type=\"text\"  id=\"from\" name=\"op_period\" class=\"from\" value="
							+ ">\r\n"
							+ "           <label for=\"from\" class=\"programOption\">From</label><input type=\"text\"  id=\"from\" name=\"op_period1\" class=\"from\" value="
							+ op_period[0] + ">\r\n"
							+ "             <label for=\"to\" class=\"programOption\">to</label><input type=\"text\" id=\"to\" name=\"op_period2\" class=\"to\" value="
							+ op_period[1] + ">\r\n" + "           </td>\r\n"
							+ "           <td><input type=\"text\" name=\"op_clock1\" style=\"width: 100px\" placeholder=\"시작시간\" class=\"reset\" value="
							+ op_clock[0] + "> \r\n"
							+ "           <input type=\"text\" name=\"op_clock2\" style=\"width: 100px\" placeholder=\"종료시간\" class=\"reset\" value="
							+ op_clock[1] + "></td>\r\n"
							+ "           <td><div class=\"checkbox\" style=\"width: 300px; font-size: 20px\" class=\"reset\">\r\n"
							+ "        <label for=\"foo1\"> <input type=\"checkbox\" id=\"foo1\"\r\n"
							+ "         name=\"day\" value=\"월 \" " + mon + ">월\r\n"
							+ "        </label> <label for=\"foo2\"> <input type=\"checkbox\" id=\"foo2\"\r\n"
							+ "         name=\"day\" value=\"화 \" " + tue + ">화\r\n"
							+ "        </label> <label for=\"foo3\"> <input type=\"checkbox\" id=\"foo3\"\r\n"
							+ "         name=\"day\" value=\"수 \" " + wed + ">수\r\n"
							+ "        </label> <label for=\"foo4\"> <input type=\"checkbox\" id=\"foo4\"\r\n"
							+ "         name=\"day\" value=\"목 \" " + thu + ">목\r\n"
							+ "        </label> <label for=\"foo5\"> <input type=\"checkbox\" id=\"foo5\"\r\n"
							+ "         name=\"day\" value=\"금 \" " + fri + ">금\r\n"
							+ "        </label> <label for=\"foo6\"> <input type=\"checkbox\" id=\"foo6\"\r\n"
							+ "         name=\"day\" value=\"토 \" " + sat + ">토\r\n"
							+ "        </label> <label for=\"foo7\"> <input type=\"checkbox\" id=\"foo7\"\r\n"
							+ "         name=\"day\" value=\"일 \" " + sun + ">일\r\n" + "        </label> \r\n"
							+ "        <label for=\"foo7\"> <input type=\"hidden\" id=\"foo7\"\r\n"
							+ "         name=\"day\" value=\"@\">\r\n" + "        </label>\r\n"
							+ "       </div></td>\r\n"
							+ "           <td><input type=\"text\" name=\"op_times\" style=\"width: 50px\" placeholder=\"횟수\" class=\"reset\" value="
							+ opList.get(i).getOp_times() + "></td>\r\n"
							+ "           <td><input type=\"text\" name=\"op_personnel\" placeholder=\"모집인원\" class=\"reset\" value="
							+ opList.get(i).getOp_personnel() + ">명</td>\r\n"
							+ "           <td><input type=\"text\" name=\"op_price\" placeholder=\"가격(원)\" class=\"reset\" value="
							+ opList.get(i).getOp_price() + "></td>\r\n");
					if (m.getM_kind().equals("t")) {
						tstr1 = ("<td><select name=\"op_trainer\"><option value=" + m.getM_name() + "," + m.getM_id()
								+ ">\r\n" + m.getM_name() + "</option>");
						str2 += tstr1;

					} else {
						cstr1 = ("<td><select name=\"op_trainer\"><option value=\"\">트레이너 선택</option>\r\n");
						for (int ss = 0; ss < yn.size(); ss++) {
							cstr1 += ("<option value='" + yn.get(ss).getT_id() + "," + yn.get(ss).getM_name() + "'>"
									+ yn.get(ss).getM_name() + "</option>\r\n");
						}
						str2 += cstr1;
					}

				}
			}
			str3 += str2;
		}
		System.out.println("최종입니다" + str2);
		sb.append(str + str3 + "</select></td> </tr>");

		return sb.toString();
	}

	public ModelAndView adModify(MultipartHttpServletRequest multi) {

		mav = new ModelAndView();
		Question adadd = new Question();

		String ad_code = null;
		String ad_category = null;
		String ad_title = null;
		String ad_content = null;
		String xxx = null;
		String[] op_contentValues = null;
		String[] day1 = null;
		Map<String, String> opMap = new HashMap<String, String>();
		List<String> ad_nameTId = null;
		String[] op_trainerValues = null;
		String[] dayValues = null;
		String[] op_periodValues = null;
		String[] op_period_1sValues = null;
		String[] op_period_2sValues = null;
		String[] op_timesValues = null;
		String[] op_clock1Values = null;
		String[] op_clock2Values = null;
		String[] op_priceValues = null;
		String[] op_personnelValues = null;

		Member mb = (Member) session.getAttribute("mb");
		adadd.setAd_name(mb.getM_id());// 세션아이디
		opMap.put("ad_name", mb.getM_id());
		
		ad_category = multi.getParameter("ad_category");// adadd.getAd_category();
		if (ad_category != null && ad_category != "") {
			System.out.println("ad_category : " + ad_category);
			if (ad_category.equals("normal")) {
				adadd.setAd_category("일반");
				opMap.put("ad_category", "일반");
			}
			if (ad_category.equals("pt")) {
				adadd.setAd_category("피트니스");
				opMap.put("ad_category", "피트니스");
			}
			if (ad_category.equals("homeTraining")) {
				adadd.setAd_category("홈트레이닝");
				opMap.put("ad_category", "홈트레이닝");
			}
			if (ad_category.equals("pilates")) {
				adadd.setAd_category("필라테스");
				opMap.put("ad_category", "필라테스");
			}
			if (ad_category.equals("yoga")) {
				adadd.setAd_category("요가");
				opMap.put("ad_category", "요가");
			}
		}

		ad_title = multi.getParameter("ad_title");// adadd.getAd_title();
		if (ad_title != null && ad_title != "") {
			System.out.println("ad_title : " + ad_title);
			adadd.setAd_title(ad_title);
			opMap.put("ad_title", ad_title);
		}

		ad_content = multi.getParameter("ad_content");// adadd.getAd_content();
		if (ad_content != null && ad_content != "") {
			System.out.println("ad_content : " + ad_content);
			adadd.setAd_content(ad_content);
			opMap.put("ad_content", ad_content);
		}

		op_contentValues = multi.getParameterValues("op_content");// adadd.getOp_content();
		op_timesValues = multi.getParameterValues("op_times");
		op_clock1Values = multi.getParameterValues("op_clock1");
		op_clock2Values = multi.getParameterValues("op_clock2");
		op_priceValues = multi.getParameterValues("op_price");
		op_personnelValues = multi.getParameterValues("op_personnel");
		xxx = multi.getParameter("ad_code");

		System.out.println("op_adcodeop_adcode = " + xxx);
		op_trainerValues = multi.getParameterValues("op_trainer");
		if (mb.getM_kind().equals("t")) {
			ad_nameTId = new ArrayList<>();
			for (int i = 0; i < op_trainerValues.length; i++) {
				if (op_trainerValues[i] != "") {
					String[] ad_nameT = op_trainerValues[i].split(",");
					System.out.println("ad_nameT[0] : " + ad_nameT[0]);
					System.out.println("ad_nameT[1] : " + ad_nameT[1]);
					ad_nameTId.add(ad_nameT[1]);// ad_name3
					String ad_nameTName = ad_nameT[0];// ad_name
					System.out.println(i + "트레이너아이디 출력 " + ad_nameTId.get(i));// ad_name3
					System.out.println(i + "트레이너이름  : " + ad_nameTName);// ad_name
				}
			}
		}

		// day
		dayValues = multi.getParameterValues("day");
		String b = "";
		for (int i = 0; i < dayValues.length; i++) {
			if (dayValues[i] != "") {
				b += dayValues[i];
			}
		}
		System.out.println("b=" + b);
		day1 = b.split("@"); // @ 기준으로 나누기

		// 일반 일 수
		op_periodValues = multi.getParameterValues("op_period");
		if (op_periodValues.length != 0 && op_periodValues != null) {
			for (int i = 0; i < op_periodValues.length; i++) {
				System.out.println("op_period출력 : " + op_periodValues[i]);
			}
		}

		// period
		// 달력
		op_period_1sValues = multi.getParameterValues("op_period1");// adadd.getOp_period1().split(",");
		// op_period_1[0] = mm/dd/yyyy
		if (op_period_1sValues.length != 0 && op_period_1sValues != null) {
			for (int i = 0; i < op_period_1sValues.length; i++) {
				System.out.println("op_period_1출력i " + op_period_1sValues[i]);
			}

		}

		op_period_2sValues = multi.getParameterValues("op_period2");// adadd.getOp_period1().split(",");
		// op_period_1[0] = mm/dd/yyyy
		if (op_period_2sValues.length != 0 && op_period_2sValues != null) {
			System.out.println("op_period_2출력 " + op_period_2sValues);
			for (int i = 0; i < op_period_2sValues.length; i++) {
				System.out.println("op_period_2출력i " + op_period_2sValues[i]);
			}
		}

		adadd.setAd_status("모집중");
		opMap.put("ad_status", "모집중");

		System.out.println("xxxxxxxxx  = " + xxx);
		adadd.setXxx(xxx);
		opMap.put("op_adcode", xxx);
		if (mDao.admodify(adadd)) {
			System.out.println("광고입력성공");

			String root = multi.getSession().getServletContext().getRealPath("/");
			System.out.println("root=" + root);
			String path = root + "resources/upload/";
			// 2.폴더 생성을 꼭 할것...
			File dir = new File(path);
			if (!dir.isDirectory()) { // upload폴더 없다면
				dir.mkdirs(); // upload폴더 생성
			}
			Map<String, String> map = new HashMap<>();
			List<MultipartFile> files = multi.getFiles("ap_image");
			String oriFileName = null;
			String sysFileName = null;
			System.out.println("::::::" + files.size());
			try {
				for (int i = 0; i < files.size(); i++) {
					oriFileName = files.get(i).getOriginalFilename();
					String index = String.valueOf(i);
					sysFileName = index + System.currentTimeMillis() + "."
							+ oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
					System.out.println("oriFileName=" + oriFileName);
					System.out.println("sysFileName=" + sysFileName);
					map.put("ap_adcode", xxx);
					map.put("ap_image", sysFileName);
					System.out.println("ap_adcode=" + map.get("ap_adcode"));
					System.out.println("ap_image=" + map.get("ap_image"));

					if (sysFileName != null) {
						files.get(i).transferTo(new File(path + sysFileName));
						mDao.deletepf(m.getM_id());
						if (mDao.adpfmodify(map)) {
							System.out.println(i + "번째 광고사진 업로드 및 등록 성공");
						}
					}
				}
			} catch (Exception e) {
				System.out.println("AdPhoto Insert fail");
			}

			System.out.println("광고입력성공2");
			System.out.println("xxx : " + adadd.getXxx());
			System.out.println("xxx : " + adadd.getXxx());
			System.out.println("xxop_contentValues.length : " + op_contentValues.length);
			for (int i = 0; i < op_contentValues.length; i++) {
				opMap.put("op_adcode", xxx);
				opMap.put("op_content", op_contentValues[i]);
				opMap.put("op_price", op_priceValues[i]);
				if (mb.getM_kind().equals("t")) {
					if (ad_nameTId.size() != 0)
						opMap.put("op_trainer", ad_nameTId.get(i));
					System.out.println("aaaa=" + ad_nameTId.get(i));
				} else if (mb.getM_kind().equals("c")) {
					if (op_trainerValues[i] != "") {
						String []ad = op_trainerValues[i].split(",");
						opMap.put("op_trainer", ad[0]);
						System.out.println("pppppp=" + op_trainerValues[i]);
					}
				}

				if (ad_category.equals("normal")) {// 일반일때
					opMap.put("op_period", op_periodValues[i]);
					System.out.println("bbbb=" + op_periodValues[i]);

					opMap.put("op_personnel", "-100");
					System.out.println("qqqq=" + op_personnelValues[i]);

					opMap.put("op_times", op_timesValues[i]);
					opMap.put("op_day", day1[i]);

				} else {// 일반 제외 다른 프로그램일때
					if (op_period_1sValues[i] != "" && op_period_2sValues[i] != "") {
						opMap.put("op_period", op_period_1sValues[i] + "~" + op_period_2sValues[i]);// 일반일경우 제외
						System.out.println("cccc=" + op_period_1sValues[i] + op_period_2sValues[i]);
					}
					if (op_timesValues[i] != "") {
						opMap.put("op_times", op_timesValues[i]);
						System.out.println("eeee=" + op_timesValues[i]);
					}
					if (day1 != null && day1.length != 0 && day1[i] != "") {
						opMap.put("op_day", day1[i]);
					}
					if (op_personnelValues[i] != "") {
						opMap.put("op_personnel", op_personnelValues[i]);
						System.out.println("qqqq=" + op_personnelValues[i]);
					}
				}

				if (op_clock1Values[i] != "" && op_clock2Values[i] != "") {
					opMap.put("op_clock", op_clock1Values[i] + "~" + op_clock2Values[i]);
					System.out.println("dddd=" + op_clock1Values[i] + op_clock2Values[i]);
				}

				System.out.println(opMap.get("op_trainer"));
				System.out.println(opMap.get("op_period"));
				System.out.println(opMap.get("op_adcode"));
				if (mDao.opmodify(opMap)) {
					System.out.println(i + "옵션입력성공");
				}
			}
		} else {
			System.out.println("광고등록실패");
		}
		if (mb.getM_kind().equals("t")) {
			mav.setViewName("forward:/trainer");
		} else if (mb.getM_kind().equals("c")) {
			mav.setViewName("forward:/company");
		}
		return mav;
	}

}