package com.restarant.backend.repository;

import com.restarant.backend.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Voucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {}
