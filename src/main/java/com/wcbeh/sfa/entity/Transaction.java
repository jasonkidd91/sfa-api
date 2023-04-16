package com.wcbeh.sfa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_portfolio_id")
    private CustomerPortfolio customerPortfolio;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_status")
    private String transactionStatus;

}