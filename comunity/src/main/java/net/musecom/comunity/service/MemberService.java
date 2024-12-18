package net.musecom.comunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.musecom.comunity.mapper.MemberMapper;
import net.musecom.comunity.model.CustomUserDetails;
import net.musecom.comunity.model.Member;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	 public Member getAuthenticatedMember() {
		//인증정보를 이용한 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		System.out.println("권한" + auth);
		
		if(auth != null && auth.getPrincipal() instanceof CustomUserDetails) {	
           CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
           return userDetails.getMember();
        }
		return null;
	 }
	 
	 
	 public Member findByUserid(String userid) {
		 return memberMapper.getMemberUserId(userid);
	 }
}

