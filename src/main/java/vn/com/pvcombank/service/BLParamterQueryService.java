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
import vn.com.pvcombank.domain.BLParamter;
import vn.com.pvcombank.repository.BLParamterRepository;
import vn.com.pvcombank.service.criteria.BLParamterCriteria;

/**
 * Service for executing complex queries for {@link BLParamter} entities in the database.
 * The main input is a {@link BLParamterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BLParamter} or a {@link Page} of {@link BLParamter} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BLParamterQueryService extends QueryService<BLParamter> {

    private final Logger log = LoggerFactory.getLogger(BLParamterQueryService.class);

    private final BLParamterRepository bLParamterRepository;

    public BLParamterQueryService(BLParamterRepository bLParamterRepository) {
        this.bLParamterRepository = bLParamterRepository;
    }

    /**
     * Return a {@link List} of {@link BLParamter} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BLParamter> findByCriteria(BLParamterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BLParamter> specification = createSpecification(criteria);
        return bLParamterRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BLParamter} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BLParamter> findByCriteria(BLParamterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BLParamter> specification = createSpecification(criteria);
        return bLParamterRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BLParamterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BLParamter> specification = createSpecification(criteria);
        return bLParamterRepository.count(specification);
    }

    /**
     * Function to convert {@link BLParamterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BLParamter> createSpecification(BLParamterCriteria criteria) {
        Specification<BLParamter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BLParamter_.id));
            }
            if (criteria.getKeyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKeyId(), BLParamter_.keyId));
            }
            if (criteria.getKeyValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKeyValue(), BLParamter_.keyValue));
            }
            if (criteria.getRecordStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecordStatus(), BLParamter_.recordStatus));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), BLParamter_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), BLParamter_.createdBy));
            }
            if (criteria.getDateCreated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateCreated(), BLParamter_.dateCreated));
            }
        }
        return specification;
    }
}
