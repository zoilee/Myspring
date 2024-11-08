package net.musecom.comunity.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.musecom.comunity.model.Bbs;
import net.musecom.comunity.model.BbsAdmin;
import net.musecom.comunity.model.BbsCategory;
import net.musecom.comunity.model.FileDto;
import net.musecom.comunity.model.Member;
import net.musecom.comunity.service.BbsAdminService;
import net.musecom.comunity.service.BbsService;
import net.musecom.comunity.service.ContentsControll;
import net.musecom.comunity.service.FileService;
import net.musecom.comunity.service.MemberService;
import net.musecom.comunity.util.Paging;

@Controller
@RequestMapping("/bbs")
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	@Autowired
	private BbsAdminService adminService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private ServletContext sc;

	@Autowired
	private ContentsControll contentsControll;  //html 태그 정리를 위한 클래스
	
	
	/****************************************************************************
	 * list
	 * @param bbsid
	 * @param page
	 * @param searchKey
	 * @param searchVal
	 * @param model
	 * @return
	 */
	
	@GetMapping("/list")
	public String List(
		@RequestParam("bbsid") int bbsid, 
		@RequestParam(value="page", defaultValue="1") int page,
		@RequestParam(required=false) String searchKey,
		@RequestParam(required=false) String searchVal,
		Model model) {
		
		BbsAdmin bbsAdminDto = new BbsAdmin();
		bbsAdminDto = adminService.getBbsAdminData(bbsid);
		
		/*** 권한 검증 **/
		if(bbsAdminDto.getLgrade() > 0) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication instanceof AnonymousAuthenticationToken) {
				//익명 사용자이며 게시판이 회원제일 경우
				model.addAttribute("error", "회원제입니다. 로그인하세요.");
				return "redirect: /comunity/";
			}else {
				//인증정보를 이용한 사용자 정보 가져오기
		        Member member = memberService.getAuthenticatedMember();
	            if(member.getGrade() < bbsAdminDto.getLgrade()) {
	            	model.addAttribute("error", "권한이 없습니다.");
	            	model.addAttribute("member", member);
	            	return "redirect: /comunity/";
	            }
			}
		}
		
		//익명 사용자 접근 가능한 경우 처리해 주기
		if(!(SecurityContextHolder.getContext().getAuthentication() instanceof  AnonymousAuthenticationToken)) {
			Member member = memberService.getAuthenticatedMember();
			model.addAttribute("member", member);
		}
		      	
		
		/******************************************************************/
		//쓰레기 파일 삭제
		List<String> fileNames = fileService.selectFileWithBbsIdZero();
		if(fileNames != null && !fileNames.isEmpty()) {
			String delFilePath = sc.getRealPath("/res/upload/") + bbsid + "/";
			System.out.println(delFilePath);
			
			File fileDesk = null;
			for( String fileName : fileNames) {
				System.out.println(fileName);
				fileDesk = new File(delFilePath + fileName);
				
				//파일이 서버에 있으면 삭제
				if(fileDesk.exists() && fileDesk.delete()) {
					System.out.println(fileDesk + "삭제했습니다.");
				}
			}
				//파일 삭제가 완료되면 table의 컬럼 삭제
				fileService.deleteFileWithBbsIdZero();
		}	
		
		
		List<BbsCategory> categories = null;
        if(bbsAdminDto.getCategory() > 0) {
        	categories = adminService.getBbsCategoryById(bbsid);
        }
          
        model.addAttribute("categories", categories);
		model.addAttribute("adminBbs", bbsAdminDto);
		
		String skin = bbsAdminDto.getSkin();
		int listCount = bbsAdminDto.getListcount();
		int pageCount = bbsAdminDto.getPagecount();
		int pg = (page -1) * listCount;
		
		int totalRecord = ((searchKey != null && !searchKey.isEmpty()) && 
		   (searchVal != null && !searchVal.isEmpty())) ?
		      bbsService.getSearchBbsCount(bbsid, searchKey, searchVal)	
	          :bbsService.getBbsCount(bbsid);
		
		Paging paging = new Paging(totalRecord, listCount, page, pageCount);
		
		List<Bbs> bbslist = ((searchKey != null && !searchKey.isEmpty()) && 
				   (searchVal != null && !searchVal.isEmpty()))?
		     bbsService.getSerchBbsList(bbsid, pg, listCount, searchKey, searchVal)
		     :bbsService.getBbsList(bbsid, pg, listCount);
	
		//게시물 번호
		long num = paging.getTotalRecords() - pg;
		
		for(Bbs bbs : bbslist) {
			/*
			 * LocalDateTime dateTime = bbs.getWdate().toLocalDateTime();
			 * bbs.setFormattedDate(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			 */
			Timestamp dateTime = bbs.getWdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			bbs.setFormattedDate(sdf.format(dateTime));
			//파일정보 조회
			List<FileDto> files = fileService.getFilesByBbsId(bbs.getId());
		    List<String> fileExts = new ArrayList<>();
		    List<String> filesName = new ArrayList<>();
		    
		    for(FileDto file: files) {
		    	fileExts.add(file.getExt());
		    	filesName.add(file.getNewfilename());
		    }
		    bbs.setFileExt(fileExts);
		    bbs.setNewfilename(filesName);
		    bbs.setNum(num);
		    
		    String contents = contentsControll.extractParagraphs(bbs.getContent());
		    bbs.setContent(contentsControll.cutParagraph(contents, 30));
		    num--;
		}
		
		model.addAttribute("paging", paging);	
		model.addAttribute("bbslist", bbslist);
		
		if(skin.equals("gallery")) {
		   return "gallery.list";
		}else if(skin.equals("article")) {
		   return "article.list";
		}else if(skin.equals("blog")) {
		   return "blog.list";	 
		}else {
			return "bbs.list";
		}
	}
	
	
	/*****************************************************************************]
	 * write get
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/write")
	public String writeForm(@RequestParam("bbsid") int id, Model model ) {
		
		//인증정보를 이용한 사용자 정보 가져오기
		Member member = memberService.getAuthenticatedMember();
		model.addAttribute("member", member);
		
        BbsAdmin bbsAdminDto = new BbsAdmin();
		bbsAdminDto = adminService.getBbsAdminData(id);
		
		List<BbsCategory> categories = null;
        if(bbsAdminDto.getCategory() > 0) {
        	categories = adminService.getBbsCategoryById(id);
        }
        System.out.println("member" + member);
        
        
        model.addAttribute("categories", categories);
		model.addAttribute("adminBbs", bbsAdminDto);
        
		return "bbs.write";
	}
	
	
	/***************************************************************************
	 * write post
	 * @param bbsid
	 * @param fileIds
	 * @param title
	 * @param content
	 * @param writer
	 * @param password
	 * @param sec
	 * @param userid
	 * @param category
	 * @param admin
	 * @param model
	 * @return
	 */
	@PostMapping("/write")
	public String writeAction(
		@RequestParam("bbsAdminId") int bbsid,	
        @RequestParam(value="fileId[]", required = false) List<Long> fileIds, 
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("writer") String writer,
        @RequestParam("password") String password,
        @RequestParam("sec") byte sec,
        @RequestParam("userid") String userid,
        @RequestParam(name = "category", required = false) String category,
        Model model) {
		System.out.println("게시판 글쓰기 writeAction()");
		try {
	        Bbs bbs = new Bbs();
	        bbs.setTitle(title);
	        bbs.setContent(content);
	        bbs.setBbsid(bbsid);
	        bbs.setWriter(writer);
	        bbs.setPassword(password);
	        bbs.setSec(sec);
	        bbs.setUserid(userid);
	        bbs.setCategory(category);
	        
			bbsService.getBbsInsert(bbs, fileIds);
			
			if(userid.equals("admin")) {
			     return "redirect:/admin/write";
			}else {
				return "redirect:/bbs/list?bbsid="+bbsid;
			}
		}catch(Exception e) {
		    model.addAttribute("error", "글 작성중 오류가 발생했습니다." + e.getMessage());
			if(userid.equals("admin")) {
			     return "redirect:/admin/write";
			}else {
				return "redirect:/bbs/list?bbsid="+bbsid;
			}
		}
	}
	
	/******************************************************************************
	 * upload
	 * @param file
	 * @param bbsid
	 * @return
	 */
	
	@PostMapping("/upload")
	public ResponseEntity<Map<String, Object>> uploadFile(
		@RequestParam("file") MultipartFile file, 
		@RequestParam("bbsid") int bbsid){
        Map<String, Object> result = new HashMap<>();
        
        try {
            BbsAdmin bbsAdmin = new BbsAdmin();
            FileDto fileDto = new FileDto();
            bbsAdmin = adminService.getBbsAdminData(bbsid);
            String path = Integer.toString(bbsid);
            String extFilter = bbsAdmin.getFilechar();
            String[] ext = (extFilter != null && !extFilter.isEmpty()) ?
            		          extFilter.split(",") : null;
            long fileSize = bbsAdmin.getFilesize() * 1024 * 1024;
        	
        	fileDto = fileService.uploadFile(file, path, ext, fileSize);
        	
        	result.put("success", true);
        	result.put("fileId", fileDto.getId());
        	result.put("fileName", fileDto.getNewfilename());
        	result.put("orFileName", fileDto.getOrfilename());
        	result.put("fileSize", fileDto.getFilesize());
        	result.put("fileUrl", "/comunity/res/upload/"+path+"/"+fileDto.getNewfilename());
        	result.put("ext", fileDto.getExt());
        	
        }catch(Exception e) {
        	result.put("success" , false);
        	result.put("fileId", e.getMessage());
        	System.out.println(Arrays.toString(e.getStackTrace()));
        }

		return ResponseEntity.ok(result);
	}


	//이미지 경로 반환하기
	/*
	@GetMapping("/res/upload/{bbsId}/{fname}")
	@ResponseBody
	public ResponseEntity<Resource> getImage(
			@PathVariable("bbsId") int bbsId,
			@PathVariable("fname") String fname){
		try {
			Path imagePath = Paths.get("/comunity/res/upload/"+bbsId+"/"+fname);
			Resource resource = new UrlResource(imagePath.toUri().toURL());
			return ResponseEntity.ok()
				   .contentType(MediaType.IMAGE_JPEG)
				   .body(resource);
		}catch (MalformedURLException e) {
	        e.printStackTrace();
	        return ResponseEntity.notFound().build();
	    }
	}
		
	*/
}
