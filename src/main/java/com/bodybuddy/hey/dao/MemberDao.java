
package com.bodybuddy.hey.dao;
import com.bodybuddy.hey.bean.Member;

public interface MemberDao {





	public boolean normalMemberJoin(Member mb);

	public boolean trainerMemberJoin(Member mb);

	public boolean companyMemberJoin(Member mb);

	public boolean forgetId(Member mb);
	
	public int checkId(String m_id); 







}
