package com.vti.gold.Specification;

import com.vti.gold.entity.Order;
import com.vti.gold.entity.OrderStatus;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> hasStatus(OrderStatus status) {

        return (root, query, cb) -> {

            if (status == null) {
                return null;
            }

            return cb.equal(root.get("status"), status);
        };

    }

    public static Specification<Order> hasUsername(String username) {

        return (root, query, cb) -> {

            if (username == null || username.trim().isEmpty()) {
                return null;
            }


            Join<Object, Object> user = root.join("user");


            query.distinct(true);


            return cb.like(cb.lower(user.get("username")), "%" + username.toLowerCase() + "%");


        };

    }

}
