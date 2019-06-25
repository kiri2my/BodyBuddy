package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.dao.MemberDao;
import com.bodybuddy.hey.userClass.UploadFile;
import com.google.gson.Gson;

@Service
public class MemberManagemant {
	@Autowired
	MemberDao mDao;
	@Autowired
	HttpSession session; // request는 권장하지 않음
	@Autowired
	private UploadFile upload;

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

	public ModelAndView infomodifyn(MultipartHttpServletRequest multi) {
		System.out.println("mm  infomodifyn 시작");
		String view = null;
		Member mb = new Member();

		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id = sessionMb.getM_id();
		String m_birth = sessionMb.getM_birth();
		System.out.println("시작해보자");
		System.out.println(m_birth);
		System.out.println(m_birth);
		String m_name = sessionMb.getM_name();
		String m_pw = multi.getParameter("m_pw");
		System.out.println(m_pw);
		System.out.println(m_pw);
		String m_phone = multi.getParameter("m_phone");
		System.out.println(m_phone);
		System.out.println(m_phone);
		String m_addr = multi.getParameter("m_addr");
		System.out.println(m_addr);
		System.out.println(m_addr);
		String m_exaddr = multi.getParameter("m_exaddr");
		System.out.println(m_exaddr);
		System.out.println(m_exaddr);
		String pf_image = multi.getParameter("pf_image");
		System.out.println(pf_image);
		System.out.println(pf_image);

		int i = mDao.imgOverlap(m_id);
		if (i == 0) {
			System.out.println("if 가 0이다 이말이야");
			upload.fileUp(multi, m_id);
			BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
			mb.setM_id(m_id);
			mb.setM_pw(pwdEncoder.encode(m_pw));
			mb.setM_phone(m_phone);
			mb.setM_addr(m_addr);
			mb.setM_exaddr(m_exaddr);
			mb.setM_birth(m_birth);
			mb.setM_name(m_name);
			mb.setPf_image(pf_image);
			mDao.updateNorMb(mb);
			Member mb1 = mDao.getModifyN(m_id);
			Member mbPhoto = mDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
		} else if (i >= 1) {
			System.out.println("if 가 1이다 이말이야");

			upload.fileUp2(multi, m_id);
			BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
			mb.setM_id(m_id);
			mb.setM_pw(pwdEncoder.encode(m_pw));
			mb.setM_phone(m_phone);
			mb.setM_addr(m_addr);
			mb.setM_exaddr(m_exaddr);
			mb.setM_birth(m_birth);
			mb.setM_name(m_name);
			mb.setPf_image(pf_image);
			mDao.updateNorMb(mb);
			Member mb1 = mDao.getModifyN(m_id);
			Member mbPhoto = mDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
		}
		mav.setViewName("manage/trainer/trainer");

		return mav;
	}

	public String deleteAd(String ad_code) {

		Integer success = mDao.deleteAd(ad_code);
		Gson gson = new Gson();
		String str = gson.toString();
		return str;
	}

	public ModelAndView advertiseModifyFrm(String ad_code) {
		// int i = mDao.opcount(ad_code);
		mav = new ModelAndView();
		Member mb = (Member) session.getAttribute("mb");
		System.out.println("메멘의 수정폼입니다");
		ArrayList<Question> opList = mDao.advertiseModifyFrm(ad_code);
		System.out.println("dao입니다");
		System.out.println("opList1" + opList.size());
		String makeopHtml = opHtml(opList, opList.size());

		Question list = new Question();
		System.out.println("list" + list.getOp_code());
		System.out.println(opList);

		
		mav.addObject("opHtml", makeopHtml);
		mav.addObject("opList", opList);
		mav.setViewName("manage/advertisemodifyfrm2");
		System.out.println("메멘의 리턴입니다");
		return mav;
	}

