package com.enviro.assessment.junior.barayi.enviro365.controller;

import com.enviro.assessment.junior.barayi.enviro365.model.InvestmentProduct;
import com.enviro.assessment.junior.barayi.enviro365.model.Investor;
import com.enviro.assessment.junior.barayi.enviro365.service.InvestorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/investors")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;

    @GetMapping
    public ResponseEntity<List<Investor>> getAllInvestors() {
        return ResponseEntity.ok(investorService.getAllInvestors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investor> getInvestorById(@PathVariable Long id) {
        return ResponseEntity.ok(investorService.getInvestorById(id));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<InvestmentProduct>> getProductsByInvestor(@PathVariable Long id) {
        return ResponseEntity.ok(investorService.getProductsByInvestor(id));
    }
}