package com.bodybuddy.hey.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Sales;
import com.bodybuddy.hey.dao.SalesDao;

@Service
public class SalesService {
	@Autowired
	private SalesDao sDao;

	// private HttpSession session; // request는 권장하지 않음

	Sales sales;
	
	ModelAndView mav;

	public ModelAndView getSalesHistory(Member mb) {
		mav = new ModelAndView();
		String view = null;
		String m_id=mb.getM_id();
		List<Sales> sList = null;
		System.out.println("getSalesHistory mDao in");
		sList = sDao.getSalesHistory(m_id);
		// System.out.println("mList = " + sList.get(0).getM_name());
		System.out.println("sList size = " + sList.size());

		if (0 != sList.size()) {
			System.out.println("getSalesHistory select success");
			view = "manage/salesHistory";
			mav.setViewName(view);
			mav.addObject("sList", sList);
		} else {
			System.out.println("getSalesHistory list select error");
			view = "manage/company/company";
			mav.setViewName(view);
		}

		return mav;
	}

}
