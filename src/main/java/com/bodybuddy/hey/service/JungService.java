package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

	public ModelAndView getProfileList(String m_id) {
		System.out.println("정 서비스");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		System.out.println("696969696969696969   " + m_id);
		m = mDao.getProfileList(m_id);

		view = "manage/profileModifyT";
		mav.setViewName(view);

		mav.addObject("m", m);

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
				+ "														<td><button onclick='acceptrequest()'>요청</button></td>\n"
				+ "														<td><button onclick='cancel()'>취소</button></td>\n"
				+ "														<td id='state'>" + state + "</td>\n"
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
		List<Member> mList = new ArrayList<Member>();

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
			//session.setAttribute("qLists", qList);
			//qList.get(0).
		} else {
			System.out.println("없어");
			view = "manage/question/questionList";
			mav.setViewName(view);
		}

		return mav;

	}

	// 취소취소취소취소취소취소취소취소취소취소취소취소취소취소취소
	public String cancel(Member mb) {
		String cc = null;
		String id = mb.getM_id();
		if (mDao.cancel(id)) {
			System.out.println("취소완료");
			cc = "취소";
		}

		else {
			System.out.println("취소실패");
		}

		return cc;
	}

	// 요청요청요청요청요청요청

	public String acceptrequest(Member mb, String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		m.setM_id(mb.getM_id());
		m.setC_bname(name);
		System.out.println("id:" + m.getM_id());
		System.out.println("name:" + m.getC_bname());

		System.out.println("들어가자");

		if (mDao.acceptrequestupdate(m)) {
			System.out.println("update 완료");
		} else if (mDao.acceptrequestInsert(m)) {
			System.out.println("insert 완료");
		}
		System.out.println("나오자");

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

	public ModelAndView adinsert(Question adadd, String[] day, String[] op_content) {

		String ad_title = adadd.getAd_title();
		System.out.println("ad_title : " + ad_title);

		String ad_content = adadd.getAd_content();
		System.out.println("ad_content : " + ad_content);

		for (int i = 0; i < day.length; i++) {
			System.out.println("day출력 : " + day[i]);
		}

		int op_price = adadd.getOp_price();
		System.out.println("op_price : " + op_price);

		for (int i = 0; i < op_content.length; i++) {
			System.out.println("op_content출력 : " + op_content[i]);
		}

		String op_trainer = adadd.getOp_trainer();
		System.out.println("op_trainer : " + op_trainer);

		return null;
	}

	public ModelAndView profileComplete(String id) {
		mDao.profileComplete(id);
		return null;
	}

}