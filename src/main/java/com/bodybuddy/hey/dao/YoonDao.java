package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;


public interface YoonDao {

	List<Map<String, String>> mainList();

	List<OpCategory> opCateListAll();

	List<Map<String, String>> getproListN(String m_id);

	List<Map<String, String>> getnormalListN(String m_id);

	List<Map<String, String>> getpayListN(String m_id);

	List<Map<String, String>> dibsN(String d_id);


	Member getModifyN(String m_id);

	Member getPhotoModifyN(String m_id);

	
}