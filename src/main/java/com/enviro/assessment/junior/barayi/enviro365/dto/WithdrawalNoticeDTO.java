package com.enviro.assessment.junior.barayi.enviro365.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalNoticeDTO {
    private Long id;
    private BigDecimal amount;
    private String status;
    private LocalDateTime withdrawalDate;
    private Long productId;
    private String productName;
}