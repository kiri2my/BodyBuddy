package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Alias("review")
@Setter @Getter

public class Review {
	 private String rv_num;
	 private String rv_pscode;
	 private String rv_name;
	 private String rv_content;
	 private String rv_date;
	 private int rv_stpoint;

}
