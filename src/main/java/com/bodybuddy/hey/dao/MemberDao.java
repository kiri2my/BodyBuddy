package com.bodybuddy.hey.dao;

import java.util.ArrayList;
import java.util.List;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;

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

	public void profileComplete(String id);

	public boolean acceptrequestInsert(Member m);

	public void DeleteRealId(String m_id);

	public Question qaNum(String qa_num);

	public boolean questionReply(String qa_acontent, String qa_num);

	public Question qNaCheck(String qa_num);


}
