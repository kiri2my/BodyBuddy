package com.bodybuddy.hey.bean;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("tprofile")
@Setter @Getter
public class Tprofile {
	private String m_id;
	private String m_name;
	private String m_kind;
	private String t_cid;
	private String t_career;
	private String c_bname;
	private String pf_image;

}
