package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Review;


public interface YoonDao {

	List<Map<String, String>> mainList();

	 List<OpCategory> opCateListAll(); 

	List<Map<String, String>> dibsN(String d_id);

<<<<<<< HEAD
	List<Map<String, String>> mainMapList2(Map<String, String> local);

	List<Map<String, String>> mainMapList1(Map<String, String> local);

=======
>>>>>>> b339f098e94f2f370d5239abc4f7519c4264b122
	List<Map<String, String>> getproListN(String m_id);

	List<Map<String, String>> getnormalListN(String m_id);

	List<Map<String, String>> getpayListN(String m_id);

	Member getModifyN(String m_id);

	Member getPhotoModifyN(String m_id);

	List<Map<String, String>> getdibs(String m_id);

	boolean reviewInsert(Review rv);

	boolean reviewOverlap(Review rv);

<<<<<<< HEAD

=======
>>>>>>> b339f098e94f2f370d5239abc4f7519c4264b122
	
}