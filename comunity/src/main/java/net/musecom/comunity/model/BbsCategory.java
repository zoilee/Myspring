package net.musecom.comunity.model;

import java.util.List;

import lombok.Data;

@Data
public class BbsCategory {
	private int id; //�⺻��
	private int bbsid; //bbs�� ���̵� (�ܷ�Ű)
	private String categorytext; //ī�װ� ����
	private int categorynum; //ī�װ� ����
	}
