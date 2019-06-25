package com.bodybuddy.hey.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;
import com.bodybuddy.hey.bean.YesOrNo;

public interface MemberDao {

	public boolean normalMemberJoin(Member mb);

	public boolean trainerMemberJoin(Member mb);

	public boolean companyMemberJoin(Member mb);

	public int checkId(String s);

	public int checkCompanyNum(String c_num);

	public List<Member> forgetId(Member mb);

	public List<Member> forgetPw(Member mb);

	public void temporaryPw(Member mb);
	
	public Member getProfileList(String m_id);

	public List<Member> getTfindC(Member m);

	public List<Question> getQuestionList(String id);

	public boolean cancel(String id);

	public boolean acceptrequestupdate(Member m);

	public List<Member> getNormalMemberList(String id);

	public List<Member> getProgramMemberList(String id);

	public boolean acceptrequestInsert(String id, String name);
	
	public boolean memberDeleteReal(String m_id);

	public void profileComplete(Member mb);

	public boolean acceptrequestInsert(Member m);

	public void DeleteRealId(String m_id);

	public Question qaNum(String qa_num);


	public ArrayList<YesOrNo> trinerlist(String id);

	public Member kindkind(String id);

	public boolean imginsert(Question adadd);


	public boolean questionReply(String qa_acontent, String qa_num);

	public Question qNaCheck(String qa_num);


	public List<Question> getAdvertiselist(String id);

	public Member trainerModifyT(String m_id);

	public Integer deleteAd(String ad_code);
	
	
	
	public Member getPhotoModifyN(String m_id);

	public Member getModifyN(String m_id);

	public void updateNorMb(Member mb);

	public int imgOverlap(String m_id);

	public String pfimage(String m_id);
	
	public boolean adPhotoInsert(Map<String, String> map);

	public void imginsert(Map<String, String> fMap);

	

	public Member orginC_id(String m_id);

	public int comfirm(Member m);

	public Member getSosok(String m_id);

	public int comfirm1(String m_id);

	public boolean adinsert(Question adadd);

	public boolean opinsert(Map<String, String> opMap);

	public int opcount(String ad_code);

	public ArrayList<Question> advertiseModifyFrm(String ad_code);



	
	
	
	public boolean admodify(Question adadd);
	
	public boolean opmodify(Map<String, String> opMap);

	public boolean adpfmodify(Map<String, String> map);

	public boolean deletepf(String ad_code);

	public Integer deleteAdphoto(String ad_code);

	




}
