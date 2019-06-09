package com.bodybuddy.hey.dao;

import java.util.List;
import java.util.Map;

import com.bodybuddy.hey.bean.OpCategory;


public interface YoonDao {

	List<Map<String, String>> mainList();

	List<OpCategory> opCateListAll();

	List<Map<String, String>> dibsN(String d_id);

	
}
