package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("dc")
@Setter @Getter
public class DailyCheck {
	private String da_code;
	private String da_pscode;
	private String da_status;
	private String da_opperiod;
	
	private String dn_date;
	private String dn_dacode;
	
	private String ad_category;
	
	private String op_period;
	
	private String ps_date;
	private String ps_date1;
	private String ps_code;
	
	
	private String sd;
	private String date;
	private String status;
}
