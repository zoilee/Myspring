package net.musecom.comunity.model;

import lombok.Data;

@Data
public class MemberRole {
	private int id;
	private int membersid;
	private String user_role;
}
