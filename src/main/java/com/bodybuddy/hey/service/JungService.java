package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.dao.MemberDao;
import com.google.gson.Gson;

@Service
public class JungService {
	@Autowired
	private MemberDao mDao;

	private HttpSession session;

	Member m;
	String view = null;
	ModelAndView mav;

	public ModelAndView getProfileList(String id) {
		System.out.println("정 서비스");
		mav = new ModelAndView();
		String view = null;

		m = new Member();
		m = mDao.getProfileList(id);

		if (null != m) {
			System.out.println("profile list select success");

			view = "manage/profileModifyT";
			mav.setViewName(view);

			mav.addObject("m", m);
		} else {
			System.out.println("profile list select error");
			view = "redirect:profileModifyT.jsp";
			mav.setViewName(view);
		}

		return mav;
	}

	private String makeHTMLProfileList(String bname, String addr, String state) {
		StringBuilder sb = new StringBuilder();
		sb.append("									<table id=\"recent-purchases-listing\" class=\"table\">\n"
				+ "											<thead>\n"
				+ "												<tr>\n"
				+ "													<th>업체이름</th>\n"
				+ "													<th>업체주소</th>\n"
				+ "													<th>요청</th>\n"
				+ "													<th>요청취소</th>\n"
				+ "													<th>상태</th>\n"
				+ "												</tr>\n"
				+ "											</thead>\n"
				+ "											<tbody>\n"
				+ "													<tr>\n"
				+ "														<td>" + bname + "</td>\n"
				+ "														<td>" + addr + "</td>\n"
				+ "														<td><button onclick='request()'>요청</button></td>\n"
				+ "														<td><button onclick='cancel()'>취소</button></td>\n"
				+ "														<td>" + state + "</td>\n"
				+ "													</tr>\n"
				+ "											</tbody>\n" + "									</table>");
		return sb.toString();
	}

	public String getTfindC(String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		m.setC_bname(name);
		List<Member> mList = new ArrayList<Member>();
		mList = mDao.getTfindC(m);
		System.out.println("다오다녀옴");
		String addr = mList.get(0).getM_addr();
		String bname = m.getC_bname();
		System.out.println("상태보여라");
		System.out.println("상태 : " + mList.get(0).getYn_state());
		String state = mList.get(0).getYn_state();
		System.out.println("bname=" + bname);
		System.out.println("addr=" + addr);
		System.out.println("state=" + state);
		String html = null;
		System.out.println("DB 다녀왔어요!");
		if (0 != mList.size()) {
			System.out.println("company search select success");
			html = makeHTMLProfileList(bname, addr, state);
		} else {
			System.out.println("company search select error");
			/* view = "redirect:profileModifyT.jsp"; */

		}

		return html;

	}

	public ModelAndView questionList(String id) {
		System.out.println("정 서비스");
		mav = new ModelAndView();
		String view = null;

		m = new Member();
		m = mDao.getProfileList(id);
		System.out.println("이거다");
		if (null != m) {
			System.out.println("profile list select success");
			view = "manage/profileModifyT";
			mav.setViewName(view);
			mav.addObject("m", m);
		} else {
			System.out.println("profile list select error");
			view = "redirect:profileModifyT.jsp";
			mav.setViewName(view);
		}
		return mav;
	}

	public ModelAndView getQuestionList(String id) {
		mav = new ModelAndView();

		List<Question> qList = null;
		System.out.println("다오 들어가요!");

		qList = mDao.getQuestionList(id);
		System.out.println("다오 다녀왔어요");

		if (0 != qList.size()) {
			System.out.println("qList size = " + qList.size());
			view = "manage/question/questionList";
			mav.setViewName(view);
			mav.addObject("qList", qList);
		} else {
			System.out.println("없어");
			view = "manage/question/questionList";
			mav.setViewName(view);
		}

		return mav;

	}

	// 취소취소취소취소취소취소취소취소취소취소취소취소취소취소취소
	public String cancel(String id) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		m.setM_id(id);
		System.out.println("m.getM_id(id)" + m.getM_id());
		List<Member> mList = new ArrayList<Member>();
		System.out.println("켄슬전");
		mDao.cancel(id);
		System.out.println("켄슬후");
		mList = mDao.getTfindC(m);
		System.out.println("다오다녀옴");
		String addr = mList.get(0).getM_addr();
		String bname = m.getC_bname();
		System.out.println("상태보여라");
		System.out.println("상태 : " + mList.get(0).getYn_state());
		String state = mList.get(0).getYn_state();
		System.out.println("bname=" + bname);
		System.out.println("addr=" + addr);
		System.out.println("state=" + state);
		String html = null;
		System.out.println("DB 다녀왔어요!");
		if (0 != mList.size()) {
			System.out.println("company search select success");
			html = makeHTMLcancel(bname, addr, state);
		} else {
			System.out.println("company search select error");
			/* view = "redirect:profileModifyT.jsp"; */

		}

		return html;
	}

	// 취소취소취소취소취소취소취소취소취소취소취소취소취소취소취소
	private String makeHTMLcancel(String bname, String addr, String state) {
		StringBuilder sb = new StringBuilder();
		sb.append("									<table id=\"recent-purchases-listing\" class=\"table\">\n"
				+ "											<thead>\n"
				+ "												<tr>\n"
				+ "													<th>업체이름</th>\n"
				+ "													<th>업체주소</th>\n"
				+ "													<th>요청</th>\n"
				+ "													<th>요청취소</th>\n"
				+ "													<th>상태</th>\n"
				+ "												</tr>\n"
				+ "											</thead>\n"
				+ "											<tbody>\n"
				+ "													<tr>\n"
				+ "														<td>" + bname + "</td>\n"
				+ "														<td>" + addr + "</td>\n"
				+ "														<td><button onclick='request()'>요청</button></td>\n"
				+ "														<td><button onclick='cancel()'>취소</button></td>\n"
				+ "														<td>" + state + "</td>\n"
				+ "													</tr>\n"
				+ "											</tbody>\n" + "									</table>");
		return sb.toString();
	}
	// 요청요청요청요청요청요청

	public String acceptrequest(String id, String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		m.setM_id(id);
		m.setC_bname(name);
		System.out.println("id:"+m.getM_id());
		System.out.println("name:"+m.getC_bname());
		System.out.println("m.getM_id(id)" + m.getM_id());
		List<Member> mList = new ArrayList<Member>();
		System.out.println("켄슬전");

		System.out.println("update");
		mDao.acceptrequestupdate(id, name);

		/*
		 * System.out.println("insert"); mDao.acceptrequestInsert(id, name);
		 */

		/*
		 * mList = mDao.getTfindC(m); System.out.println("다오다녀옴"); String addr =
		 * mList.get(0).getM_addr(); String bname = m.getC_bname();
		 * System.out.println("상태보여라"); System.out.println("상태 : " +
		 * mList.get(0).getYn_state()); String state = mList.get(0).getYn_state();
		 * System.out.println("bname=" + bname); System.out.println("addr=" + addr);
		 * System.out.println("state=" + state); String html = null;
		 * System.out.println("DB 다녀왔어요!"); if (0 != mList.size()) {
		 * System.out.println("company search select success"); html =
		 * makeHTMLcancel(bname, addr, state); } else {
		 * System.out.println("company search select error"); view =
		 * "redirect:profileModifyT.jsp";
		 * 
		 * }
		 */

		return "zzz";
	}

}