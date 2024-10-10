package net.musecom.comunity.mapper;

import java.util.List;

import net.musecom.comunity.model.BbsAdmin;

public interface BbsAdminMapper {
	void insertBbsAdmin(BbsAdmin dto);
	void updateBbsAdmin(BbsAdmin dto);
	void deleteBbsAdmin(int id);
	BbsAdmin selectByid(int id);
	List<BbsAdmin> selectList();
}
