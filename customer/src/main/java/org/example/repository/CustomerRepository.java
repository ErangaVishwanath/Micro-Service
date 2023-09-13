package org.example.repository;

import org.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(
            nativeQuery = true,
            value =
                    "SELECT c.id,c.firstName,c.lastName,c.email FROM customerdb.customer c JOIN orderdb.order o ON o.id=c.id where c.orderId=:orderId"
    )
    Optional<Customer> findCustomerByOrderId(@Param("orderId") int orderId);
}
