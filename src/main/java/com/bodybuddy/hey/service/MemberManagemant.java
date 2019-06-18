package com.bodybuddy.hey.service;

import java.util.ArrayList;
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
import com.bodybuddy.hey.dao.YoonDao;
import com.bodybuddy.hey.userClass.UploadFile;

@Service
public class MemberManagemant {
	@Autowired
	MemberDao mDao;
	@Autowired
	HttpSession session; // request는 권장하지 않음
	@Autowired
	private UploadFile upload;
	@Autowired
	private YoonDao yDao;

	Member m;

	ModelAndView mav;

	String view = null;

	public ModelAndView normalMemberJoin(Member mb) {
		System.out.println("맴버 매니지맨트 시작");
		mav = new ModelAndView();
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));

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
		System.out.println("씨발");
		Question qa = mDao.qNaCheck(qa_num);
		System.out.println("씨이발!!!!!!!!!!!" + qa);
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
		String view=null;
		Member mb=new Member();

		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id=sessionMb.getM_id();
		String m_birth=sessionMb.getM_birth();
		String m_name=sessionMb.getM_name();
		String m_pw=multi.getParameter("m_pw");
		System.out.println(m_pw);
		System.out.println(m_pw);
		String m_phone=multi.getParameter("m_phone");
		System.out.println(m_phone);
		System.out.println(m_phone);
		String m_addr=multi.getParameter("m_addr");
		System.out.println(m_addr);
		System.out.println(m_addr);
		String m_exaddr=multi.getParameter("m_exaddr");
		System.out.println(m_exaddr);
		System.out.println(m_exaddr);
		String pf_image=multi.getParameter("pf_image");
		System.out.println(pf_image);
		System.out.println(pf_image);

		
		 int i=mDao.imgOverlap(m_id);
		 if(i==0) {
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
			Member mb1=yDao.getModifyN(m_id);
			Member mbPhoto = yDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
		 }else if(i>=1) {
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
			Member mb1=mDao.getModifyN(m_id);
			Member mbPhoto = mDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
		 }
		 mav.setViewName("manage/trainer/trainer");
		 
		return mav;
	}
}