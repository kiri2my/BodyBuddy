package com.bodybuddy.hey.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

		int count = mDao.comfirm1(m_id);

		System.out.println("count:" + count);
		if (count != 0) {
			Member m1 = mDao.getSosok(m_id);
			mav.addObject("m1", m1);
		}

		view = "manage/profileModifyT";
		mav.setViewName(view);

		mav.addObject("m", m);

		return mav;
	}

	private String makeHTMLProfileList(String bname, String addr) {
		StringBuilder sb = new StringBuilder();
		sb.append("         <table id=\"recent-purchases-listing\" class=\"table\">\n" + "           <thead>\n"
				+ "            <tr>\n" + "             <th>업체이름</th>\n" + "             <th>업체주소</th>\n"
				+ "             <th>요청</th>\n" + "             <th>요청취소</th>\n" + "            </tr>\n"
				+ "           </thead>\n" + "           <tbody>\n" + "             <tr>\n" + "              <td>"
				+ bname + "</td>\n" + "              <td>" + addr + "</td>\n"
				+ "              <td><button onclick='acceptrequest()'>요청</button></td>\n"
				+ "              <td><button onclick='cancel()'>취소</button></td>\n" + "             </tr>\n"
				+ "           </tbody>\n" + "         </table>");
		return sb.toString();
	}

	public String getTfindC(String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;
		m = new Member();
		m.setC_bname(name);

		m = mDao.getTfindC(m);
		System.out.println("다오다녀옴");
		String addr = m.getM_addr();
		String bname = m.getC_bname();
		System.out.println("상태보여라");
		System.out.println("상태 : " + m.getYn_state());
		String html = null;
		System.out.println("DB 다녀왔어요!");

		System.out.println("company search select success");
		html = makeHTMLProfileList(bname, addr);

		System.out.println("company search select error");

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
			// session.setAttribute("qLists", qList);
			// qList.get(0).
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

	public ModelAndView acceptrequest(Member mb, String name) {
		System.out.println("드루와!!");
		mav = new ModelAndView();
		String view = null;

		m = new Member();

		m = mDao.findC_id(name);
		m.setM_id(mb.getM_id());
		m.setC_bname(name);

		System.out.println("회사 아이디c_id : " + m.getC_id());
		System.out.println("로그인한 본인 t_id:" + m.getM_id());
		System.out.println("회사이름 name:" + m.getC_bname());

		int count = mDao.comfirm(m);
		System.out.println("count : " + count);

		/* m.getC_id()!= 디비에있는 업체아이디; */
		try {
			if (count == 1)
				mDao.acceptrequestupdate(m);
			if (count == 0)
				mDao.acceptrequestInsert(m);

		} catch (Exception e) {

		}

		System.out.println("나오자");

		mav.setViewName("manage/profileModifyT");

		return mav;
	}

	@Transactional
 public ModelAndView adinsert(Question adadd, String[] day,String op_trainer1) {

  mav = new ModelAndView();


  int checkNum = adadd.getCheckNum();
  System.out.println("checkNum : " + checkNum);
  
  /* adadd.setOp_day(); */
  String ad_name1 = adadd.getAd_name();
  System.out.println("ad_name1"+ad_name1);
  String[] ad_name2 = op_trainer1.split(",");
  System.out.println("ad_name2[0] : "+ad_name2[0]);
  System.out.println("ad_name2[1] : "+ad_name2[1]);
  String ad_name = ad_name2[0];
  String ad_name3 = ad_name2[1];
  
  
  System.out.println("트레이너이름 : "+ ad_name3);
  System.out.println("ad_name : " + ad_name);

  String ad_category = adadd.getAd_category();
  System.out.println("ad_category : " + ad_category);

  String ad_title = adadd.getAd_title();
  System.out.println("ad_title : " + ad_title);

  String ad_content = adadd.getAd_content();
  System.out.println("ad_content : " + ad_content);

  // content
  String op_content = adadd.getOp_content();
  System.out.println("op_content출력 : " + op_content);
  String[] op_content_1 = op_content.split(",");
  System.out.println("op_content_1.op_content_1."+op_content_1.length);
  
  // day
  String a = Arrays.toString(day);
  String a2=a.replace(",", ""); //컴마없애기
  String a3 = a2.substring(1,a2.length()-1); //앞뒤 대괄호 없애기
  String[] day1 = a3.split("@"); // @ 기준으로 나누기
  // period
  // 달력
  
  
   String[] op_period_1 = adadd.getOp_period1().split(",");
   //op_period_1[0] = mm/dd/yyyy 
   for(int i=0; i<op_period_1.length; i++) {
    op_period_1[i] = op_period_1[i].replace("/","");
    String x = op_period_1[i].substring(4);
    String y = op_period_1[i].substring(0,4);
    
    op_period_1[i] = x+y;
   }
 
  String[] op_period_2 = adadd.getOp_period2().split(",");// 달력
  for(int i=0; i<op_period_2.length; i++) {
    op_period_2[i] = op_period_2[i].replace("/","");
    String x = op_period_2[i].substring(4);
    String y = op_period_2[i].substring(0,4);
    
    op_period_2[i] = x+y;
   }

 
  String op_times = adadd.getOp_times();
  System.out.println("op_times" + op_times);
  String[] op_times_1 = op_times.split(",");
  // clock
  // 시작시간
  String op_clock1 = adadd.getOp_clock1();
  System.out.println("op_clock" + op_clock1);
  String[] op_clock_1 = op_clock1.split(",");

  // 종료시간
  String op_clock2 = adadd.getOp_clock2();
  System.out.println("op_clock" + op_clock2);
  String[] op_clock_2 = op_clock2.split(",");

  String op_price = adadd.getOp_price();
  System.out.println("op_price : " + op_price);
  String[] op_price_1 = op_price.split(",");
  System.out.println("op_price_1"+ op_price_1[0]);
  System.out.println("op_price_1"+ op_price_1[1]);
  
  String op_trainer = adadd.getOp_trainer();
  System.out.println("op_trainer : " + op_trainer);
  String[] op_trainer_1 = op_trainer.split(",");
  
  String op_personnel = adadd.getOp_personnel();
  System.out.println("op_personnel : "+op_personnel);
  String[] op_personnel_1 = op_personnel.split(",");

 

  
  String ap_image = adadd.getAp_image();
  System.out.println("ap_image : " + ap_image);

  adadd.setAd_status("모집중");

  // advertise insert
  System.out.println();
  
  
  Map<String,String> opMap = new HashMap<String,String>();  
  String xxx = null;
  if (mDao.adinsert(adadd)) {
   System.out.println("광고입력성공");
   System.out.println("xxx : " + adadd.getXxx());
   xxx= adadd.getXxx();
   opMap.put("op_adcode",xxx);
   System.out.println(op_content);
   System.out.println("op_content.length() 의 수"+op_content.length());
   for(int i=0; i<op_content_1.length; i++) {
    System.out.println("반복문 op_price_1[i] = "+i+" 번째 "+op_price_1[i]);
    System.out.println(ad_category);
    opMap.put("ad_category",ad_category);
    opMap.put("op_trainer", ad_name3);
    opMap.put("op_price",op_price_1[i]);
    opMap.put("op_period",op_period_1[i] +"~"+  op_period_2[i]);
    opMap.put("op_times", op_times_1[i]);
    opMap.put("op_day",  day1[i]);
    opMap.put("op_content",op_content_1[i]);
    opMap.put("op_clock",op_clock_1[i] + "~"  + op_clock_2[i]);
    opMap.put("op_personnel",op_personnel_1[i]);
    System.out.println(opMap.get("op_trainer"));
    System.out.println(opMap.get("op_period"));
    System.out.println(opMap.get("op_adcode"));
   if (mDao.opinsert(opMap)) {
    System.out.println("옵션입력성공");
   }
    
   }
   mav.setViewName("manage/trainer/trainer");
   return mav;
  } else {
   System.out.println("광고등록실패");
  }

  return mav;
 }

public ModelAndView profileComplete(Member mb, String t_career) {

  mav = new ModelAndView();
  System.out.println("t_career:" + t_career);
  mb.setT_career(t_career);
  mDao.profileComplete(mb);
  String view = "manage/trainer/trainerMain";
  mav.setViewName(view);
  return mav;
 }

 public ModelAndView advertisewriterfrm(Member mb) {
  mav = new ModelAndView();
  System.out.println("광고입력폼");

  String trainerlist = makeHtmltrainerlist(mb);
  System.out.println(trainerlist);

  mav.addObject("trainerlist", trainerlist);
  String view = "manage/advertisewritefrm";
  mav.setViewName(view);
  System.out.println("서비스 끝");
  return mav;
 }

 private String makeHtmltrainerlist(Member mb) {
  StringBuilder sb = new StringBuilder();
  String id = mb.getM_id();
  System.out.println("id:" + id);
  // 다오가즈아
  m = mDao.kindkind(id);
  String kind = m.getM_kind();
  System.out.println("KIND : " + kind);
  System.out.println("Name : " + m.getM_name());
  if (kind.equals("t")) {
   
   
   System.out.println("트레이너네요");
   sb.append("<select name=\"op_trainer\"><option value=" + m.getM_name()+","+m.getM_id() + ">" + m.getM_name()
     + "</option></select>");
   System.out.println("저장되었다");
   return sb.toString();
   
   
   
   
  } else if (kind.equals("c")) {//업체
   List<YesOrNo> yn = mDao.trinerlist(id);
   sb.append("<select name=\"op_trainer\">\r\n" + "    <option value=\"\">트레이너 선택</option>\r\n");
   for (int i = 0; i < yn.size(); i++) {
    sb.append("<option value=\"트레이너\">" + yn.get(i).getYn_trainer() + "</option>\r\n");
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
   view = "manage/advertisemanage";
   mav.setViewName(view);
  }
  return mav;
 }

}