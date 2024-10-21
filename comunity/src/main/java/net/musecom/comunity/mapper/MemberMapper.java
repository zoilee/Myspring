package net.musecom.comunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

@Mapper
public interface MemberMapper {
   Member getMemberUserId(String userid); //���̵� ��������
   void setInsertMember(Member member); 
   void setInsertRole(MemberRole memberRole);
   List<MemberRole> getMemberRole(int membersid); //���̵� ��ȣ�� role ��������
}
