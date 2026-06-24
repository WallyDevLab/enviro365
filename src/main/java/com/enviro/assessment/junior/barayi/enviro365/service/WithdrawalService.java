package com.enviro.assessment.junior.barayi.enviro365.service;

import com.enviro.assessment.junior.barayi.enviro365.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.junior.barayi.enviro365.exception.WithdrawalValidationException;
import com.enviro.assessment.junior.barayi.enviro365.model.*;
import com.enviro.assessment.junior.barayi.enviro365.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final InvestmentProductRepository productRepository;

    public WithdrawalNoticeDTO createWithdrawal(Long productId, BigDecimal amount) {
        InvestmentProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new WithdrawalValidationException("Product not found with id: " + productId));

        Investor investor = product.getInvestor();
        long age = ChronoUnit.YEARS.between(investor.getDateOfBirth(), LocalDate.now());

        if (product.getProductType() == ProductType.RETIREMENT_ANNUITY && age < 65) {
            throw new WithdrawalValidationException("Retirement withdrawals only allowed for investors older than 65");
        }

        BigDecimal maxAllowed = product.getBalance().multiply(BigDecimal.valueOf(0.90));
        if (amount.compareTo(maxAllowed) > 0) {
            throw new WithdrawalValidationException("Withdrawal amount exceeds 90% of balance. Maximum allowed: " + maxAllowed);
        }

        product.setBalance(product.getBalance().subtract(amount));
        productRepository.save(product);

        WithdrawalNotice notice = new WithdrawalNotice();
        notice.setProduct(product);
        notice.setAmount(amount);
        notice.setWithdrawalDate(LocalDateTime.now());
        notice.setStatus("COMPLETED");

        WithdrawalNotice savedNotice = withdrawalNoticeRepository.save(notice);
        return mapToDTO(savedNotice);
    }

    public List<WithdrawalNoticeDTO> getWithdrawalsByProduct(Long productId) {
        return withdrawalNoticeRepository.findByProductId(productId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private WithdrawalNoticeDTO mapToDTO(WithdrawalNotice notice) {
        if (notice == null) {
            return null;
        }

        WithdrawalNoticeDTO dto = new WithdrawalNoticeDTO();
        dto.setId(notice.getId());
        dto.setAmount(notice.getAmount());
        dto.setWithdrawalDate(notice.getWithdrawalDate());
        dto.setProductId(notice.getProduct().getId());
        dto.setProductName(notice.getProduct().getProductName());
        dto.setStatus(notice.getStatus());
        return dto;
    }

    public String exportWithdrawalsAsCsv(Long productId) {
        List<WithdrawalNotice> withdrawals = productId != null
                ? withdrawalNoticeRepository.findByProductId(productId)
                : withdrawalNoticeRepository.findAll();

        StringBuilder csv = new StringBuilder();
        csv.append("ID,Amount,Status,Withdrawal Date,Product Name\n");

        for (WithdrawalNotice w : withdrawals) {
            csv.append(w.getId()).append(",")
                    .append(w.getAmount()).append(",")
                    .append(w.getStatus()).append(",")
                    .append(w.getWithdrawalDate().toLocalDate()).append(",")
                    .append(w.getProduct().getProductName()).append("\n");
        }

        return csv.toString();
    }
}