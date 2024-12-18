package com.falabella.sales.notifications.infrastructure.adapters.out.persistence.repositories;

import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.entities.NotificationEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long>, JpaSpecificationExecutor<NotificationEntity> {
    Page<NotificationEntity> findAll(Specification<NotificationEntity> specification, Pageable pageable);
    Optional<NotificationEntity> findById(Long notificationId);
    List<NotificationEntity> findByUserId(Long userId);
}
