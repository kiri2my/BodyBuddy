package com.bodybuddy.hey.dao;

import java.util.ArrayList;
import java.util.List;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Sales;

public interface SalesDao {

	ArrayList<Sales> getMainSalesHistory(String id);
	List<Sales> getSalesHistory(String m_id);

}
