package com.falabella.sales.users.infrastructure.adapters.out.persistence.implementations;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.out.RolePersistencePort;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories.RoleJpaRepository;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.RoleQuerySpecifications;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications.SpecificationUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolePostgresPersistenceAdapter implements RolePersistencePort {
    private final RoleJpaRepository roleJpaRepository;
    public RolePostgresPersistenceAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }
    @Override
    public PaginationResult<Role> findRoles(RoleFilters roleFilters) {
        Specification<RoleEntity> specification = Specification.where(null);
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, RoleQuerySpecifications.nameContains(roleFilters.getName()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, RoleQuerySpecifications.descriptionContains(roleFilters.getDescription()));
        Pageable pageable = PageRequest.of(roleFilters.getPage().getNumber(), roleFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
        Page<RoleEntity> roleEntities = this.roleJpaRepository.findAll(specification, pageable);
        return
    }
    @Override
    public Optional<Role> findRoleById(Integer roleId) {

    }
    @Override
    public Optional<Role> findRoleByName(String roleName) {

    }
}
