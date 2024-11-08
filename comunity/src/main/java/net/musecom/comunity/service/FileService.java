package net.musecom.comunity.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.musecom.comunity.mapper.FileMapper;
import net.musecom.comunity.model.FileDto;

@Service
public class FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	public FileDto uploadFile(
			MultipartFile file, 
			String path, 
			String[] exts,
			long maxSize) throws IOException {
	   fileUploadService.setAbsolutePath(path);
	   fileUploadService.setAllowedExt(exts);
	   fileUploadService.setMaxSize(maxSize);
	   FileDto fileDto = fileUploadService.uploadFile(file);
	   
	   //db 저장
	   fileMapper.insertFile(fileDto);
	   
	   return fileDto;
	}
	
    public List<String> selectFileWithBbsIdZero() {
    	return fileMapper.selectTrashFile();
    }
    
    public void deleteFileWithBbsIdZero() {
    	fileMapper.deleteTrashFile();
    }
	
    public List<FileDto> getFilesByBbsId(long bbsid) {
    	return fileMapper.selectFileByBbsId(bbsid);
    }
    
}
