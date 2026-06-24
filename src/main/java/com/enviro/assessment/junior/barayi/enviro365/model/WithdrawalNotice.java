package com.enviro.assessment.junior.barayi.enviro365.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "withdrawal_notices")
public class WithdrawalNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDateTime withdrawalDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private InvestmentProduct product;
}