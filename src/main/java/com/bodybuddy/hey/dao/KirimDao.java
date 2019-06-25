package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Alarm;
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

	public Map<String, String> getTrainerProfile(String m_id);

	public List<Map<String, String>> getTrainerPOption(String m_id);

	public Map<String, String> getCompanyProfile(String m_id);

	public List<Map<String, String>> getCompanyProfileOption(String m_id);

	public String deleteRealIdCheck(String m_id);

	public boolean insertDaliy(Map<String, String> cs2);

	public Payment selectPscode(Map<String, String> cs);

	public int selectOverlap(Map<String, String> cs);

	public String selectPeriod(String da_opperiod);	
	
	public List<Map<String, String>> detailPsCount(String ad_code);

	public List<Map<String, String>> getTrainerPCategory(String m_id);

	public List<Map<String, String>> getOpPeriodList();

	public int expireOption(String op_code);

	public List<Map<String, String>> getAdExpireList();

	public int expireAd(String ad_code);

	public Map<String, String> personnelCalc(String ps_opcode);

	public boolean detailQaWriteInsert(Qna qna);

	public List<Map<String, String>> adPhotoList(String ad_code);

	public String getAdName(String ad_code);

	public boolean alarmSelfPurch(Map<String, String> m);
	
	public int alarmSendPurch(Map<String, String> m);

	public List<Alarm> alarmReceiveAll(String m_id);

	public boolean alarmConfirm(String al_code);

	public int alarmSendQuestion(Map<String, String> m);

	public String getQuestionId(String qa_num);

	public int alarmSendAnswer(Map<String, String> m);

	public int alarmSendSskReq(Map<String, String> m);



	

	
	

	


	/*
	 * boolean payInsert(PaymentHistory ph);
	 * 
	 * List<PaymentHistory> getPayList(String ps_adcode);
	 */

}
