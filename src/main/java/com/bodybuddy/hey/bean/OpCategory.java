package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("opCategory")
@Setter @Getter
public class OpCategory {
	private String op_adcode; 
	private String op_code;
	private String op_trainer;
	private String op_name;
	private int op_price;  
	private String op_category;
	
	private String m_name;
	private String m_addr;
	
	 
 
	
}
