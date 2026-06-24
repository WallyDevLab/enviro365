package com.enviro.assessment.junior.barayi.enviro365.repository;

import com.enviro.assessment.junior.barayi.enviro365.model.InvestmentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentProductRepository  extends JpaRepository<InvestmentProduct, Long> {
}