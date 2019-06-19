package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.bean.YesOrNo;
import com.bodybuddy.hey.dao.KirimDao;
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
	Question q;

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
	@Transactional
	public ModelAndView adinsert(Question adadd, String[] day) {
		
		mav = new ModelAndView();
		
		String day1 = Arrays.toString(day);
		System.out.println("요일 "+day1);
		day1.substring(1, day1.length()-1);
		adadd.setOp_day(day1);
		
		String ad_name = adadd.getAd_name();
		System.out.println("ad_name : "+ad_name);
		
		String ad_category = adadd.getAd_category();
		System.out.println("ad_category : "+ ad_category );
			
		String ad_title = adadd.getAd_title();
		System.out.println("ad_title : " + ad_title);

		String ad_content = adadd.getAd_content();
		System.out.println("ad_content : " + ad_content);
		
		String op_period1 = adadd.getOp_period1().replace("/", ""); //06072019 
		String x1= op_period1.substring(4);
		String y1= op_period1.substring(0, 4);
		String z1= x1+y1;
		
		String op_period2 = adadd.getOp_period1().replace("/", ""); //06072019 
		String x2= op_period2.substring(4);
		String y2= op_period2.substring(0, 4);
		String z2= x2+y2;
		
		
		System.out.println(z1);
		System.out.println(z2);
		
		String op_period = z1+"~"+z2;
		/* op_period.replace("/", ""); */
		adadd.setOp_period(op_period);
		System.out.println("op_period : "+ op_period);
		
		int op_price = adadd.getOp_price();
		System.out.println("op_price : " + op_price);

		String op_content = adadd.getOp_content();
		System.out.println("op_content출력 : " + op_content);
		
		String op_trainer = adadd.getOp_trainer();
		System.out.println("op_trainer : " + op_trainer);

		String ap_image = adadd.getAp_image();
		System.out.println("ap_image : " + ap_image);
		
		
		adadd.setAd_status("모집중");
		
		 if(mDao.adinsert(adadd)) {
			 System.out.println("광고입력성공");
			 System.out.println("xxx : "+adadd.getXxx());
			 if(mDao.opinsert(adadd)) {
				 System.out.println("옵션입력성공");
				 if(!adadd.getAp_image().equals(null)||adadd.getAp_image().equals(""	)) {
					 mDao.imginsert(adadd);
					 System.out.println("이미지등록성공");
				 }else System.out.println("이미지가 없음");
				 mav.setViewName("manage/advertisemanage");
			 return mav;
			 }
		 }else {
			 System.out.println("광고등록실패");
		 }
		 
		
		return null;
	}

	public ModelAndView profileComplete(String id) {
		mDao.profileComplete(id);
		return null;
	}

	public ModelAndView advertisewriterfrm(Member mb) {
		mav = new ModelAndView();
		System.out.println("광고입력폼");
		
		
		String trainerlist = makeHtmltrainerlist(mb);
		System.out.println(trainerlist);
		
		mav.addObject("trainerlist",trainerlist);
		String view="manage/advertisewritefrm";
		mav.setViewName(view);
		System.out.println("서비스 끝");
		return mav;
	}

	private String makeHtmltrainerlist(Member mb) {
		StringBuilder sb = new StringBuilder();
		String id=mb.getM_id();
		System.out.println("id:"+id);
		//다오가즈아
		m = mDao.kindkind(id);
		String kind = m.getM_kind();
		System.out.println("KIND : "+kind);
		System.out.println("Name : "+m.getM_name());
		if(kind.equals("t")) {
			System.out.println("트레이너네요");
			sb.append("<select name=\"op_trainer\"><option value="+m.getM_name()+">"+m.getM_name()+"</option></select>");
			System.out.println("저장되었다");
			return sb.toString();
		}else if(kind.equals("c")){
			List<YesOrNo> yn= mDao.trinerlist(id);
			sb.append("<select name=\"op_trainer\">\r\n" + 
					"    <option value=\"\">트레이너 선택</option>\r\n");
			for(int i = 0;i<yn.size();i++) {
				sb.append("<option value=\"트레이너\">"+yn.get(i).getYn_trainer()+"</option>\r\n" 
						);
			}
		sb.append("</select>");
				 
		}
		return sb.toString();
	}

	public ModelAndView getAdvertisemanage(String id) {
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		
		List<Question> adList = null;
		
		
		adList = mDao.getAdvertiselist(id);
		System.out.println("이거다");
		if (0 != adList.size()) {
			System.out.println("advertise list select success");
			view = "manage/advertisemanage";
			mav.setViewName(view);
			mav.addObject("adList", adList);
		} else {
			System.out.println("advertise list select error");
			view = "redirect:advertisemanage.jsp";
			mav.setViewName(view);
		}
		return mav;
	}

}