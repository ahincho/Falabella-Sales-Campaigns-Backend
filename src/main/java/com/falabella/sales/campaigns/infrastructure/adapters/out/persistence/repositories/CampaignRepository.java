package com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.repositories;

import com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.entities.CampaignJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignJpaEntity, Long> {
}