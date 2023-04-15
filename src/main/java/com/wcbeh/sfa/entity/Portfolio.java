package com.wcbeh.sfa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @Column(name = "portfolio_name")
    private String portfolioName;

    @Column(name = "portfolio_desc")
    private String portfolioDesc;

}