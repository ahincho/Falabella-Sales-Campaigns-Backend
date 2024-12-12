package com.falabella.sales.files.infrastructure.out;

import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.models.ContentType;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class AwsS3StorageAdapter implements StoragePort {
    public final S3Client s3Client;
    public final String s3Bucket;
    public AwsS3StorageAdapter(S3Client s3Client, String s3Bucket) {
        this.s3Client = s3Client;
        this.s3Bucket = s3Bucket;
    }
    @Override
    public String createOneFile(String fileName, InputStream inputStream, ContentType contentType) {
        return "";
    }
    @Override
    public List<String> findFiles() {
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
            .bucket(s3Bucket)
            .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);
        return listObjectsV2Response.contents().stream().map(S3Object::key).toList();
    }
    @Override
    public Optional<InputStream> getOneFile(String fileName) {
        return Optional.empty();
    }
    @Override
    public void updateOneFile(String fileName, InputStream inputStream, ContentType contentType) {

    }
    @Override
    public void deleteOneFile(String fileName) {

    }
}
