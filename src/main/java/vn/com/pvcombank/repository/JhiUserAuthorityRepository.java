package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.JhiUserAuthority;

/**
 * Spring Data SQL repository for the JhiUserAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JhiUserAuthorityRepository extends JpaRepository<JhiUserAuthority, Long>, JpaSpecificationExecutor<JhiUserAuthority> {}
