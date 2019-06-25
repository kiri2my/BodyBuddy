package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("yesorno")
@Setter @Getter
public class YesOrNo {
	private String yn_trainer;
	private String yn_company;
	private String yn_state;
	private String yn_date;

	private String m_name;
	private String t_id;
	
	
}
