package com.vti.gold.Specification;

import com.vti.gold.entity.Gold;
import org.springframework.data.jpa.domain.Specification;

public class GoldSpecification {
    public static Specification<Gold> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.trim().isEmpty()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Gold> hasType(String type) {
        return (root, query, cb) -> {
            if (type == null || type.trim().isEmpty()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("type")),
                    "%" + type.toLowerCase() + "%"
            );
        };
    }
}
