package com.enviro.assessment.junior.barayi.enviro365.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "investment_products")
public class InvestmentProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
}