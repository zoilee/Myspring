package net.musecom.comunity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.musecom.comunity.model.CustomUserDetails;
import net.musecom.comunity.model.Member;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		String userid = null;
		//���������� �̿��� ����� ���� ��������
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("����" + auth);
		
		if(auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
				
				CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();		
				userid = userDetails.getUsername();
				Member member = userDetails.getMember();
				System.out.println("�������̵�" + userDetails.toString());
				model.addAttribute("member", member);
			
		}
		
		model.addAttribute("userid", userid );
		
		
		return "home";
	}
	
}
