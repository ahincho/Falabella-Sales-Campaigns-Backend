package com.falabella.sales.campaigns.infrastructure.out.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "campaigns")
@Entity(name = "campaign")
public class CampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String loginMessage;
    private String loginImage;
    private String backgroundColor;
    private String homeMessage;
    private String homeMessageImage;
    private String messageTextColor;
    private String couponUsageMessage;
    private String legalConditions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
