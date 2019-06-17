package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("sales")
@Setter @Getter
public class Sales {
	private String ps_code;
	private String ps_adcode;
	private String ps_mid;
	private String ps_date;
	private String ps_price;
	private String ad_title;
	private String m_name;
	private String m_id;
	

}
