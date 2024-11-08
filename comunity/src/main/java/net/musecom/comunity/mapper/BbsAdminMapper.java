package net.musecom.comunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.musecom.comunity.model.BbsAdmin;
import net.musecom.comunity.model.BbsCategory;

@Mapper
public interface BbsAdminMapper {
   int insertBbsAdmin(BbsAdmin dto);
   int updateBbsAdmin(BbsAdmin dto);
   int fileUpdateBbsAdmin(BbsAdmin dto);
   int deleteBbsAdmin(int id);
   BbsAdmin selectById(int id);
   List<BbsAdmin> selectList();
   
   List<BbsCategory> selectCategoryByBbsId(int id); 
   
}
