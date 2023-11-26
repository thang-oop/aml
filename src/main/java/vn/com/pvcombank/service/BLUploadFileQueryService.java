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
import vn.com.pvcombank.domain.BLUploadFile;
import vn.com.pvcombank.repository.BLUploadFileRepository;
import vn.com.pvcombank.service.criteria.BLUploadFileCriteria;

/**
 * Service for executing complex queries for {@link BLUploadFile} entities in the database.
 * The main input is a {@link BLUploadFileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BLUploadFile} or a {@link Page} of {@link BLUploadFile} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BLUploadFileQueryService extends QueryService<BLUploadFile> {

    private final Logger log = LoggerFactory.getLogger(BLUploadFileQueryService.class);

    private final BLUploadFileRepository bLUploadFileRepository;

    public BLUploadFileQueryService(BLUploadFileRepository bLUploadFileRepository) {
        this.bLUploadFileRepository = bLUploadFileRepository;
    }

    /**
     * Return a {@link List} of {@link BLUploadFile} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BLUploadFile> findByCriteria(BLUploadFileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BLUploadFile> specification = createSpecification(criteria);
        return bLUploadFileRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BLUploadFile} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BLUploadFile> findByCriteria(BLUploadFileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BLUploadFile> specification = createSpecification(criteria);
        return bLUploadFileRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BLUploadFileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BLUploadFile> specification = createSpecification(criteria);
        return bLUploadFileRepository.count(specification);
    }

    /**
     * Function to convert {@link BLUploadFileCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BLUploadFile> createSpecification(BLUploadFileCriteria criteria) {
        Specification<BLUploadFile> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BLUploadFile_.id));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), BLUploadFile_.systemFileName));
            }
            if (criteria.getSystemFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSystemFileName(), BLUploadFile_.systemFileName));
            }

            if (criteria.getTagetCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTagetCompany(), BLUploadFile_.tagetCompany));
            }
            if (criteria.getValidate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValidate(), BLUploadFile_.validate));
            }
            if (criteria.getServiceStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceStatus(), BLUploadFile_.serviceStatus));
            }
            if (criteria.getFileSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFileSize(), BLUploadFile_.systemFileSize));
            }
            if (criteria.getRecordStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecordStatus(), BLUploadFile_.recordStatus));
            }
            if (criteria.getUploadBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUploadBy(), BLUploadFile_.uploadBy));
            }
            if (criteria.getDateUpload() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateUpload(), BLUploadFile_.dateUpload));
            }
            if (criteria.getAuthoriseBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthoriseBy(), BLUploadFile_.authoriseBy));
            }
            if (criteria.getDateAuthorise() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateAuthorise(), BLUploadFile_.dateAuthorise));
            }
        }
        return specification;
    }
}
