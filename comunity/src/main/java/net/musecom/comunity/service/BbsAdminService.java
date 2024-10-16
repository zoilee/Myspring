package net.musecom.comunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.musecom.comunity.mapper.BbsAdminMapper;
import net.musecom.comunity.model.BbsAdmin;

@Service
public class BbsAdminService {
	
	@Autowired
	private BbsAdminMapper bbsAdminMapper;
	
	public int editBbsAdmin(BbsAdmin bbsAdmin) {
		return bbsAdminMapper.updateBbsAdmin(bbsAdmin);
	}
	
	
}
