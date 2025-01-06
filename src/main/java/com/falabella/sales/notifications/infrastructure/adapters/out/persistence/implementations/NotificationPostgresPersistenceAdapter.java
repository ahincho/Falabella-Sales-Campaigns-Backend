package com.falabella.sales.notifications.infrastructure.adapters.out.persistence.implementations;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;
import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.entities.NotificationEntity;
import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.mappers.NotificationPersistenceMapper;
import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.repositories.NotificationJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationPostgresPersistenceAdapter implements NotificationPersistencePort {
    private final NotificationJpaRepository notificationJpaRepository;
    public NotificationPostgresPersistenceAdapter(
        NotificationJpaRepository notificationJpaRepository
    ) {
        this.notificationJpaRepository = notificationJpaRepository;
    }
    @Override
    @Transactional
    public Notification createOneNotification(Notification notification) {
        NotificationEntity savedNotification = this.notificationJpaRepository.save(NotificationPersistenceMapper.domainToEntity(notification));
        return NotificationPersistenceMapper.entityToDomain(savedNotification);
    }
    @Override
    public PaginationResult<Notification> findNotifications(NotificationFilters notificationFilters) {
        Pageable pageable = buildPageable(notificationFilters);
        Specification<NotificationEntity> specification = buildSpecification(notificationFilters);
        Page<NotificationEntity> notificationEntityPage = this.notificationJpaRepository.findAll(specification, pageable);
        return NotificationPersistenceMapper.entityPageToDomainPage(notificationEntityPage);
    }
    @Override
    public Optional<Notification> findOneNotificationById(Long notificationId) {
        return this.notificationJpaRepository.findById(notificationId).map(NotificationPersistenceMapper::entityToDomain);
    }

    
    @Override
    public List<Notification> findNotificationsByUserId(Long userId) {
        List<NotificationEntity> notificationEntities = this.notificationJpaRepository.findByUserId(userId);
        return notificationEntities.stream()
                .map(NotificationPersistenceMapper::entityToDomain)
                .collect(Collectors.toList());
    }
    

    @Override
    public Boolean existsOneNotificationById(Long notificationId) {
        return this.notificationJpaRepository.existsById(notificationId);
    }

    @Override
    @Transactional
    public void updateOneNotificationById(Long notificationId, Notification notification) {
        this.notificationJpaRepository.save(NotificationPersistenceMapper.domainToEntity(notification));
    }
    @Override
    @Transactional
    public void deleteOneNotificationById(Long notificationId) {
        this.notificationJpaRepository.deleteById(notificationId);
    }
    private Pageable buildPageable(NotificationFilters notificationFilters) {
        return PageRequest.of(notificationFilters.getPage().getNumber(), notificationFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
    }
    private Specification<NotificationEntity> buildSpecification(NotificationFilters notificationFilters) {
        Specification<NotificationEntity> specification = Specification.where(null);
        
        return specification;
    }
}
