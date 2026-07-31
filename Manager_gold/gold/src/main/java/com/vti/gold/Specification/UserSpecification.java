package com.vti.gold.Specification;

import com.vti.gold.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasUsername(String username) {

        return (root, query, criteriaBuilder) -> {

            if (username == null || username.trim().isEmpty()) {
                return null;
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%");

        };

    }

    public static Specification<User> hasEmail(String email) {

        return (root, query, criteriaBuilder) -> {

            if (email == null || email.trim().isEmpty()) {
                return null;
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");

        };

    }

    public static Specification<User> hasFullName(String fullName) {

        return (root, query, criteriaBuilder) -> {

            if (fullName == null || fullName.trim().isEmpty()) {
                return null;
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");

        };

    }


}
