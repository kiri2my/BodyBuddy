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
	private String op_name;
	private int op_price;  
	private String op_category;
	
<<<<<<< HEAD
	private String m_name;//
	private String m_addr;//
=======
	private String m_name;
	private String m_addr;
	
>>>>>>> b339f098e94f2f370d5239abc4f7519c4264b122
	 
 
	
}
