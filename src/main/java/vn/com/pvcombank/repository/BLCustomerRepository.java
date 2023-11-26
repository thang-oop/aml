package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLCustomer;

/**
 * Spring Data SQL repository for the BLCustomer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLCustomerRepository extends JpaRepository<BLCustomer, Long>, JpaSpecificationExecutor<BLCustomer> {}
