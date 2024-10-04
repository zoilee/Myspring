package net.musecom.comunity.mapper;

import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

public interface MemberMapper {
   Member getMemberUserId(String userid);
   void setInsertMember(Member member);
   void setInsertRole(MemberRole memberRole);
}
