package com.enviro.assessment.junior.barayi.enviro365.repository;

import com.enviro.assessment.junior.barayi.enviro365.model.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {

    List<WithdrawalNotice> findByProductId(Long productId);
}