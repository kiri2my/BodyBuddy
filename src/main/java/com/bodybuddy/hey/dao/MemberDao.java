package com.bodybuddy.hey.dao;

import java.util.List;

import com.bodybuddy.hey.bean.Member;

public interface MemberDao {

	public boolean normalMemberJoin(Member mb);

	public List<Member> getMemberList(String id);

	public List<Member> getMemberSearch(Member m);

	public List<Member> getTrainerList(String id);

	public List<Member> getTrainerSearch(Member m);

	public boolean trainerMemberJoin(Member mb);

	public boolean companyMemberJoin(Member mb);

	public int checkId(String m_id);

	public List<Member> getNormalMemberList(String id);

	public List<Member> getProgramMemberList(String id);

}
