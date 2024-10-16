package net.musecom.comunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musecom.comunity.mapper.BbsAdminMapper;
import net.musecom.comunity.mapper.BbsCategoryMapper;
import net.musecom.comunity.model.BbsAdmin;
import net.musecom.comunity.service.BbsAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BbsCategoryMapper categoryMapper;
	
	@Autowired
	private BbsAdminMapper bbsAdminMapper;
	
	@Autowired
	private BbsAdminService bbsAdminService;
	
	@GetMapping("")
	public String adminList(Model model) {
		
		List<BbsAdmin> bbsAdminList = bbsAdminMapper.selectList();
		String script = "";
		for(BbsAdmin admin : bbsAdminList) {
			script += "$('#lgrade"+admin.getId()+"').val('"+admin.getLgrade()+"').prop('selected',true);";
			script += "$('#rgrade"+admin.getId()+"').val('"+admin.getRgrade()+"').prop('selected',true);";
			script += "$('#fgrade"+admin.getId()+"').val('"+admin.getFgrade()+"').prop('selected',true);";
			script += "$('#regrade"+admin.getId()+"').val('"+admin.getRegrade()+"').prop('selected',true);";
			script += "$('#comgrade"+admin.getId()+"').val('"+admin.getComgrade()+"').prop('selected',true);";
			script += "$('#skin"+admin.getId()+"').val('"+admin.getSkin()+"').prop('selected',true);";
			script += "$('#category"+admin.getId()+"').val('"+admin.getCategory()+"').prop('selected',true);";			
		}
		model.addAttribute("script", script);
		model.addAttribute("lists", bbsAdminMapper.selectList());
		return "admin.index";
	}
	
	@GetMapping("/write")
	public String noticeWrite(Model model) {
		model.addAttribute("categories", categoryMapper.selectCategoryByBbsId(1));
		return "admin.write";
	}
	
	@PostMapping("/editBbsAdmin")
	@ResponseBody
	public int editBbsAdmin(
			@RequestParam("id") int id ,
			@RequestParam("bbstitle") String bbstitle,
			@RequestParam("skin") String skin,
			@RequestParam("category") byte category,
			@RequestParam("listcount") byte listcount,
			@RequestParam("pagecount") byte pagecount,
			@RequestParam("lgrade") byte lgrade,
			@RequestParam("rgrade") byte rgrade,
			@RequestParam("fgrade") byte fgrade,
			@RequestParam("regrade") byte regarde,
			@RequestParam("comgrade") byte comgrade)
	{
		
		BbsAdmin bbsAdmin = new BbsAdmin();
		bbsAdmin.setId(id);
		bbsAdmin.setBbstitle(bbstitle);
		bbsAdmin.setSkin(skin);
		bbsAdmin.setCategory(category);
		bbsAdmin.setListcount(listcount);
		bbsAdmin.setPagecount(pagecount);
		bbsAdmin.setLgrade(lgrade);
		bbsAdmin.setRgrade(rgrade);
		bbsAdmin.setFgrade(fgrade);
		bbsAdmin.setRegrade(regarde);
		bbsAdmin.setComgrade(comgrade);
		
		//¼­ºñ½º
		
		int result  = bbsAdminService.editBbsAdmin(bbsAdmin);

		return result;
	}
	
}
