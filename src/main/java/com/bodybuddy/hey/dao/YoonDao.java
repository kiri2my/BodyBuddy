package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Counsel;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Review;


public interface YoonDao {

	List<Map<String, String>> mainList();
	
	List<Map<String, String>> mainListCate(String cate);

	List<Map<String, String>> dibsN(String d_id);

	List<Map<String, String>> mainListMap(Map<String, String> local);

	List<Map<String, String>> getproListN(String m_id);

	List<Map<String, String>> getnormalListN(String m_id);

	List<Map<String, String>> getpayListN(String m_id);

	Member getModifyN(String m_id);

	Member getPhotoModifyN(String m_id);

	List<Map<String, String>> getdibs(String m_id);

	boolean reviewInsert(Review rv);

	boolean reviewOverlap(Review rv);
	
	List<Map<String, String>> getCounsel(String cs_opcode, String cs_mid);

	Counsel getcounselN(Counsel cs);

	List<Map<String, String>> getCounsel(Map<String, String> cs);

	int imgOverlap(String m_id);

	boolean fileInsert(Map<String, String> fMap);

	boolean fileUpdate(Map<String, String> fMap);

	void updateNorMb(Member mb);

	List<Map<String, String>> getDailyList(Map<String, String> ck);
	
	
	Member getCbname(String m_id);

	List<Map<String, String>> getsales(String m_id);

	List<Map<String, String>> getsalesAll(String m_id);

	List<Map<String, String>> getchart(String t_cid);

	List<Map<String, String>> getSalescList(String m_id);

	List<Map<String, String>> getSalesAllcList(String m_id);

	List<Map<String, String>> getchart2(String t_cid);

	List<Map<String, String>> getRvListN(String m_id);

	List<Map<String, String>> getQuestListN(String m_id);

	
}