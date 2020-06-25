package co.cafeto.EventManager.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import co.cafeto.EventManager.exception.AWSFailUploadException;
import co.cafeto.EventManager.exception.AWSFileNotValidException;

@Service
public class AWSS3Service implements IAWSS3Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3Service.class);
	private static final List<String> CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg");
	
	
	@Autowired
	@Qualifier("dafault")
	private AmazonS3 amazonS3;
	
	@Value("${app.awsServices.bucketName}")
	private String bucketName;
	
	@Value("${app.awsServices.file.max-size}")
	private Long maxSizeFile;

	@Override
	public String uploadFile(final MultipartFile multipartFile) throws AWSFileNotValidException, AWSFileNotValidException {
		LOGGER.info("File upload in progress.");
		try {
			String fileType = multipartFile.getContentType();
			long size = multipartFile.getSize();
			LOGGER.info("File Type: " + fileType+" size: "+size);
			
			if(CONTENT_TYPES.contains(fileType) && size <= maxSizeFile) {
				final File file = convertMultiPartFileToFile(multipartFile);
				String fileName = uploadFileToS3Bucket(bucketName, file);
				LOGGER.info("File upload is completed. fileName: "+fileName);
				file.delete();	// To remove the file locally created in the project folder.
				return fileName;
			} else {
				String errorMessage = CONTENT_TYPES.contains(fileType)?"Error: Max size allowed of 5MB.":"Error: Invalid format only .PNG and .JPG allowed.";
				System.out.println("Error msg: " +errorMessage);
				throw new AWSFileNotValidException(errorMessage);
			}
			
			
		} catch (final AmazonServiceException ex) {
			LOGGER.info("File upload is failed.");
			LOGGER.error("Error= {} while uploading file.", ex.getMessage());
			throw new AWSFailUploadException();
		}
	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
			throw new AWSFailUploadException();
		}
		return file;
	}

	private String uploadFileToS3Bucket(final String bucketName, final File file) {
		
		int random_int = (int)(Math.random() * (1000 - 0 + 1) + 0);
		final String uniqueFileName = LocalDateTime.now() + "_"+random_int+"_" + file.getName();
		LOGGER.info("Uploading file with name= " + uniqueFileName);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
		amazonS3.putObject(putObjectRequest);
		return uniqueFileName;
	}

}
