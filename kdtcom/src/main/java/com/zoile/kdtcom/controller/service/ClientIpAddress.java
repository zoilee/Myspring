package com.zoile.kdtcom.controller.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class ClientIpAddress {
	
	private String ip;
	
	public void getClientIpAddress(HttpServletRequest req) {
		String ip = req.getHeader("x-Forwarded-For");
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-client-IP");
		}
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-client-IP");
		}
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP-client-IP");
		}
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP_X_client-IP");
		}
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(ip == null || ip.isEmpty()||"unkown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		this.ip = ip;
	}
	public String getClientIpAddress() {
		return this.ip;
	}
}
