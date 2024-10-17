package net.musecom.comunity.mapper;

import java.util.List;

import net.musecom.comunity.model.BbsCategory;

public interface BbsCategoryMapper {
	List<BbsCategory> selectCategoryByBbsId(int bbsid); //���
	int insertBbsCategory(BbsCategory category);
	int updateBbsCategory(BbsCategory category);
	int deleteCategory(int id);
	
}
