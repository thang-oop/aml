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
import vn.com.pvcombank.domain.BLCustomerPvc;
import vn.com.pvcombank.repository.BLCustomerPvcRepository;
import vn.com.pvcombank.service.criteria.BLCustomerPvcCriteria;

/**
 * Service for executing complex queries for {@link BLCustomerPvc} entities in
 * the database.
 * The main input is a {@link BLCustomerPvcCriteria} which gets converted to
 * {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BLCustomerPvc} or a {@link Page} of
 * {@link BLCustomerPvc} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BLCustomerPvcQueryService extends QueryService<BLCustomerPvc> {

    private final Logger log = LoggerFactory.getLogger(BLCustomerPvcQueryService.class);

    private final BLCustomerPvcRepository bLCustomerPvcRepository;

    public BLCustomerPvcQueryService(BLCustomerPvcRepository bLCustomerPvcRepository) {
        this.bLCustomerPvcRepository = bLCustomerPvcRepository;
    }

    /**
     * Return a {@link List} of {@link BLCustomerPvc} which matches the criteria
     * from the database.
     *
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BLCustomerPvc> findByCriteria(BLCustomerPvcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BLCustomerPvc> specification = createSpecification(criteria);
        return bLCustomerPvcRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BLCustomerPvc} which matches the criteria
     * from the database.
     *
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BLCustomerPvc> findByCriteria(BLCustomerPvcCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BLCustomerPvc> specification = createSpecification(criteria);
        return bLCustomerPvcRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BLCustomerPvcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BLCustomerPvc> specification = createSpecification(criteria);
        return bLCustomerPvcRepository.count(specification);
    }

    /**
     * Function to convert {@link BLCustomerPvcCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BLCustomerPvc> createSpecification(BLCustomerPvcCriteria criteria) {
        Specification<BLCustomerPvc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BLCustomerPvc_.id));
            }
            if (criteria.getCif() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCif(), BLCustomerPvc_.cif));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), BLCustomerPvc_.fullName));
            }
            if (criteria.getDateOfBirth() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfBirth(), BLCustomerPvc_.dateOfBirth));
            }
            if (criteria.getLegalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalId(), BLCustomerPvc_.legalId));
            }
            if (criteria.getLegalType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalType(), BLCustomerPvc_.legalType));
            }
            if (criteria.getBranch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBranch(), BLCustomerPvc_.branch));
            }
            if (criteria.getBlCustomerId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBlCustomerId(), BLCustomerPvc_.blCustomerId));
            }
            if (criteria.getNameBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameBl(), BLCustomerPvc_.nameBl));
            }
            if (criteria.getDateOfBirthBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfBirthBl(), BLCustomerPvc_.dateOfBirthBl));
            }
            if (criteria.getLegalIdTypeBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdTypeBl(), BLCustomerPvc_.legalIdTypeBl));
            }
            if (criteria.getLegalIdNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdNumber(), BLCustomerPvc_.legalIdNumber));
            }
            if (criteria.getMatchAttr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatchAttr(), BLCustomerPvc_.matchAttr));
            }
            if (criteria.getValueAttr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValueAttr(), BLCustomerPvc_.valueAttr));
            }
            if (criteria.getWeightAttr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWeightAttr(), BLCustomerPvc_.weightAttr));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getScore(), BLCustomerPvc_.score));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), BLCustomerPvc_.status));
            }
            if (criteria.getRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemark(), BLCustomerPvc_.remark));
            }
            if (criteria.getRecordStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecordStatus(), BLCustomerPvc_.recordStatus));
            }
            if (criteria.getCoCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoCode(), BLCustomerPvc_.coCode));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), BLCustomerPvc_.createdBy));
            }
            if (criteria.getDateCreated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateCreated(), BLCustomerPvc_.dateCreated));
            }
            if (criteria.getAuthoriseBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthoriseBy(), BLCustomerPvc_.authoriseBy));
            }
            if (criteria.getDateAuthorise() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateAuthorise(), BLCustomerPvc_.dateAuthorise));
            }
        }
        return specification;
    }
}
