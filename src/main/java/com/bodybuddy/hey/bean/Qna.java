package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("qna")
@Setter @Getter
public class Qna {
	 private String qa_num;
	 private String qa_adcode;
	 private String qa_writer;
	 private String qa_wContent;
	 private String qa_wdate;
	 private String qa_answer;
	 private String qa_aContent;
	 private String qa_adate;
	 private String qa_true;
	
}
