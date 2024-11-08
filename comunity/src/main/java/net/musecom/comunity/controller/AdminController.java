package net.musecom.comunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musecom.comunity.mapper.BbsAdminMapper;
import net.musecom.comunity.model.BbsAdmin;
import net.musecom.comunity.model.BbsCategory;
import net.musecom.comunity.service.BbsAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	//@Autowired
	//private BbsCategoryMapper_ categoryMapper;
	
	@Autowired
	private BbsAdminMapper bbsAdminMapper;
	
	@Autowired
	private BbsAdminService bbsAdminService;
	
	
	@GetMapping("")
	public String adminList(Model model) {
		System.out.println("admin 시작");

	    List<BbsAdmin> bbsAdminList = bbsAdminService.getAllBbsList();
	    String script = "";
	    for(BbsAdmin admin : bbsAdminList) {
	    	script += "$('#lgrade"+admin.getId()+"').val('"+admin.getLgrade()+"').prop('selected', true);";
	    	script += "$('#rgrade"+admin.getId()+"').val('"+admin.getRgrade()+"').prop('selected', true);";
	    	script += "$('#fgrade"+admin.getId()+"').val('"+admin.getFgrade()+"').prop('selected', true);";
	    	script += "$('#regrade"+admin.getId()+"').val('"+admin.getRegrade()+"').prop('selected', true);";
	    	script += "$('#comgrade"+admin.getId()+"').val('"+admin.getComgrade()+"').prop('selected', true);";
	    	script += "$('#skin"+admin.getId()+"').val('"+admin.getSkin()+"').prop('selected', true);";
	    	script += "$('#category"+admin.getId()+"').val('"+admin.getCategory()+"').prop('selected', true);";
	    
            List<BbsCategory> categoryList = bbsAdminService.getBbsCategoryById(admin.getId());
            admin.setBbsCategory(categoryList.isEmpty() ? null : categoryList);           		
            		
            		
//	        if(admin.getCategory() == 1) {
//	        	List<BbsCategory> categoryList = categoryMapper.selectCategoryByBbsId(admin.getId());
//	        	model.addAttribute("categoryList", categoryList);
//	        }
	    }

        model.addAttribute("script", script);
		model.addAttribute("lists", bbsAdminList);
		return "admin.index";
	}
	
	@PostMapping("/mkbbs")
	@ResponseBody
	public String mkbbsForm(@RequestParam("bbstitle") String bbstitle) {
		BbsAdmin bbsAdmin = new BbsAdmin();
		bbsAdmin.setBbstitle(bbstitle);
		int rs = bbsAdminMapper.insertBbsAdmin(bbsAdmin);
		String res = (rs > 0)? "1" : "0";
		return res;
	}
	
	@GetMapping("/write")
	public String noticeWrite(Model model) {
		
		//System.out.println("list" + categoryMapper.selectCategoryByBbsId(1));
		model.addAttribute("categories", bbsAdminService.getBbsCategoryById(1));
		return "admin.write";
	}
	
	@PostMapping("/edtBbsAdmin")
	@ResponseBody
	public String editBbsAdmin( 		
		@RequestParam("id") int id,
 		@RequestParam("bbstitle") String bbstitle,
 		@RequestParam("skin") String skin,
 		@RequestParam("category") byte category,
 		@RequestParam("listcount") byte listcount,
 		@RequestParam("pagecount") byte pagecount,
 		@RequestParam("lgrade") byte lgrade,
 		@RequestParam("rgrade") byte rgrade,
 		@RequestParam("fgrade") byte fgrade,
 		@RequestParam("regrade") byte regrade,
 		@RequestParam("comgrade") byte comgrade) {

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
		bbsAdmin.setRegrade(regrade);
		bbsAdmin.setComgrade(comgrade);
		
    
		String result = Integer.toString(bbsAdminService.editBbsAdmin(bbsAdmin));

		return result;
	}
	
	//파일수정
	@PostMapping("/editfile")
	@ResponseBody
	public String eidtFile(
		@RequestParam("bbsid") int id,
		@RequestParam("filesize") int filesize,
		@RequestParam("allfilesize") int allfilesize,
		@RequestParam("fwidth") String fwidth,
		@RequestParam("fheight") String fheight,
		@RequestParam("filechar") String filechar) {
		
		BbsAdmin bbsAdmin = new BbsAdmin();
		bbsAdmin.setId(id);
		bbsAdmin.setFilesize(filesize);
		bbsAdmin.setAllfilesize(allfilesize);
		bbsAdmin.setThimgsize(fwidth+"|"+fheight);
		bbsAdmin.setFilechar(filechar);
		
		int result = bbsAdminMapper.fileUpdateBbsAdmin(bbsAdmin);
		String res = result > 0 ? "1" : "0";
		return res;
	}
	
	
	//카테고리 추가 삭제 수정
	@PostMapping("/addCategory")
	@ResponseBody
	public String addCategory(@RequestBody BbsCategory category) {

		int result = bbsAdminService.bbsCategoryInsert(category);
		String res = result > 0 ? "1" : "0";  
		return res;
	}
	
	@PostMapping("/editCategory")
	@ResponseBody
	public String editCategory(@RequestBody List<BbsCategory> categories) {
		int result = 0;
		try {
			for(BbsCategory category : categories) {
				//System.out.println(category);
			    result = bbsAdminService.bbsCategoryUpdate(category);
			}
		}catch(Exception e) {
			result = 0;
		}
		String res = result > 0 ? "1" : "0";  
		return res;

	}
	
	@PostMapping("/delCategory")
	@ResponseBody
	public String deleteCategory(@RequestParam("id") int id) {
		int result = bbsAdminService.bbsCategoryDelete(id);
		String res = result > 0 ? "1" : "0";
		return res;
	}
	

}
