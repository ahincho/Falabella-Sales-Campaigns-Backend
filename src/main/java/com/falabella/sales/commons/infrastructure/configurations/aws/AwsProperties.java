package com.falabella.sales.commons.infrastructure.configurations.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsProperties {

    private S3 s3 = new S3();
    private Credentials credentials = new Credentials();

    @Data
    public static class S3 {
        private String bucketName;
        private String region;
    }

    @Data
    public static class Credentials {
        private String accessKey;
        private String secretKey;
    }
}