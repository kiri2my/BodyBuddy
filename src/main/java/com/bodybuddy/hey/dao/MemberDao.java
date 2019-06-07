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

	public int checkId(String s);

	public int checkCnum(String c_num);

	public List<Member> forgetId(Member mb);

}
