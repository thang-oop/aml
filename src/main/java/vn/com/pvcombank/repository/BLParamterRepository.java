package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLParamter;

/**
 * Spring Data SQL repository for the BLParamter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLParamterRepository extends JpaRepository<BLParamter, Long>, JpaSpecificationExecutor<BLParamter> {
    //@Query(value="select * from bl_parameter a where a.key_id =:keyId", nativeQuery=true)
    Optional<BLParamter> findByKeyId(String keyId);
}
