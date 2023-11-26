package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLRule;

/**
 * Spring Data SQL repository for the BLRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLRuleRepository extends JpaRepository<BLRule, Long> {}
