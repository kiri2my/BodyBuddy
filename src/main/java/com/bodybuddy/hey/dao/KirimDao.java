package com.bodybuddy.hey.dao;

import java.util.List;

import com.bodybuddy.hey.bean.Member;


public interface KirimDao {

	public String getSecurityPwd(String m_id);

	public String getMemberKind(String m_id);

	public Member getNormalInfo(String m_id);

	public Member getTrainerInfo(String m_id);

	public Member getCompanyInfo(String m_id);

	boolean purchSingle(String op_code);

	/*
	 * boolean payInsert(PaymentHistory ph);
	 * 
	 * List<PaymentHistory> getPayList(String ps_adcode);
	 */

}
