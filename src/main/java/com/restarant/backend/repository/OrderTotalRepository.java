package com.restarant.backend.repository;

import com.restarant.backend.entity.OrderTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderTotal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {
    @Query("SELECT o FROM OrderTotal o WHERE o.customer.id = :id AND o.status = 0")
    OrderTotal getOrderTotalByCustomerId(Long id);
}
