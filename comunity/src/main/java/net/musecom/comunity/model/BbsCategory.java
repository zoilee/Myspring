package net.musecom.comunity.model;

import java.util.List;

import lombok.Data;

@Data
public class BbsCategory {
	private int id; //기본값
	private int bbsid; //bbs의 아이디 (외래키)
	private String categorytext; //카테고리 내용
	private int categorynum; //카테고리 순서
	}
