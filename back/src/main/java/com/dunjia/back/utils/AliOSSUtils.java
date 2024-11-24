package com.dunjia.back.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {

    // Hardcoded values for Access Key ID and Secret
    private static final String ACCESS_KEY_ID = "LTAI5tJSiQGQZuMpxgmRCP8Q";
    private static final String ACCESS_KEY_SECRET = "Vh8AGnBiJmP3JTpVdcBEI4dv90Vvf7";

//    @Value("${aliyun.oss.endpoint}")
    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
//    @Value("${aliyun.oss.bucketName}")
    private String bucketName = "web-dunjia";
//    @Value("${aliyun.oss.region}")
    private String region = "cn-hangzhou";

    private EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();


    public AliOSSUtils() throws ClientException {
    }

    // 本地环境变量获得 Access Key ID 和 Access Key Secret
//    public String upload(MultipartFile file) throws IOException {
//        InputStream inputStream = file.getInputStream();
//
//        String originalFilename = file.getOriginalFilename();
//        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//
//        // 创建OSSClient实例。
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(credentialsProvider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .build();
//
//        ossClient.putObject(bucketName, fileName, inputStream);
//
//        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
//
//        ossClient.shutdown();
//        return url;
//    }


    // 硬编码的方式上传文件
    public String upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // Create OSSClient with hardcoded credentials
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);

        // Use DefaultOSSClient to pass in access key ID and secret
        // 创建 OSSClient 实例并传入硬编码的 AccessKeyId 和 AccessKeySecret
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET)) // 使用硬编码的凭证
                .region(region)
                .build();

        // Upload the file to OSS
        ossClient.putObject(bucketName, fileName, inputStream);

        // Construct the file URL
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // Shutdown the client
        ossClient.shutdown();
        return url;
    }
}





