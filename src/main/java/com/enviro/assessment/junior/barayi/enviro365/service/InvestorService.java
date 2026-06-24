package com.enviro.assessment.junior.barayi.enviro365.service;

import com.enviro.assessment.junior.barayi.enviro365.model.InvestmentProduct;
import com.enviro.assessment.junior.barayi.enviro365.model.Investor;
import com.enviro.assessment.junior.barayi.enviro365.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository;

    public Investor getInvestorById(Long id) {
        return investorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investor not found with id: " + id));
    }

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public List<InvestmentProduct> getProductsByInvestor(Long investorId) {
        Investor investor = getInvestorById(investorId);
        return investor.getProducts();
    }
}