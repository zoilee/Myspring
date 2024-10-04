package net.musecom.comunity.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService implements FileUpload {
	
	//Ȯ���ڸ��, ��������ũ�� �ʵ� ����
	private String[] allowedExt;
	private long maxSize;
	
	//������ �ʵ�
	private String path;
	private String orPath;
	
	//��� ���ؽ�Ʈ �󿡼� ���ױ�
	@Autowired
	private ServletContext sc;

	@Override
	public String[] getAllowedExt() {
		return allowedExt;
	}

	@Override
	public void setAllowedExt(String[] allowedExt) {
        this.allowedExt = allowedExt;
	}

	@Override
	public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
	}

	@Override
	public long getMaxSize() {
		return maxSize;
	}

	@Override
	public void setAbsolutePath(String path) {
		this.path =sc.getRealPath("/res/upload/") + path + "/";
		this.orPath = path;
	}

	@Override
	public String getAbsolutePath() {
		return path;
	}

	
	//���� Ȯ���� �̸� �������� �Լ�
	@Override
	public String getFileExt(String filename) {
		if(filename == null || filename.isEmpty()) {
			return "";
		}
		int dotIndex = filename.lastIndexOf(".");
		return (dotIndex != -1 && dotIndex < filename.length() - 1) ? filename.substring(dotIndex + 1) :"";
	}
	
	//���Ͼ��ε� ó��
	public String[] uploadFile(MultipartFile file) throws IOException {
		
		String fileExt = getFileExt(file.getOriginalFilename());
		
		if(file == null || file.isEmpty()) {
			throw new IllegalArgumentException("������ �����ϴ�.");
		}
		
		//����ũ�� üũ
		if(maxSize > 0 && file.getSize() > maxSize) {
			throw new IllegalArgumentException("���� ũ��� " + maxSize + "���� �۾ƾ� �մϴ�.");
		}
		
		if(allowedExt != null && allowedExt.length > 0) {
			boolean isFileOk = false;
			for(String ext : allowedExt) {
				if(fileExt.equals(ext)) {
					isFileOk = true;
					break;
				}
			}
			
			if(!isFileOk) {
				throw new IllegalArgumentException("������ �ʴ� Ȯ���� �Դϴ�.");
			}
		}
		
		//������ + �����̸�
		String orFilename = file.getOriginalFilename();
		String newFilename = System.currentTimeMillis() + "-"+ orPath +"." + fileExt;
		File dest = new File(path, newFilename);
		
		//��������
		file.transferTo(dest);
		String[] filesname = {orFilename, newFilename};
		
		return filesname;
	}

}
