package com.falabella.sales.commons.infrastructure.configurations.aws;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws")
public class AmazonWebServicesProperties {
    private S3 s3;
    private Cloudfront cloudfront;
    private Credentials credentials;
    @Data
    public static class S3 {
        private String bucket;
        private String region;
    }
    @Data
    public static class Cloudfront {
        private String endpoint;
    }
    @Data
    public static class Credentials {
        private Keys keys;
    }
    @Data
    public static class Keys {
        private String access;
        private String secret;
    }
}
