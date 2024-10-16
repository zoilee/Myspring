package net.musecom.comunity.mapper;

import java.util.List;

import net.musecom.comunity.model.BbsCategory;

public interface BbsCategoryMapper {
	List<BbsCategory> selectCategoryByBbsId(int bbsid); //¸ñ·Ï
	void insertBbsCategory(BbsCategory category);
	void updateBbsCategory(BbsCategory category);
	void deleteCategory(int id);
	
}
