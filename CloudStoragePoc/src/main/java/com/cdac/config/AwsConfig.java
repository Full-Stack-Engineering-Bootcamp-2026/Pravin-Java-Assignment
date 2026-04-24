package com.cdac.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


@Configuration
public class AwsConfig {

     @Value("${backblaze.accessKeyId}")
    private String accessKeyId;

    @Value("${backblaze.secretKey}")
    private String secretKey;

    @Value("${backblaze.endpoint}")
    private String endpoint;

    @Value("${backblaze.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
         AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretKey);

        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))  
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}