package com.wcbeh.sfa.repository;

import com.wcbeh.sfa.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomer_CustomerId(Long customerId);

    @Query("SELECT SUM(t.transactionAmount) FROM Transaction t " +
            " WHERE t.portfolio.portfolioId = :portfolioId" +
            " AND t.customer.customerId = :customerId")
    BigDecimal sumBalanceByCustomerPortfolio(
            @Param("portfolioId") Long portfolioId,
            @Param("customerId") Long customerId);

    @Query("SELECT SUM(t.transactionAmount) FROM Transaction t " +
            " WHERE t.portfolio.portfolioId = :portfolioId" +
            " AND t.customer.customerId = :customerId" +
            " AND t.transactionType = :transactionType")
    BigDecimal sumInvestmentReturnsByCustomerPortfolio(
            @Param("portfolioId") Long portfolioId,
            @Param("customerId") Long customerId,
            @Param("transactionType") String transactionType);

}
