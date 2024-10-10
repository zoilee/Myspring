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
import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;
import net.musecom.comunity.service.ClientIpAddress;
import net.musecom.comunity.service.FileUploadService;

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
		
		if(zipcode == null) {zipcode = 0; } //int�� ����� �� ó��
		
		Member dto = new Member();
		MemberRole rdto = new MemberRole();
		//������ �ּ�
		clientIpAddress.setClientIpAddress(request);
		//��й�ȣ ��ȣȭ
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
		
		//���Ͼ��ε� ó��
		if(userimg != null && !userimg.isEmpty()) {
			try {
			 
			  fileUpload.setAbsolutePath("members");  //���ϰ�μ���
			  String[] exts = {"jpg", "gif", "png"};
			  fileUpload.setAllowedExt(exts); //����ϴ� Ȯ���� ����
			  long maxSize = 1 * 1024 * 1024; //�ִ� 1�ް�
			  fileUpload.setMaxSize(maxSize);
			  String[] fnames = fileUpload.uploadFile(userimg);
			  dto.setOruserimg(fnames[0]);
			  dto.setUserimg(fnames[1]);
			  
			}catch(Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				return "redirect:/";
			}
		}	
		
		dao.insertMem(dto);
		
		//primary key auto increment �� ���
		int membersid= dto.getId();
		
		rdto.setMembersid(membersid);
		rdto.setUserRole(role);
		
		dao.insertMemRole(rdto);
		
		//redirect �ϋ� ���� ���� ���
		redirectAttributes.addFlashAttribute("memberok", "ok");
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(value="error", required=false) String error, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
		return "login";
	}
	

	
	
	@GetMapping("/member")
	public String memberIndex(Model model) {
		return "member.index";
	}
	
	@GetMapping("/admin")
	public String adminIndex(Model model) {
		return "admin.index";
	}
}
