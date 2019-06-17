package com.bodybuddy.hey.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.bean.Qna;
import com.bodybuddy.hey.bean.Review;


public interface KirimDao {

	public String getSecurityPwd(String m_id);

	public String getMemberKind(String m_id);

	public Member getNormalInfo(String m_id);

	public Member getTrainerInfo(String m_id);

	public Member getCompanyInfo(String m_id);

	boolean purchSingle(Payment ph);

	public Map<String, String> detailPage(String ad_code);

	public List<OpCategory> opCateList(String ad_code);

	public boolean dibsAdd(Map<String, String> dibs);

	public boolean dibsDelete(Map<String, String> dibs);

	public List<Map<String, String>> detailReview(String ad_code);
	
	public List<Qna> detailQa(String ad_code);

	public HashMap<String, String> getTrainerProfile(String m_id);

	public List<HashMap<String, String>> getTrainerProfileOption(String m_id);

	public HashMap<String, String> getCompanyProfile(String m_id);

	public List<HashMap<String, String>> getCompanyProfileOption(String m_id);

	public String deleteRealIdCheck(String m_id);

	public boolean insertDaliy(Map<String, String> cs2);

	public Payment selectPscode(Map<String, String> cs);

	public int selectOverlap(Map<String, String> cs);

	public String selectPeriod(String da_opperiod);	
	
	


	/*
	 * boolean payInsert(PaymentHistory ph);
	 * 
	 * List<PaymentHistory> getPayList(String ps_adcode);
	 */

}
