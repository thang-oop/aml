package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.BLCustomer;
import vn.com.pvcombank.repository.BLCustomerRepository;

/**
 * Service Implementation for managing {@link BLCustomer}.
 */
@Service
@Transactional
public class BLCustomerService {

    private final Logger log = LoggerFactory.getLogger(BLCustomerService.class);

    private final BLCustomerRepository bLCustomerRepository;

    public BLCustomerService(BLCustomerRepository bLCustomerRepository) {
        this.bLCustomerRepository = bLCustomerRepository;
    }

    /**
     * Save a bLCustomer.
     *
     * @param bLCustomer the entity to save.
     * @return the persisted entity.
     */
    public BLCustomer save(BLCustomer bLCustomer) {
        log.debug("Request to save BLCustomer : {}", bLCustomer);
        return bLCustomerRepository.save(bLCustomer);
    }

    /**
     * Partially update a bLCustomer.
     *
     * @param bLCustomer the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BLCustomer> partialUpdate(BLCustomer bLCustomer) {
        log.debug("Request to partially update BLCustomer : {}", bLCustomer);

        return bLCustomerRepository
            .findById(bLCustomer.getId())
            .map(
                existingBLCustomer -> {
                    if (bLCustomer.getFullName() != null) {
                        existingBLCustomer.setFullName(bLCustomer.getFullName());
                    }
                    if (bLCustomer.getFirstName() != null) {
                        existingBLCustomer.setFirstName(bLCustomer.getFirstName());
                    }
                    if (bLCustomer.getLastName() != null) {
                        existingBLCustomer.setLastName(bLCustomer.getLastName());
                    }
                    if (bLCustomer.getOtherName1() != null) {
                        existingBLCustomer.setOtherName1(bLCustomer.getOtherName1());
                    }
                    if (bLCustomer.getOtherName2() != null) {
                        existingBLCustomer.setOtherName2(bLCustomer.getOtherName2());
                    }
                    if (bLCustomer.getOtherName3() != null) {
                        existingBLCustomer.setOtherName3(bLCustomer.getOtherName3());
                    }
                    if (bLCustomer.getPositionBl() != null) {
                        existingBLCustomer.setPositionBl(bLCustomer.getPositionBl());
                    }
                    if (bLCustomer.getDateOfBirthBl() != null) {
                        existingBLCustomer.setDateOfBirthBl(bLCustomer.getDateOfBirthBl());
                    }
                    if (bLCustomer.getCountryBl1() != null) {
                        existingBLCustomer.setCountryBl1(bLCustomer.getCountryBl1());
                    }
                    if (bLCustomer.getCountryBl2() != null) {
                        existingBLCustomer.setCountryBl2(bLCustomer.getCountryBl2());
                    }
                    if (bLCustomer.getLegalIdTypeBl1() != null) {
                        existingBLCustomer.setLegalIdTypeBl1(bLCustomer.getLegalIdTypeBl1());
                    }
                    if (bLCustomer.getLegalIdNumber1() != null) {
                        existingBLCustomer.setLegalIdNumber1(bLCustomer.getLegalIdNumber1());
                    }
                    if (bLCustomer.getLegalIdTypeBl2() != null) {
                        existingBLCustomer.setLegalIdTypeBl2(bLCustomer.getLegalIdTypeBl2());
                    }
                    if (bLCustomer.getLegalIdNumber2() != null) {
                        existingBLCustomer.setLegalIdNumber2(bLCustomer.getLegalIdNumber2());
                    }
                    if (bLCustomer.getOtherInfLegal1() != null) {
                        existingBLCustomer.setOtherInfLegal1(bLCustomer.getOtherInfLegal1());
                    }
                    if (bLCustomer.getOtherInfLegal2() != null) {
                        existingBLCustomer.setOtherInfLegal2(bLCustomer.getOtherInfLegal2());
                    }
                    if (bLCustomer.getAddressBl1() != null) {
                        existingBLCustomer.setAddressBl1(bLCustomer.getAddressBl1());
                    }
                    if (bLCustomer.getAddressBl2() != null) {
                        existingBLCustomer.setAddressBl2(bLCustomer.getAddressBl2());
                    }
                    if (bLCustomer.getAddressNowBl1() != null) {
                        existingBLCustomer.setAddressNowBl1(bLCustomer.getAddressNowBl1());
                    }
                    if (bLCustomer.getAddressNowBl2() != null) {
                        existingBLCustomer.setAddressNowBl2(bLCustomer.getAddressNowBl2());
                    }
                    if (bLCustomer.getTypeBl() != null) {
                        existingBLCustomer.setTypeBl(bLCustomer.getTypeBl());
                    }
                    if (bLCustomer.getSource() != null) {
                        existingBLCustomer.setSource(bLCustomer.getSource());
                    }
                    if (bLCustomer.getRecordStatus() != null) {
                        existingBLCustomer.setRecordStatus(bLCustomer.getRecordStatus());
                    }
                    if (bLCustomer.getUploadFileId() != null) {
                        existingBLCustomer.setUploadFileId(bLCustomer.getUploadFileId());
                    }
                    if (bLCustomer.getCoCode() != null) {
                        existingBLCustomer.setCoCode(bLCustomer.getCoCode());
                    }
                    if (bLCustomer.getCreatedBy() != null) {
                        existingBLCustomer.setCreatedBy(bLCustomer.getCreatedBy());
                    }
                    if (bLCustomer.getDateCreated() != null) {
                        existingBLCustomer.setDateCreated(bLCustomer.getDateCreated());
                    }
                    if (bLCustomer.getAuthoriseBy() != null) {
                        existingBLCustomer.setAuthoriseBy(bLCustomer.getAuthoriseBy());
                    }
                    if (bLCustomer.getDateAuthorise() != null) {
                        existingBLCustomer.setDateAuthorise(bLCustomer.getDateAuthorise());
                    }
                    if (bLCustomer.getCreatedBy() != null) {
                        existingBLCustomer.setCreatedBy(bLCustomer.getCreatedBy());
                    }
                    if (bLCustomer.getDateCreated() != null) {
                        existingBLCustomer.setDateCreated(bLCustomer.getDateCreated());
                    }

                    return existingBLCustomer;
                }
            )
            .map(bLCustomerRepository::save);
    }

    /**
     * Get all the bLCustomers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BLCustomer> findAll(Pageable pageable) {
        log.debug("Request to get all BLCustomers");
        return bLCustomerRepository.findAll(pageable);
    }

    /**
     * Get one bLCustomer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BLCustomer> findOne(Long id) {
        log.debug("Request to get BLCustomer : {}", id);
        return bLCustomerRepository.findById(id);
    }

    /**
     * Delete the bLCustomer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BLCustomer : {}", id);
        bLCustomerRepository.deleteById(id);
    }
}
