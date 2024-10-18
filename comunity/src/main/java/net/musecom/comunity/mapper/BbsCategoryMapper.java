package net.musecom.comunity.mapper;

import java.util.List;

import net.musecom.comunity.model.BbsCategory;

public interface BbsCategoryMapper {
	List<BbsCategory> selectCategoryByBbsId(int bbsid); //¸ñ·Ï
	int insertCategory(BbsCategory category);
	int updateCategory(BbsCategory category);
	int deleteCategory(int id);
	int selectMaxCategorynum(int bbsId);
	
}
