package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("opCategory")
@Setter @Getter
public class OpCategory {
	private String op_adcode; 
	private String op_code;
	private String op_trainer;//
	private String op_content;
	private int op_price;  
	private String op_period;
	
	private String op_times;
	private String op_day;
	private String op_clock;
	private int op_personnel;
	

	private String m_name;//
	private String m_addr;//
	private String m_exaddr;
	
}
