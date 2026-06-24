package com.enviro.assessment.junior.barayi.enviro365.controller;

import com.enviro.assessment.junior.barayi.enviro365.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.junior.barayi.enviro365.model.WithdrawalNotice;
import com.enviro.assessment.junior.barayi.enviro365.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
@RequiredArgsConstructor
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<WithdrawalNoticeDTO> createWithdrawal(
            @PathVariable Long productId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(withdrawalService.createWithdrawal(productId, amount));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<WithdrawalNoticeDTO>> getWithdrawalsByProduct(
            @PathVariable Long productId) {
        return ResponseEntity.ok(withdrawalService.getWithdrawalsByProduct(productId));
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportWithdrawals(
            @RequestParam(required = false) Long productId) {
        String csv = withdrawalService.exportWithdrawalsAsCsv(productId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=withdrawals.csv")
                .header("Content-Type", "text/csv")
                .body(csv);
    }
}