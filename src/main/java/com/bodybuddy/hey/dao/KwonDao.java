package com.bodybuddy.hey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Company;
import com.bodybuddy.hey.bean.DailyCheck;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Question;

public interface KwonDao {

	public List<Member> getMemberList(String id);

	public List<Member> getMemberSearch(Member m);

	public List<Member> getTrainerList(String id);

	public List<Member> getTrainerSearch(Member m);

	public ArrayList<HashMap<String, String>> getNormalMemberList(String code);

	public List<Member> getProgramMemberList(String id);

	public List<Member> getTrainerMemberList(String id);

	public List<Member> getworkingAttitude(Member m);

	public List<Member> getProgramMember(String id);

	public boolean programCheckInsert(String code);

	public String programCheckSelect(String code);

	public boolean programcheckInsert2(String da_code);

	public List<DailyCheck> getAttended(String code);

	public Company getInfomodifyC(String id);

	public void infoModifyPwUpdate(Company com);

	public void infoModifyMemberUpdate(Company com);

	public void infoModifyCompanyUpdate(Company com);

	public void fileInsert(Map<String, String> fMap);

	public void fileUpdate(Map<String, String> fMap);

	public boolean trainerDailyCheckInsert(Member m);

	public boolean trainerDailyCheckUpdate(Member m);

	public String normalDailyCheckSelect(String code);

	public void normalDailyCheckInsert(DailyCheck dc);

	public DailyCheck normalDailyCheckSelect1(String code);

	public void normalDailyCheckInsert1(DailyCheck dc);

	public void normalDailyCheckInsert2(DailyCheck dc);

	public void normalDailyCheckUpdate(DailyCheck dc);

	public DailyCheck normalDailyCheckSelect2(String code);

	public int normalDailyCheckSelect3(DailyCheck dc);

	public DailyCheck programDailyCheckSelect(String code);

	public DailyCheck categoryCheck(String code);

	public void programDailyCheckInsert(DailyCheck dc);

	public Member getinfoModifyImage(String id);

	public int profilePhotoSelect(String m_id);

	public ArrayList<HashMap<String, String>> getTrainerJoinList(String id);

	public void trainerJoinUpdate(Map<String, String> map);

	public void trainerJoinInsert(Map<String, String> map);

	public void trainerDiscon(Map<String, String> map);

	public void trainerDisconDelete(Map<String, String> map);

	public String getNormalMemberListCode(String id);

	public void changeState(Map<String, String> map);

	public ArrayList<Member> getMainMemberList(String id);

	public ArrayList<Question> getMainAdvertise(String id);

	public int trainerRequest(String id);

	public ArrayList<HashMap<String, String>> getTrainerSales(Map<String, String> map);

	public ArrayList<HashMap<String, String>> getTrainerSalesSelect(Map<String, String> map);


}
