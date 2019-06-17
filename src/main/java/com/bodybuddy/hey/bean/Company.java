package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("company")
@Setter @Getter
public class Company {
	private String c_id;
	private String c_num;
	private String c_bname;
	private String c_bphone;
	
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_phone;
	private String m_birth;
	private String m_addr;
	private String m_exaddr;
	private String m_kind;
}