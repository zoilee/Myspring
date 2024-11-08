package net.musecom.comunity.dao;

import java.util.List;

import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

public interface MemberDao {
  List<Member> getAllmem();
  Member getMem(int num);
  void insertMem(Member dto);
  void insertMemRole(MemberRole rdto);
  void updateMem(Member dto);
  void delMem(int num);
}
