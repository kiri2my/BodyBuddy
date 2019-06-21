package com.bodybuddy.hey.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("alarm")
@Setter @Getter
public class Alarm {
	String al_code;
	String al_mSId;
	String al_mRId;
	String al_kind;
	String al_status;
	LocalDateTime al_date;
	

}
