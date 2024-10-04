package net.musecom.comunity.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Member {
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
	  private String userimg;
	  private String oruserimg;
	  private String userprofile;
	  private Timestamp create_at;
	  private Timestamp edit_at;
	  private String userip;
	  private int grade;
}
