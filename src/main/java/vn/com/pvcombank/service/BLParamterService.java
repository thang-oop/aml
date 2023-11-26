package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.BLParamter;
import vn.com.pvcombank.repository.BLParamterRepository;

/**
 * Service Implementation for managing {@link BLParamter}.
 */
@Service
@Transactional
public class BLParamterService {

    private final Logger log = LoggerFactory.getLogger(BLParamterService.class);

    private final BLParamterRepository bLParamterRepository;

    public BLParamterService(BLParamterRepository bLParamterRepository) {
        this.bLParamterRepository = bLParamterRepository;
    }

    /**
     * Save a bLParamter.
     *
     * @param bLParamter the entity to save.
     * @return the persisted entity.
     */
    public BLParamter save(BLParamter bLParamter) {
        log.debug("Request to save BLParamter : {}", bLParamter);
        return bLParamterRepository.save(bLParamter);
    }

    /**
     * Partially update a bLParamter.
     *
     * @param bLParamter the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BLParamter> partialUpdate(BLParamter bLParamter) {
        log.debug("Request to partially update BLParamter : {}", bLParamter);

        return bLParamterRepository
            .findById(bLParamter.getId())
            .map(
                existingBLParamter -> {
                    if (bLParamter.getKeyId() != null) {
                        existingBLParamter.setKeyId(bLParamter.getKeyId());
                    }
                    if (bLParamter.getKeyValue() != null) {
                        existingBLParamter.setKeyValue(bLParamter.getKeyValue());
                    }
                    if (bLParamter.getRecordStatus() != null) {
                        existingBLParamter.setRecordStatus(bLParamter.getRecordStatus());
                    }
                    if (bLParamter.getDescription() != null) {
                        existingBLParamter.setDescription(bLParamter.getDescription());
                    }
                    if (bLParamter.getCreatedBy() != null) {
                        existingBLParamter.setCreatedBy(bLParamter.getCreatedBy());
                    }
                    if (bLParamter.getDateCreated() != null) {
                        existingBLParamter.setDateCreated(bLParamter.getDateCreated());
                    }

                    return existingBLParamter;
                }
            )
            .map(bLParamterRepository::save);
    }

    /**
     * Get all the bLParamters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BLParamter> findAll(Pageable pageable) {
        log.debug("Request to get all BLParamters");
        return bLParamterRepository.findAll(pageable);
    }

    /**
     * Get one bLParamter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BLParamter> findOne(Long id) {
        log.debug("Request to get BLParamter : {}", id);
        return bLParamterRepository.findById(id);
    }

    /**
     * Delete the bLParamter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BLParamter : {}", id);
        bLParamterRepository.deleteById(id);
    }

    /**
     * Get one bLParamter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BLParamter> findByKeyId(String keyId) {
        log.debug("Request to get BLParamter : {}", keyId);
        return bLParamterRepository.findByKeyId(keyId);
    }
}
