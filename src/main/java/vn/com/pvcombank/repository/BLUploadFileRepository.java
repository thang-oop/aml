package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.BLUploadFile;

/**
 * Spring Data SQL repository for the BLUploadFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BLUploadFileRepository extends JpaRepository<BLUploadFile, Long>, JpaSpecificationExecutor<BLUploadFile> {}
