package com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_campaign")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponCampaignJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignJpaEntity campaign;

    private String couponCode;
    private Boolean isUsed;
    private String usedAt;
}