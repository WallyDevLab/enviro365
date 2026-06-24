package com.enviro.assessment.junior.barayi.enviro365.repository;

import com.enviro.assessment.junior.barayi.enviro365.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
}