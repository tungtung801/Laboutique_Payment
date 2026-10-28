package com.structure.springboot.features.payment.repository;

import com.structure.springboot.features.order.entity.OrderEntity;
import com.structure.springboot.features.payment.entity.PaymentEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository
        extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByTxnRef(String txnRef);

    Optional<PaymentEntity> findFirstByOrderIdOrderByCreatedAtDesc(
            UUID orderId
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OrderEntity> findForPaymentByOrderNumberAndAccountId(
            String orderNumber,
            UUID accountId
    );
}