package net.musecom.comunity.service;

import java.util.List;

import net.musecom.comunity.model.Bbs;

public interface BbsService {

	void getBbsInsert(Bbs bbs, List<Long> fileIds); 
	
	int getBbsCount(int bbsid);
	int getSearchBbsCount(int bbsid, String key, String val);
	List<Bbs> getBbsList(int bbsid, int page, int recordsPerPage);
	List<Bbs> getSerchBbsList(int bbsid, int page, int recordsPerPage, String key, String val);
	
	
}
