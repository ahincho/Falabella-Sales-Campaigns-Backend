package com.falabella.sales.campaigns.infrastructure.out.persistence.repositories;

import com.falabella.sales.campaigns.infrastructure.out.persistence.entities.CampaignEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignJpaRepository extends JpaRepository<CampaignEntity, Integer> {

}
