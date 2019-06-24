package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("question")
@Setter @Getter
public class Question {
	private String ad_code;
	private String ad_name;
	private String ad_title;
	private String ad_content;
	private String ad_kind;
	private String ad_category;
	private String ad_status;
	private String ad_date;	
	
	private String qa_adcode;	
	private String qa_writer;	
	private String qa_wcontent;	
	private String qa_wdate;	
	private String qa_acontent;	
	private String qa_adate;
	private String qa_answer;
	private String qa_num;
	private String qa_true;

	private String op_adcode; 
	private String op_code;
	private String op_trainer;
	private String op_content;
	private String op_price;  
	private String op_category;
	private String op_times;
	private String op_period;
	private String op_period1;
	private String op_period2;
	private String op_clock;
	private String op_clock1;
	private String op_clock2;
	private String op_day;
	private String op_personnel;
	
	private String xxx;
	private int checkNum;
	private String ap_image;

	private String e_name;
	private int e_price;
	
		
	}