	private String opHtml(ArrayList<Question> opList, int opSize) {
		StringBuilder sb = new StringBuilder();
		String str  = "";
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
		System.out.println(opList.get(0).getOp_adcode());
		String[] op_period = opList.get(0).getOp_period().split("~");
		String[] op_day = opList.get(0).getOp_day().split(" ");
		String[] op_clock = opList.get(0).getOp_clock().split("~");
		String ad_content = opList.get(0).getAd_content();
		String ad_title = opList.get(0).getAd_title();
		String ad_category = opList.get(0).getAd_category();
		Member m = (Member) session.getAttribute("mb");
		if (ad_category.equals("fitness")) {
			fitness = "selected=\"selected\"";
		}
		if (ad_category.equals("yoga")) {
			yoga = "selected=\"selected\"";
		}
		if (ad_category.equals("pt")) {
			pt = "selected=\"selected\"";
		}
		if (ad_category.equals("pilates")) {
			pilates = "selected=\"selected\"";
		}

		str = ("<form id=\"frm\" action=\"adinsert\">\r\n" + "  <div class=\"container\">\r\n"
				+ "   <table id=\"recent-purchases-listing\" class=\"table\">\r\n" + "    <tbody>\r\n" + "     <tr>\r\n"
				+ "      <th style=\"width: 200px\">분류</th>\r\n" + "\r\n"
				+ "      <td style=\"vertical-align: middle\">\r\n"
				+ "      <select id=\"ex-select\" name=\"ad_category\" onchange=\"selectExercise()\" class=\"form-control\" style=\"width: 150px;\">\r\n"
				+ "        <option class=\"nothing\" value=\"nothing\" >선택해주세요</option> \r\n"
				+ "        <option class=\"fitness\" value=\"fitness\" " + fitness + ">피트니스</option>\r\n"
				+ "        <option class=\"yoga\" value=\"yoga\" " + yoga + ">요가</option>\r\n"
				+ "        <option class=\"pt\" value=\"pt\" " + pt + ">개인PT</option>\r\n"
				+ "        <option class=\"pilates\" value=\"pilates\" " + pilates + ">필라테스</option>\r\n"
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
				+ "           \r\n" + "          </tr>\r\n" + "          <tr>\r\n");
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

				str2 = ("          <tr>\r\n"
						+ "           <td><input type=\"text\" name=\"op_content\"	 style=\"width: 100px\" placeholder=\"옵션명\"  class=\"reset\" value="
						+ opList.get(i).getOp_content() + "></td>\r\n" + "           <td >\r\n"
						+ "            <label for=\"from\" >From</label><input type=\"text\"  id=\"from\" name=\"op_period1\" class=\"from\" value="
						+ op_period[0] + ">\r\n"
						+ "             <label for=\"to\">to</label><input type=\"text\" id=\"to\" name=\"op_period2\" class=\"to\" value="
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
						+ "         name=\"day\" value=\"@\">\r\n" + "        </label>\r\n" + "       </div></td>\r\n"
						+ "           <td><input type=\"text\" name=\"op_times\" style=\"width: 50px\" placeholder=\"횟수\" class=\"reset\" value="
						+ opList.get(i).getOp_times() + "></td>\r\n"
						+ "           <td><input type=\"text\" name=\"op_personnel\" placeholder=\"모집인원\" class=\"reset\" value="
						+ opList.get(i).getOp_personnel() + ">명</td>\r\n"
						+ "           <td><input type=\"text\" name=\"op_price\" placeholder=\"가격(원)\" class=\"reset\" value="
						+ opList.get(i).getOp_price() + "></td>\r\n"
						+ "           <td><select name=\"op_trainer\"><option value=" + m.getM_name() + "," + m.getM_id() + ">\r\n"  
						+ m.getM_name() + "</option></select></td> </tr>\r\n");
			}
			str3 += str2;
		}
		System.out.println("최종입니다" + str2);
		sb.append(str + str3);

		return sb.toString();
	}
	
	/*
	 * public ModelAndView advertisetrlist(Member mb) { mav = new ModelAndView();
	 * String trainerlist = makeHtmltrainerlist(mb);
	 * 
	 * mav.addObject("trainerlist", trainerlist);
	 * 
	 * 
	 * return mav; }
	 * 
	 * private String makeHtmltrainerlist(Member mb) { StringBuilder sb = new
	 * StringBuilder(); String id = mb.getM_id(); System.out.println("id:" + id); //
	 * 다오가즈아 m = mDao.kindkind(id); String kind = m.getM_kind();
	 * System.out.println("KIND : " + kind); System.out.println("Name : " +
	 * m.getM_name());
	 * 
	 * System.out.println("트레이너네요");
	 * sb.append("<select name=\"op_trainer\"><option value=" + m.getM_name() + ","
	 * + m.getM_id() + ">" + m.getM_name() + "</option></select>");
	 * System.out.println("저장되었다"); return sb.toString();
	 * 
	 * }
	 */

}