package com.restarant.backend.repository;

import com.restarant.backend.entity.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TableOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableOrderRepository extends JpaRepository<TableOrder, Long> {}
