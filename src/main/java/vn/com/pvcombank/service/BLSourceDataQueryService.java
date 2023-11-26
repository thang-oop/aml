package vn.com.pvcombank.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.com.pvcombank.domain.*; // for static metamodels
import vn.com.pvcombank.domain.BLSourceData;
import vn.com.pvcombank.repository.BLSourceDataRepository;
import vn.com.pvcombank.service.criteria.BLSourceDataCriteria;

/**
 * Service for executing complex queries for {@link BLSourceData} entities in the database.
 * The main input is a {@link BLSourceDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BLSourceData} or a {@link Page} of {@link BLSourceData} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BLSourceDataQueryService extends QueryService<BLSourceData> {

    private final Logger log = LoggerFactory.getLogger(BLSourceDataQueryService.class);

    private final BLSourceDataRepository bLMappingParamRepository;

    public BLSourceDataQueryService(BLSourceDataRepository bLMappingParamRepository) {
        this.bLMappingParamRepository = bLMappingParamRepository;
    }

    /**
     * Return a {@link List} of {@link BLSourceData} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BLSourceData> findByCriteria(BLSourceDataCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BLSourceData> specification = createSpecification(criteria);
        return bLMappingParamRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BLSourceData} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BLSourceData> findByCriteria(BLSourceDataCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BLSourceData> specification = createSpecification(criteria);
        return bLMappingParamRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BLSourceDataCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BLSourceData> specification = createSpecification(criteria);
        return bLMappingParamRepository.count(specification);
    }

    /**
     * Function to convert {@link BLSourceDataCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BLSourceData> createSpecification(BLSourceDataCriteria criteria) {
        Specification<BLSourceData> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BLSourceData_.id));
            }
            if (criteria.getSourceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceName(), BLSourceData_.sourceName));
            }
            if (criteria.getSourceFilePrefix() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceFilePrefix(), BLSourceData_.sourceFilePrefix));
            }
            if (criteria.getSourceCols() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceCols(), BLSourceData_.sourceCols));
            }
            if (criteria.getSourceRef() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceRef(), BLSourceData_.sourceRef));
            }
            if (criteria.getRecordStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecordStatus(), BLSourceData_.recordStatus));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), BLSourceData_.createdBy));
            }
            if (criteria.getDateCreated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateCreated(), BLSourceData_.dateCreated));
            }
            if (criteria.getAuthoriseBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthoriseBy(), BLSourceData_.authoriseBy));
            }
            if (criteria.getDateAuthorise() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateAuthorise(), BLSourceData_.dateAuthorise));
            }
        }
        return specification;
    }
}
