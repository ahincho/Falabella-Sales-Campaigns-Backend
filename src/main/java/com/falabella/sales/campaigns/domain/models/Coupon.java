package com.falabella.sales.campaigns.domain.models;

import com.falabella.sales.users.domain.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private Long id;
    private User user;
    private Campaign campaign;
    private CouponType type;
    private String codeBar;
    private String codeWeb;
    private CouponStatus status;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
