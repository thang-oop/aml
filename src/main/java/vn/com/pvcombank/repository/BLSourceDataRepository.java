package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLSourceData;

/**
 * Spring Data SQL repository for the BLMappingParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLSourceDataRepository extends JpaRepository<BLSourceData, Long>, JpaSpecificationExecutor<BLSourceData> {}
