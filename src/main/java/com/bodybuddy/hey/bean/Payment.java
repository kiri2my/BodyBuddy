package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("payment")
@Setter @Getter
public class Payment {
	 private String ps_code;
	 private String ps_adcode;
	 private String ps_opcode;
	 private String ps_mid;
	 private String ps_date;
	 private int ps_price;
}
