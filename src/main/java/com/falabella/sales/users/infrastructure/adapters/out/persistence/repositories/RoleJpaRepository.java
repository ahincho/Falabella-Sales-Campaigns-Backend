package com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories;

import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer>, JpaSpecificationExecutor<RoleEntity> {

}
