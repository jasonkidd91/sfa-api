package com.wcbeh.sfa.repository;

import com.wcbeh.sfa.entity.CustomerPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPortfolioRepository extends JpaRepository<CustomerPortfolio, Long> {

    List<CustomerPortfolio> findByCustomer_CustomerId(Long customerId);

}
