package com.falabella.sales.users.infrastructure.adapters.out.persistence.implementations;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.UserEntity;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.mappers.UserPersistenceMapper;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories.UserJpaRepository;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.SpecificationUtils;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.UserQuerySpecifications;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserPostgresPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    public UserPostgresPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    @Override
    @Transactional
    public User createOneUser(User user) {
        UserEntity savedUser = this.userJpaRepository.save(UserPersistenceMapper.domainToEntity(user));
        return UserPersistenceMapper.entityToDomain(savedUser);
    }
    @Override
    @Transactional
    public User assignRolesToUser(Long userId, List<Integer> roleIds) {
        return null;
    }
    @Override
    public PaginationResult<User> findUsers(UserFilters userFilters) {
        Pageable pageable = buildPageable(userFilters);
        Specification<UserEntity> specification = buildSpecification(userFilters);
        Page<UserEntity> userEntityPage = this.userJpaRepository.findAll(specification, pageable);
        return UserPersistenceMapper.entityPageToDomainPage(userEntityPage);
    }
    @Override
    public Optional<User> findOneUserById(Long userId) {
        return this.userJpaRepository.findById(userId).map(UserPersistenceMapper::entityToDomain);
    }
    @Override
    public Optional<User> findOneUserByUsername(String username) {
        return this.userJpaRepository.findByUsernameIgnoreCase(username).map(UserPersistenceMapper::entityWithNoRolesToDomain);
    }
    @Override
    public Optional<User> findOneUserByEmail(String email) {
        return this.userJpaRepository.findByEmailIgnoreCase(email).map(UserPersistenceMapper::entityWithNoRolesToDomain);
    }
    @Override
    public Boolean existsOneUserById(Long userId) {
        return this.userJpaRepository.existsById(userId);
    }
    @Override
    public Boolean existsOneUserByUsername(String username) {
        return this.userJpaRepository.existsByUsernameIgnoreCase(username);
    }
    @Override
    public Boolean existsOneUserByEmail(String email) {
        return this.userJpaRepository.existsByEmailIgnoreCase(email);
    }
    @Override
    @Transactional
    public void updateOneUserById(Long userId, User user) {
        this.userJpaRepository.save(UserPersistenceMapper.domainToEntity(user));
    }
    @Override
    @Transactional
    public void deleteOneUserById(Long userId) {
        this.userJpaRepository.deleteById(userId);
    }
    @Override
    @Transactional
    public void revokeRolesToUser(Long userId, List<Long> roleIds) {

    }
    private Pageable buildPageable(UserFilters userFilters) {
        return PageRequest.of(userFilters.getPage().getNumber(), userFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
    }
    private Specification<UserEntity> buildSpecification(UserFilters userFilters) {
        Specification<UserEntity> specification = Specification.where(null);
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, UserQuerySpecifications.firstnameContains(userFilters.getFirstname()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, UserQuerySpecifications.lastnameContains(userFilters.getLastname()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, UserQuerySpecifications.usernameContains(userFilters.getUsername()));
        return specification;
    }
}
