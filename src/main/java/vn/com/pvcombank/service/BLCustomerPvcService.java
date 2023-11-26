package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.BLCustomerPvc;
import vn.com.pvcombank.repository.BLCustomerPvcRepository;

/**
 * Service Implementation for managing {@link BLCustomerPvc}.
 */
@Service
@Transactional
public class BLCustomerPvcService {

    private final Logger log = LoggerFactory.getLogger(BLCustomerPvcService.class);

    private final BLCustomerPvcRepository bLCustomerPvcRepository;

    public BLCustomerPvcService(BLCustomerPvcRepository bLCustomerPvcRepository) {
        this.bLCustomerPvcRepository = bLCustomerPvcRepository;
    }

    /**
     * Save a bLCustomerPvc.
     *
     * @param bLCustomerPvc the entity to save.
     * @return the persisted entity.
     */
    public BLCustomerPvc save(BLCustomerPvc bLCustomerPvc) {
        log.debug("Request to save BLCustomerPvc : {}", bLCustomerPvc);
        return bLCustomerPvcRepository.save(bLCustomerPvc);
    }

    /**
     * Partially update a bLCustomerPvc.
     *
     * @param bLCustomerPvc the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BLCustomerPvc> partialUpdate(BLCustomerPvc bLCustomerPvc) {
        log.debug("Request to partially update BLCustomerPvc : {}", bLCustomerPvc);

        return bLCustomerPvcRepository
            .findById(bLCustomerPvc.getId())
            .map(
                existingBLCustomerPvc -> {
                    if (bLCustomerPvc.getCif() != null) {
                        existingBLCustomerPvc.setCif(bLCustomerPvc.getCif());
                    }
                    if (bLCustomerPvc.getFullName() != null) {
                        existingBLCustomerPvc.setFullName(bLCustomerPvc.getFullName());
                    }
                    if (bLCustomerPvc.getDateOfBirth() != null) {
                        existingBLCustomerPvc.setDateOfBirth(bLCustomerPvc.getDateOfBirth());
                    }
                    if (bLCustomerPvc.getLegalId() != null) {
                        existingBLCustomerPvc.setLegalId(bLCustomerPvc.getLegalId());
                    }
                    if (bLCustomerPvc.getLegalType() != null) {
                        existingBLCustomerPvc.setLegalType(bLCustomerPvc.getLegalType());
                    }
                    if (bLCustomerPvc.getBranch() != null) {
                        existingBLCustomerPvc.setBranch(bLCustomerPvc.getBranch());
                    }
                    if (bLCustomerPvc.getBlCustomerId() != null) {
                        existingBLCustomerPvc.setBlCustomerId(bLCustomerPvc.getBlCustomerId());
                    }
                    if (bLCustomerPvc.getNameBl() != null) {
                        existingBLCustomerPvc.setNameBl(bLCustomerPvc.getNameBl());
                    }
                    if (bLCustomerPvc.getDateOfBirthBl() != null) {
                        existingBLCustomerPvc.setDateOfBirthBl(bLCustomerPvc.getDateOfBirthBl());
                    }
                    if (bLCustomerPvc.getLegalIdTypeBl() != null) {
                        existingBLCustomerPvc.setLegalIdTypeBl(bLCustomerPvc.getLegalIdTypeBl());
                    }
                    if (bLCustomerPvc.getLegalIdNumber() != null) {
                        existingBLCustomerPvc.setLegalIdNumber(bLCustomerPvc.getLegalIdNumber());
                    }
                    if (bLCustomerPvc.getMatchAttr() != null) {
                        existingBLCustomerPvc.setMatchAttr(bLCustomerPvc.getMatchAttr());
                    }
                    if (bLCustomerPvc.getValueAttr() != null) {
                        existingBLCustomerPvc.setValueAttr(bLCustomerPvc.getValueAttr());
                    }
                    if (bLCustomerPvc.getWeightAttr() != null) {
                        existingBLCustomerPvc.setWeightAttr(bLCustomerPvc.getWeightAttr());
                    }
                    if (bLCustomerPvc.getScore() != null) {
                        existingBLCustomerPvc.setScore(bLCustomerPvc.getScore());
                    }
                    if (bLCustomerPvc.getStatus() != null) {
                        existingBLCustomerPvc.setStatus(bLCustomerPvc.getStatus());
                    }
                    if (bLCustomerPvc.getRemark() != null) {
                        existingBLCustomerPvc.setRemark(bLCustomerPvc.getRemark());
                    }
                    if (bLCustomerPvc.getRecordStatus() != null) {
                        existingBLCustomerPvc.setRecordStatus(bLCustomerPvc.getRecordStatus());
                    }
                    if (bLCustomerPvc.getCoCode() != null) {
                        existingBLCustomerPvc.setCoCode(bLCustomerPvc.getCoCode());
                    }
                    if (bLCustomerPvc.getCreatedBy() != null) {
                        existingBLCustomerPvc.setCreatedBy(bLCustomerPvc.getCreatedBy());
                    }
                    if (bLCustomerPvc.getDateCreated() != null) {
                        existingBLCustomerPvc.setDateCreated(bLCustomerPvc.getDateCreated());
                    }
                    if (bLCustomerPvc.getAuthoriseBy() != null) {
                        existingBLCustomerPvc.setAuthoriseBy(bLCustomerPvc.getAuthoriseBy());
                    }
                    if (bLCustomerPvc.getDateAuthorise() != null) {
                        existingBLCustomerPvc.setDateAuthorise(bLCustomerPvc.getDateAuthorise());
                    }
                    if (bLCustomerPvc.getCreatedBy() != null) {
                        existingBLCustomerPvc.setCreatedBy(bLCustomerPvc.getCreatedBy());
                    }
                    if (bLCustomerPvc.getDateCreated() != null) {
                        existingBLCustomerPvc.setDateCreated(bLCustomerPvc.getDateCreated());
                    }

                    return existingBLCustomerPvc;
                }
            )
            .map(bLCustomerPvcRepository::save);
    }

    /**
     * Get all the bLCustomerPvcs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BLCustomerPvc> findAll(Pageable pageable) {
        log.debug("Request to get all BLCustomerPvcs");
        return bLCustomerPvcRepository.findAll(pageable);
    }

    /**
     * Get one bLCustomerPvc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BLCustomerPvc> findOne(Long id) {
        log.debug("Request to get BLCustomerPvc : {}", id);
        return bLCustomerPvcRepository.findById(id);
    }

    /**
     * Delete the bLCustomerPvc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BLCustomerPvc : {}", id);
        bLCustomerPvcRepository.deleteById(id);
    }
}
