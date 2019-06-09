package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Payment;


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
	


	/*
	 * boolean payInsert(PaymentHistory ph);
	 * 
	 * List<PaymentHistory> getPayList(String ps_adcode);
	 */

}
