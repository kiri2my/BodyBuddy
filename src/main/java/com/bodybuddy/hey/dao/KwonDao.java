package com.bodybuddy.hey.dao;

import java.util.List;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;

public interface KwonDao {

	public List<Member> getMemberList(String id);

	public List<Member> getMemberSearch(Member m);

	public List<Member> getTrainerList(String id);

	public List<Member> getTrainerSearch(Member m);

	public List<Member> getNormalMemberList(String id);

	public List<Member> getProgramMemberList(String id);

	public List<Member> getTrainerMemberList(String id);

	public boolean dailyCheckInsert(Member m);

	public boolean dailyCheckUpdate(Member m);

	public List<Member> getworkingAttitude(Member m);

	public List<Member> getProgramMember(String id);

	public boolean programCheckInsert(String code);

	public String programCheckSelect(String code);

	public boolean programcheckInsert2(String da_code);

	public List<Member> getAttended(String code);

}
