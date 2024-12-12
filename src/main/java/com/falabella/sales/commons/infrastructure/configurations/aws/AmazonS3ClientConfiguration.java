package com.falabella.sales.commons.infrastructure.configurations.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonS3ClientConfiguration {
    private final AmazonWebServicesProperties amazonWebServicesProperties;
    private final StaticCredentialsProvider staticCredentialsProvider;
    public AmazonS3ClientConfiguration(
        AmazonWebServicesProperties amazonWebServicesProperties,
        StaticCredentialsProvider staticCredentialsProvider
    ) {
        this.amazonWebServicesProperties = amazonWebServicesProperties;
        this.staticCredentialsProvider = staticCredentialsProvider;
    }
    @Bean
    public S3Client s3Client() {
        String region = amazonWebServicesProperties.getS3().getRegion();
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(staticCredentialsProvider)
            .build();
    }
    @Bean
    public String s3Bucket() {
        return amazonWebServicesProperties.getS3().getBucket();
    }
}
