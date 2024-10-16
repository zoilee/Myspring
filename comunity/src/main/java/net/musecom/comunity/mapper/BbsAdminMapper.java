package net.musecom.comunity.mapper;

import java.util.List;

import net.musecom.comunity.model.BbsAdmin;

public interface BbsAdminMapper {
	int insertBbsAdmin(BbsAdmin dto);
	int updateBbsAdmin(BbsAdmin dto);
	int deleteBbsAdmin(int id);
	BbsAdmin selectByid(int id);
	List<BbsAdmin> selectList();
}
