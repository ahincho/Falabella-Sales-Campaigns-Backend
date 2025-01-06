package com.falabella.sales.commons.infrastructure.configurations.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Configuration
public class AmazonCredentialsConfiguration {
    private final AmazonWebServicesProperties amazonWebServicesProperties;
    public AmazonCredentialsConfiguration(AmazonWebServicesProperties amazonWebServicesProperties) {
        this.amazonWebServicesProperties = amazonWebServicesProperties;
    }
    @Bean
    public StaticCredentialsProvider credentialsProvider() {
        String accessKey = amazonWebServicesProperties.getCredentials().getKeys().getAccess();
        String secretKey = amazonWebServicesProperties.getCredentials().getKeys().getSecret();
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return StaticCredentialsProvider.create(awsCredentials);
    }
}
