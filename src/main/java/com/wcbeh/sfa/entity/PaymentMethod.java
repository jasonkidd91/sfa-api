package com.wcbeh.sfa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "payment_name")
    private String paymentName;

    @Column(name = "is_active")
    private boolean isActive;

}

