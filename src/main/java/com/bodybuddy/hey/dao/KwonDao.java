package com.bodybuddy.hey.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Company;
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

	public List<Member> getworkingAttitude(Member m);

	public List<Member> getProgramMember(String id);

	public boolean programCheckInsert(String code);

	public String programCheckSelect(String code);

	public boolean programcheckInsert2(String da_code);

	public List<Member> getAttended(String code);

	public Company getInfomodifyC(String id);

	public void infoModifyPwUpdate(Company com);

	public void infoModifyMemberUpdate(Company com);

	public void infoModifyCompanyUpdate(Company com);

	public void fileInsert(Map<String, String> fMap);

	public void fileUpdate(Map<String, String> fMap);

	public boolean trainerDailyCheckInsert(Member m);

	public boolean trainerDailyCheckUpdate(Member m);

	public String normalDailyCheckSelect(String code);

	public void normalDailyCheckInsert(String da_code);

	public HashMap<String, String> normalDailyCheckSelect1(String code);

	public void normalDailyCheckInsert1(HashMap<String, String> map);

	public void normalDailyCheckInsert2(HashMap<String, String> map);

	public void normalDailyCheckUpdate(HashMap<String, String> map);

}
