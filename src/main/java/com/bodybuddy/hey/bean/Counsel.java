package com.bodybuddy.hey.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Alias("counsel")
@Setter @Getter

public class Counsel {
	 private String cs_opcode;
	 private String cs_mid;
	 private Date cs_date;
	 private String cs_content;
	 private String cs_image;

}
