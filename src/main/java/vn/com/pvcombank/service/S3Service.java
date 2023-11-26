package vn.com.pvcombank.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Tag;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.com.pvcombank.web.rest.BLUploadFileResource;

@Service
public class S3Service {
    // private final Logger log = LoggerFactory.getLogger(BLUploadFileResource.class);

    // private String S3_PUBLIC_URL = "";

    // @Value("${aws.s3.dev.accessKey}")
    // private String S3_ACCESS_KEY;

    // @Value("${aws.s3.dev.privateKey}")
    // private String S3_PRIVATE_KEY;

    // @Value("${aws.s3.dev.bucket}")
    // private String S3_BUCKET;

    // Regions CLIENT_REGION = Regions.AP_SOUTHEAST_1;

    // AmazonS3 amazonS3;

    // public void init() {
    // 	AWSCredentials credentials = new BasicAWSCredentials(S3_ACCESS_KEY, S3_PRIVATE_KEY);
    // 	this.amazonS3 = AmazonS3ClientBuilder.standard()
    // 			.withRegion(CLIENT_REGION)
    // 			.withCredentials(new AWSStaticCredentialsProvider(credentials))
    // 			.build();
    // }

    // public String putObject(String fileName, InputStream input, ObjectMetadata metadata, List<Tag> tags)
    // 		throws Exception {
    // 	if (amazonS3.doesObjectExist(S3_BUCKET, fileName)) {
    // 		throw new Exception(
    // 				"Tên file đã tồn tại trên hệ thống, vui lòng điền thêm thông tin khách hàng vào tên file để tránh trùng lặp");
    // 	}

    // 	log.info("-- upload 1 S3: {}", S3_BUCKET);
    // 	log.info("-- upload 2 S3: {}", fileName);

    // 	PutObjectRequest putRequest = new PutObjectRequest(S3_BUCKET, fileName, input, metadata)
    // 			.withCannedAcl(CannedAccessControlList.Private);

    // 	putRequest.setTagging(new ObjectTagging(tags));

    // 	log.info("-- upload S3: {}", putRequest);

    // 	amazonS3.putObject(putRequest);

    // 	return S3_PUBLIC_URL + fileName;
    // }

    // public InputStream getObject(String filePath) throws Exception {
    // 	String fileName = StringUtils.replace(filePath, S3_PUBLIC_URL, "");
    // 	return amazonS3.getObject(new GetObjectRequest(S3_BUCKET, fileName)).getObjectContent();
    // }

    // public void deleteObject(String objectPath) throws Exception {
    // 	String objectName = StringUtils.replace(objectPath, S3_PUBLIC_URL, "");
    // 	amazonS3.deleteObject(S3_BUCKET, objectName);
    // }

    // public URL getPreSignedLink(String objectPath) throws Exception {
    // 	java.util.Date expiration = new java.util.Date();
    // 	long expTimeMillis = Instant.now().toEpochMilli();
    // 	expTimeMillis += 1000 * 5 * 60;
    // 	expiration.setTime(expTimeMillis);
    // 	GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET, objectPath)
    // 			.withMethod(HttpMethod.GET)
    // 			.withExpiration(expiration);
    // 	URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    // 	return url;
    // }

}
