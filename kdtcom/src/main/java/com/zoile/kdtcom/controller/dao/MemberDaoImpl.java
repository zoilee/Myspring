package com.zoile.kdtcom.controller.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zoile.kdtcom.model.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private MemberMapper mem;
	
	
	@Override
	public List<MemberDto> getAllMembers() {
		
		return mem.findList();
	}

	@Override
	public MemberDto getMem(int num) {
		
		return mem.findById(num);
	}

	@Override
	public void insertMem(MemberDto dto) {
		mem.insertMember(dto);

	}

	@Override
	public void updateMem(MemberDto dto) {
		mem.updateMember(dto);

	}

	@Override
	public void delMem(int num) {
		mem.deleteMember(num);

	}

}
