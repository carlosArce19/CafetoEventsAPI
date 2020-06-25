package co.cafeto.EventManager.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAWSS3Service {

	String uploadFile(MultipartFile multipartFile)  throws Exception;
}
