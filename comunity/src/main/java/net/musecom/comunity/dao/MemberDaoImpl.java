package net.musecom.comunity.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.musecom.comunity.mapper.MemberMapper;
import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private MemberMapper mem;
	
	@Override
	public List<Member> getAllmem() {
		return null;
	}

	@Override
	public Member getMem(int num) {
		return null;
	}

	@Override
	public void insertMem(Member dto) {
		mem.setInsertMember(dto);
	}

	@Override
	public void updateMem(Member dto) {

	}

	@Override
	public void delMem(int num) {

	}

	@Override
	public void insertMemRole(MemberRole rdto) {
		mem.setInsertRole(rdto);
	}

}
