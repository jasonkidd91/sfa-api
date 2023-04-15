package com.wcbeh.sfa.repository;

import com.wcbeh.sfa.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

    List<CustomerTransaction> findByTransactionStatusAndCustomer_CustomerId(String transactionStatus, Long customerId);

}
