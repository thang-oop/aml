package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLCondition;

/**
 * Spring Data SQL repository for the BLCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLConditionRepository extends JpaRepository<BLCondition, Long> {}
