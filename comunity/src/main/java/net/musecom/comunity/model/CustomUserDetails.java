package net.musecom.comunity.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

	private Member member;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public CustomUserDetails(Member member, Collection<MemberRole> roles) {
		this.member = member;
		this.authorities = roles.stream()
								.map(role -> new SimpleGrantedAuthority(role.getUserRole()))
								.collect(Collectors.toList());
		System.out.println(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getUserpass();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getUserid();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [member=" + member + ", authorities=" + authorities + "]";
	}

	
}
