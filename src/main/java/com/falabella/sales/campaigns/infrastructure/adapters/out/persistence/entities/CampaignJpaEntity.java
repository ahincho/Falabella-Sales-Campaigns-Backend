package com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "campaign")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String startDate;
    private String endDate;
    private String totalCoupons;
    private String usedCouponsCount;
    private Boolean status;
    private String createdAt;
    private String loginMessage;
    private String loginImage;
    private String backgroundColor;
    private String homeMessageImage;
    private String homeImage;
    private String homeMessage;
    private String messageTextColor;
    private String couponUsageMessage;
    private String legalConditions;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CouponCampaignJpaEntity> couponsList;
}