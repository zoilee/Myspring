package com.zoile.kdtcom.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDto {
	private int id;
	private String userid;
	private String userpass;
	private String username;
	private String useremail;
	private String usertel;
	private int zipcode;
	private String address;
	private String detail_address;
	private String extra_address;
	private String memberscol;
	private String userimg;
	private String userprofile;
	private Timestamp create_at;
	private Timestamp edit_at;
	private String userip;
	private int grade;
	
	
	
	
}

