package net.musecom.comunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.musecom.comunity.mapper.MemberMapper;
import net.musecom.comunity.model.CustomUserDetails;
import net.musecom.comunity.model.Member;
import net.musecom.comunity.model.MemberRole;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberMapper.getMemberUserId(username);
		if(member == null) {
			throw new UsernameNotFoundException("ȸ�� ������ �����ϴ�.");
		}
		List<MemberRole> roles = memberMapper.getMemberRole(member.getId());
		
		
		return new CustomUserDetails(member, roles);
	}

}
