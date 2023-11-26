package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLCustomerPvc;

/**
 * Spring Data SQL repository for the BLCustomerPvc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLCustomerPvcRepository extends JpaRepository<BLCustomerPvc, Long>, JpaSpecificationExecutor<BLCustomerPvc> {}
