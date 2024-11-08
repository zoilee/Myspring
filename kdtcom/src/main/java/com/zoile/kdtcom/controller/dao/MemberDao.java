package com.zoile.kdtcom.controller.dao;

import java.util.List;

import com.zoile.kdtcom.model.MemberDto;

public interface MemberDao {
	List<MemberDto> getAllMembers();
	MemberDto getMem(int num);
	void insertMem(MemberDto dto);
	void updateMem(MemberDto dto);
	void delMem(int num);
	
}
