package net.musecom.comunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

@Mapper
public interface MemberMapper {
   Member getMemberUserId(String userid); //아이디 가져오기
   void setInsertMember(Member member); 
   void setInsertRole(MemberRole memberRole);
   List<MemberRole> getMemberRole(int membersid); //아이디 번호로 role 가져오기
}
