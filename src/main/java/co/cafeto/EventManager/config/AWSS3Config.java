package co.cafeto.EventManager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSS3Config {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKeyId;
    
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretAccessKey;
    
    @Value("${cloud.aws.region.static}")
    private String region;
 
    @Bean("dafault")
    public AmazonS3 getAmazonS3Cient() {
    	System.out.println("Enter to config of the AWS S3");
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        // Get AmazonS3 client and return the s3Client object.
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
