package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("member")
@Setter @Getter
public class Member {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_phone;
	private String m_birth;
	private String m_addr;
	private String m_exaddr;
	private String m_kind;
	
	private String t_id;
	private String t_cid;
	private String t_career;
	
	private String c_id;
	private String c_num;
	private String c_bname;
	private String c_bphone;
	 
	private String pf_image;

	private String ps_date;
	private String ps_date1;
	private String ps_date2;
	private String ps_code;
	private String ps_adcode;
	private String ps_opcode;
	private String ps_day;

	private String yn_date;
	private String yn_company;
	private String yn_trainer;
	private String yn_state;
	 
	private String dt_tid;
	private String dt_cid;
	private String dt_date;
	private String dt_status;
	
	private String op_trainer;
	private String op_period;
	private String op_code;
	private String op_adcode;

	private String dn_dacode;
	private String dn_date;
	private String da_code;
	private String da_pscode;
	private String da_status;
	
	private String ad_title;
	private String ad_category;
	private String ad_code;
	
	private String rownum;
}
