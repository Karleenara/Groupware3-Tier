package com.ds.gw.bt.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends DeptDto{
	private String user_id;
	private String user_nm;
	private String user_eml_addr;
	private String user_telno;
	private String user_addr;
	private int user_dept_no;
	private String user_aprv_yn;
	private String user_hobby;
	String searchKeyword="";
}
