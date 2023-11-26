package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.BLSourceData;
import vn.com.pvcombank.repository.BLSourceDataRepository;

/**
 * Service Implementation for managing {@link BLSourceData}.
 */
@Service
@Transactional
public class BLSourceDataService {

    private final Logger log = LoggerFactory.getLogger(BLSourceDataService.class);

    private final BLSourceDataRepository bLMappingParamRepository;

    public BLSourceDataService(BLSourceDataRepository bLMappingParamRepository) {
        this.bLMappingParamRepository = bLMappingParamRepository;
    }

    /**
     * Save a bLMappingParam.
     *
     * @param bLMappingParam the entity to save.
     * @return the persisted entity.
     */
    public BLSourceData save(BLSourceData bLMappingParam) {
        log.debug("Request to save BLMappingParam : {}", bLMappingParam);
        return bLMappingParamRepository.save(bLMappingParam);
    }

    /**
     * Partially update a bLMappingParam.
     *
     * @param bLMappingParam the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BLSourceData> partialUpdate(BLSourceData bLMappingParam) {
        log.debug("Request to partially update BLMappingParam : {}", bLMappingParam);

        return bLMappingParamRepository
            .findById(bLMappingParam.getId())
            .map(
                existingBLMappingParam -> {
                    if (bLMappingParam.getSourceName() != null) {
                        existingBLMappingParam.setSourceName(bLMappingParam.getSourceName());
                    }
                    if (bLMappingParam.getSourceFilePrefix() != null) {
                        existingBLMappingParam.setSourceFilePrefix(bLMappingParam.getSourceFilePrefix());
                    }
                    if (bLMappingParam.getSourceCols() != null) {
                        existingBLMappingParam.setSourceCols(bLMappingParam.getSourceCols());
                    }
                    if (bLMappingParam.getSourceRef() != null) {
                        existingBLMappingParam.setSourceRef(bLMappingParam.getSourceRef());
                    }
                    if (bLMappingParam.getRecordStatus() != null) {
                        existingBLMappingParam.setRecordStatus(bLMappingParam.getRecordStatus());
                    }
                    if (bLMappingParam.getCreatedBy() != null) {
                        existingBLMappingParam.setCreatedBy(bLMappingParam.getCreatedBy());
                    }
                    if (bLMappingParam.getDateCreated() != null) {
                        existingBLMappingParam.setDateCreated(bLMappingParam.getDateCreated());
                    }
                    if (bLMappingParam.getAuthoriseBy() != null) {
                        existingBLMappingParam.setAuthoriseBy(bLMappingParam.getAuthoriseBy());
                    }
                    if (bLMappingParam.getDateAuthorise() != null) {
                        existingBLMappingParam.setDateAuthorise(bLMappingParam.getDateAuthorise());
                    }

                    return existingBLMappingParam;
                }
            )
            .map(bLMappingParamRepository::save);
    }

    /**
     * Get all the bLMappingParams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BLSourceData> findAll(Pageable pageable) {
        log.debug("Request to get all BLMappingParams");
        return bLMappingParamRepository.findAll(pageable);
    }

    /**
     * Get one bLMappingParam by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<BLSourceData> findOne(Long id) {
        log.debug("Request to get BLMappingParam : {}", id);
        return bLMappingParamRepository.findById(id);
    }

    /**
     * Delete the bLMappingParam by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BLMappingParam : {}", id);
        bLMappingParamRepository.deleteById(id);
    }
}
