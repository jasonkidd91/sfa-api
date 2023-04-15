package com.wcbeh.sfa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "customer_transaction")
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_transaction_id")
    private Long customerTransactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_portfolio_id")
    private CustomerPortfolio customerPortfolio;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentMethod paymentMethod;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_start_date")
    private Date transactionStartDate;

    @Column(name = "transaction_end_date")
    private Date transactionEndDate;

    @Column(name = "transaction_next_date")
    private Date transactionNextDate;

    @Column(name = "transaction_status")
    private String transactionStatus;

}