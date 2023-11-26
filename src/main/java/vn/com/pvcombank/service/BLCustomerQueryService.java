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
import vn.com.pvcombank.domain.BLCustomer;
import vn.com.pvcombank.repository.BLCustomerRepository;
import vn.com.pvcombank.service.criteria.BLCustomerCriteria;

/**
 * Service for executing complex queries for {@link BLCustomer} entities in the database.
 * The main input is a {@link BLCustomerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BLCustomer} or a {@link Page} of {@link BLCustomer} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BLCustomerQueryService extends QueryService<BLCustomer> {

    private final Logger log = LoggerFactory.getLogger(BLCustomerQueryService.class);

    private final BLCustomerRepository bLCustomerRepository;

    public BLCustomerQueryService(BLCustomerRepository bLCustomerRepository) {
        this.bLCustomerRepository = bLCustomerRepository;
    }

    /**
     * Return a {@link List} of {@link BLCustomer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BLCustomer> findByCriteria(BLCustomerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BLCustomer> specification = createSpecification(criteria);
        return bLCustomerRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BLCustomer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BLCustomer> findByCriteria(BLCustomerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BLCustomer> specification = createSpecification(criteria);
        return bLCustomerRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BLCustomerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BLCustomer> specification = createSpecification(criteria);
        return bLCustomerRepository.count(specification);
    }

    /**
     * Function to convert {@link BLCustomerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BLCustomer> createSpecification(BLCustomerCriteria criteria) {
        Specification<BLCustomer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BLCustomer_.id));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), BLCustomer_.fullName));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), BLCustomer_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), BLCustomer_.lastName));
            }
            if (criteria.getOtherName1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOtherName1(), BLCustomer_.otherName1));
            }
            if (criteria.getOtherName2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOtherName2(), BLCustomer_.otherName2));
            }
            if (criteria.getOtherName3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOtherName3(), BLCustomer_.otherName3));
            }
            if (criteria.getPositionBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPositionBl(), BLCustomer_.positionBl));
            }
            if (criteria.getDateOfBirthBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfBirthBl(), BLCustomer_.dateOfBirthBl));
            }
            if (criteria.getCountryBl1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryBl1(), BLCustomer_.countryBl1));
            }
            if (criteria.getCountryBl2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountryBl2(), BLCustomer_.countryBl2));
            }
            if (criteria.getLegalIdTypeBl1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdTypeBl1(), BLCustomer_.legalIdTypeBl1));
            }
            if (criteria.getLegalIdNumber1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdNumber1(), BLCustomer_.legalIdNumber1));
            }
            if (criteria.getLegalIdTypeBl2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdTypeBl2(), BLCustomer_.legalIdTypeBl2));
            }
            if (criteria.getLegalIdNumber2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalIdNumber2(), BLCustomer_.legalIdNumber2));
            }
            if (criteria.getOtherInfLegal1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOtherInfLegal1(), BLCustomer_.otherInfLegal1));
            }
            if (criteria.getOtherInfLegal2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOtherInfLegal2(), BLCustomer_.otherInfLegal2));
            }
            if (criteria.getAddressBl1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressBl1(), BLCustomer_.addressBl1));
            }
            if (criteria.getAddressBl2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressBl2(), BLCustomer_.addressBl2));
            }
            if (criteria.getAddressNowBl1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressNowBl1(), BLCustomer_.addressNowBl1));
            }
            if (criteria.getAddressNowBl2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressNowBl2(), BLCustomer_.addressNowBl2));
            }
            if (criteria.getTypeBl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeBl(), BLCustomer_.typeBl));
            }
            if (criteria.getSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSource(), BLCustomer_.source));
            }
            if (criteria.getRecordStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecordStatus(), BLCustomer_.recordStatus));
            }
            if (criteria.getUploadFileId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUploadFileId(), BLCustomer_.uploadFileId));
            }
            if (criteria.getCoCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoCode(), BLCustomer_.coCode));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), BLCustomer_.createdBy));
            }
            if (criteria.getDateCreated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateCreated(), BLCustomer_.dateCreated));
            }
            if (criteria.getAuthoriseBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthoriseBy(), BLCustomer_.authoriseBy));
            }
            if (criteria.getDateAuthorise() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateAuthorise(), BLCustomer_.dateAuthorise));
            }
        }
        return specification;
    }
}
