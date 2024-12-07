package com.falabella.sales.campaigns.infrastructure.adapters.in.rest;

import com.falabella.sales.campaigns.application.ports.in.CreateCampaignUseCase;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.infrastructure.adapters.in.rest.dtos.CampaignCreateRequest;
import com.falabella.sales.campaigns.domain.models.CouponCampaign;
import com.falabella.sales.config.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private AwsProperties awsProperties;

    @Autowired
    private CreateCampaignUseCase createCampaignUseCase;

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@Validated @ModelAttribute CampaignCreateRequest request) throws IOException {
        // Subir imágenes a S3
        String loginImagePath = uploadImageToS3(request.getLoginImage());
        String homeImagePath = uploadImageToS3(request.getHomeImage());

        // Convertir DTOs a entidades del dominio
        List<CouponCampaign> couponsList = convertToDomainCoupons(request.getCouponsList());

        // Crear la campaña
        Campaign campaign = Campaign.builder()
                .name(request.getName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalCoupons(request.getTotalCoupons())
                .usedCouponsCount(request.getUsedCouponsCount())
                .status(request.getStatus())
                .loginMessage(request.getLoginMessage())
                .loginImage(loginImagePath)
                .backgroundColor(request.getBackgroundColor())
                .homeMessageImage(homeImagePath)
                .homeMessage(request.getHomeMessage())
                .messageTextColor(request.getMessageTextColor())
                .couponUsageMessage(request.getCouponUsageMessage())
                .legalConditions(request.getLegalConditions())
                .couponsList(couponsList)
                .build();

        // Usar el caso de uso para crear la campaña
        Campaign createdCampaign = createCampaignUseCase.createCampaign(campaign);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCampaign.getId()).toUri();

        return ResponseEntity.created(location).body(createdCampaign);
    }

    private String uploadImageToS3(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String key = Paths.get(image.getOriginalFilename()).getFileName().toString();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucketName())
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest,
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(image.getBytes()));

            return s3Client.utilities().getUrl(builder -> builder.bucket(awsProperties.getS3().getBucketName()).key(key)).toExternalForm();
        }
        return null;
    }

    private List<CouponCampaign> convertToDomainCoupons(List<com.falabella.sales.campaigns.infrastructure.adapters.in.rest.dtos.CouponCampaign> dtoCoupons) {
        return dtoCoupons.stream()
                .map(dto -> CouponCampaign.builder()
                        .id(dto.getId())
                        .campaignId(dto.getCampaignId())
                        .couponCode(dto.getCouponCode())
                        .isUsed(dto.getIsUsed())
                        .usedAt(dto.getUsedAt())
                        .build())
                .collect(Collectors.toList());
    }
}