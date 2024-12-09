package com.falabella.sales.users.infrastructure.adapters.out.persistence.implementations;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.out.RolePersistencePort;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.mappers.RolePersistenceMapper;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories.RoleJpaRepository;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.RoleQuerySpecifications;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.SpecificationUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolePostgresPersistenceAdapter implements RolePersistencePort {
    private final RoleJpaRepository roleJpaRepository;
    public RolePostgresPersistenceAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }
    @Override
    public PaginationResult<Role> findRoles(RoleFilters roleFilters) {
        Pageable pageable = buildPageable(roleFilters);
        Specification<RoleEntity> specification = buildSpecification(roleFilters);
        Page<RoleEntity> roleEntityPage = this.roleJpaRepository.findAll(specification, pageable);
        return RolePersistenceMapper.entityPageToDomainPage(roleEntityPage);
    }
    @Override
    public Optional<Role> findRoleById(Integer roleId) {
        return this.roleJpaRepository.findById(roleId).map(RolePersistenceMapper::entityToDomain);
    }
    @Override
    public List<Role> findRolesInIds(List<Integer> roleIds) {
        return this.roleJpaRepository.findAllById(roleIds).stream()
            .map(RolePersistenceMapper::entityToDomain).toList();
    }
    private Pageable buildPageable(RoleFilters roleFilters) {
        return PageRequest.of(roleFilters.getPage().getNumber(), roleFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
    }
    private Specification<RoleEntity> buildSpecification(RoleFilters roleFilters) {
        Specification<RoleEntity> specification = Specification.where(null);
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, RoleQuerySpecifications.nameContains(roleFilters.getName()));
        return specification;
    }
}
