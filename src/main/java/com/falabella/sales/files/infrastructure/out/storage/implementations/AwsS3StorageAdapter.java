package com.falabella.sales.files.infrastructure.out.storage.implementations;

import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.domain.models.FileFilters;
import com.falabella.sales.files.infrastructure.out.storage.mappers.AwsS3Mapper;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;
import java.util.Optional;

@Service
public class AwsS3StorageAdapter implements StoragePort {
    private final S3Client s3Client;
    private final String s3Bucket;
    private final String cloudfrontEndpoint;
    public AwsS3StorageAdapter(S3Client s3Client, String s3Bucket, String cloudfrontEndpoint) {
        this.s3Client = s3Client;
        this.s3Bucket = s3Bucket;
        this.cloudfrontEndpoint = cloudfrontEndpoint;
    }
    @Override
    public File createOneFile(String directory, File file) {
        String key = (directory != null && !directory.isEmpty()) ? directory + "/" + file.getName() : file.getName();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(this.s3Bucket)
            .key(key)
            .contentType(file.getType().getMimeType())
            .build();
        this.s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getContent(), file.getSize()));
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
            .bucket(this.s3Bucket)
            .key(key)
            .build();
        HeadObjectResponse headObjectResponse = this.s3Client.headObject(headObjectRequest);
        return AwsS3Mapper.s3ToDomain(this.cloudfrontEndpoint, headObjectResponse, key);
    }
    @Override
    public List<File> findFiles(String directory, FileFilters fileFilters) {
        return List.of();
    }
    @Override
    public Optional<File> findOneFile(String directory, String fileName) {
        String key = (directory != null && !directory.isEmpty()) ? directory + "/" + fileName : fileName;
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(this.s3Bucket)
                .key(key)
                .build();
            HeadObjectResponse headObjectResponse = this.s3Client.headObject(headObjectRequest);
            File file = AwsS3Mapper.s3ToDomain(this.cloudfrontEndpoint, headObjectResponse, key);
            return Optional.of(file);
        } catch (NoSuchKeyException noSuchKeyException) {
            return Optional.empty();
        }
    }
    @Override
    public Boolean existsOneFileByName(String directory, String fileName) {
        String key = (directory != null && !directory.isEmpty()) ? directory + "/" + fileName : fileName;
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(this.s3Bucket)
                .key(key)
                .build();
            this.s3Client.headObject(headObjectRequest);
            return true;
        } catch (NoSuchKeyException noSuchKeyException) {
            return false;
        }
    }
    @Override
    public File updateOneFile(String directory, String fileName, File file) {
        return null;
    }
    @Override
    public void deleteOneFile(String directory, String fileName) {
        String key = (directory != null && !directory.isEmpty()) ? directory + "/" + fileName : fileName;
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(this.s3Bucket)
            .key(key)
            .build();
        this.s3Client.deleteObject(deleteObjectRequest);
    }
}
