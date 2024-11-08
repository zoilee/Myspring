package net.musecom.comunity.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.musecom.comunity.dao.MemberDao;
import net.musecom.comunity.model.FileDto;
import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;
import net.musecom.comunity.service.ClientIpAddress;
import net.musecom.comunity.service.FileUploadService;
import net.musecom.comunity.service.InstargramParser;

@Controller
public class MainController {

	@Autowired
	private ClientIpAddress clientIpAddress;
	
	@Autowired
	private FileUploadService fileUpload;
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private FileDto fileDto = new FileDto();
	
	@GetMapping("/register")
	public String Register(Model model) {
		return "register";
	}
		
	@PostMapping("/register")
	public String RegisterForm(
			@RequestParam("userid") String userid,
			@RequestParam("userpass") String noopuserpass,
			@RequestParam("username") String username,
			@RequestParam("useremail") String useremail,
			@RequestParam("usertel") String usertel,
			@RequestParam(value="zipcode", required = false) Integer zipcode,
			@RequestParam("address") String address,
			@RequestParam("detail_address") String detail_address,
			@RequestParam("extra_address") String extra_address,
			@RequestParam("userprofile") String userprofile,
			@RequestParam("role") String role,
			@RequestParam(value="userimg", required=false) MultipartFile userimg,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes
			) {
		
		if(zipcode == null) {zipcode = 0; } 
		
		Member dto = new Member();
		MemberRole rdto = new MemberRole();

		clientIpAddress.setClientIpAddress(request);
	
		String userpass = passwordEncoder.encode(noopuserpass);
		
		String userip = clientIpAddress.getClientIpAddress();
		dto.setUserid(userid);
		dto.setUserpass(userpass);
		dto.setUsername(username);
		dto.setUseremail(useremail);
		dto.setUsertel(usertel);
		dto.setZipcode(zipcode);
		dto.setAddress(address);
		dto.setDetailAddress(detail_address);
		dto.setExtraAddress(extra_address);
		//dto.setUserimg(userimg);
		dto.setUserprofile(userprofile);
		dto.setUserip(userip);
		
		if(userimg != null && !userimg.isEmpty()) {
			try {
			 
			  fileUpload.setAbsolutePath("members"); 
			  String[] exts = {"jpg", "gif", "png"};
			  fileUpload.setAllowedExt(exts); 
			  long maxSize = 1 * 1024 * 1024; 
			  fileUpload.setMaxSize(maxSize);
			  fileDto = fileUpload.uploadFile(userimg);
			  dto.setOruserimg(fileDto.getOrfilename());
			  dto.setUserimg(fileDto.getNewfilename());
			  
			}catch(Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				return "redirect:/";
			}
		}	
		
		dao.insertMem(dto);
		
		//primary key auto increment 
		int membersid = dto.getId();
		
		rdto.setMembersid(membersid);
		rdto.setUserRole(role);
		
		dao.insertMemRole(rdto);
				
		//redirect 일때 정보 전달 방법
		redirectAttributes.addFlashAttribute("memberok", "ok");
		
		return "redirect:/";
	}
	
//	@GetMapping("/login")
//    public String LoginForm(@RequestParam(value="error", required=false) String error, Model model) {
//		if(error != null) {
//			model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
//		}
//		return "login";
//	}
//	
	
	@GetMapping("/member")
	public String memberIndex(Model model) {
		return "member.index";
	}
	
	 @GetMapping("/insta")
	   public String instagram(@RequestParam("instaid") String instaid, Model model ) {
	      InstargramParser instgramParser = new InstargramParser();
	      model.addAttribute("inst", instgramParser.getStringText(instaid));
	      return "main.insta";
	   }
}
