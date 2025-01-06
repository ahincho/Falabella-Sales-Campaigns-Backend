package com.falabella.sales.notifications.infrastructure.adapters.out.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {
    private SpecificationUtils() {}
    public static <T> Specification<T> applySpecificationIfNotNull(Specification<T> specification, Specification<T> condition) {
        return condition == null ? specification : specification.and(condition);
    }
}
