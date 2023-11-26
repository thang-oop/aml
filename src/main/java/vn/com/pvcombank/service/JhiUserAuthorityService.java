package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.JhiUserAuthority;
import vn.com.pvcombank.repository.JhiUserAuthorityRepository;

/**
 * Service Implementation for managing {@link JhiUserAuthority}.
 */
@Service
@Transactional
public class JhiUserAuthorityService {

    private final Logger log = LoggerFactory.getLogger(JhiUserAuthorityService.class);

    private final JhiUserAuthorityRepository jhiUserAuthorityRepository;

    public JhiUserAuthorityService(JhiUserAuthorityRepository jhiUserAuthorityRepository) {
        this.jhiUserAuthorityRepository = jhiUserAuthorityRepository;
    }

    /**
     * Save a jhiUserAuthority.
     *
     * @param jhiUserAuthority the entity to save.
     * @return the persisted entity.
     */
    public JhiUserAuthority save(JhiUserAuthority jhiUserAuthority) {
        log.debug("Request to save JhiUserAuthority : {}", jhiUserAuthority);
        return jhiUserAuthorityRepository.save(jhiUserAuthority);
    }

    /**
     * Partially update a jhiUserAuthority.
     *
     * @param jhiUserAuthority the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JhiUserAuthority> partialUpdate(JhiUserAuthority jhiUserAuthority) {
        log.debug("Request to partially update JhiUserAuthority : {}", jhiUserAuthority);

        return jhiUserAuthorityRepository
            .findById(jhiUserAuthority.getId())
            .map(
                existingJhiUserAuthority -> {
                    if (jhiUserAuthority.getUserId() != null) {
                        existingJhiUserAuthority.setUserId(jhiUserAuthority.getUserId());
                    }
                    if (jhiUserAuthority.getAuthorityName() != null) {
                        existingJhiUserAuthority.setAuthorityName(jhiUserAuthority.getAuthorityName());
                    }

                    return existingJhiUserAuthority;
                }
            )
            .map(jhiUserAuthorityRepository::save);
    }

    /**
     * Get all the jhiUserAuthorities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JhiUserAuthority> findAll(Pageable pageable) {
        log.debug("Request to get all JhiUserAuthorities");
        return jhiUserAuthorityRepository.findAll(pageable);
    }

    /**
     * Get one jhiUserAuthority by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JhiUserAuthority> findOne(Long id) {
        log.debug("Request to get JhiUserAuthority : {}", id);
        return jhiUserAuthorityRepository.findById(id);
    }

    /**
     * Delete the jhiUserAuthority by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JhiUserAuthority : {}", id);
        jhiUserAuthorityRepository.deleteById(id);
    }
}
