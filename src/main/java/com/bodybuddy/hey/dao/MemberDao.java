package com.bodybuddy.hey.dao;

import java.util.List;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;

public interface MemberDao {

	public boolean normalMemberJoin(Member mb);

	public List<Member> getMemberList(String id);

	public List<Member> getMemberSearch(Member m);

	public List<Member> getTrainerList(String id);

	public List<Member> getTrainerSearch(Member m);

	public boolean trainerMemberJoin(Member mb);

	public boolean companyMemberJoin(Member mb);

	public int checkId(String s);

	public int checkCompanyNum(String c_num);

	public List<Member> forgetId(Member mb);

	public List<Member> forgetPw(Member mb);

	public void temporaryPw(Member mb);
	
	public Member getProfileList(String id);

	public List<Member> getTfindC(Member m);


	public List<Question> getQuestionList(String id);

	public boolean cancel(String id);


	public boolean acceptrequestInsert(String id, String name);



	public List<Member> getNormalMemberList(String id);

	public List<Member> getProgramMemberList(String id);

	public boolean acceptrequestupdate(Member m);


	public List<Member> getTrainerMemberList(String id);

	public boolean dailyCheckInsert(Member m);

	public boolean dailyCheckUpdate(Member m);

	public List<Member> getworkingAttitude(Member m);

	public List<Member> getProgramMember(String id);

	public boolean programCheckInsert(String code);

	public String programCheckSelect(String code);

	public boolean programcheckInsert2(String da_code);


	public List<Member> getAttended(String code);

	public boolean memberDeleteReal(String m_id);

	public void DeleteRealId(String m_id);

	public String deleteRealIdCheck(String m_id);




}
