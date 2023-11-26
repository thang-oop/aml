package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.BLCondition;
import vn.com.pvcombank.repository.BLConditionRepository;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLCondition}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BLConditionResource {

    private final Logger log = LoggerFactory.getLogger(BLConditionResource.class);

    private static final String ENTITY_NAME = "bLCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLConditionRepository bLConditionRepository;

    public BLConditionResource(BLConditionRepository bLConditionRepository) {
        this.bLConditionRepository = bLConditionRepository;
    }

    /**
     * {@code POST  /bl-conditions} : Create a new bLCondition.
     *
     * @param bLCondition the bLCondition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLCondition, or with status {@code 400 (Bad Request)} if the bLCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-conditions")
    public ResponseEntity<BLCondition> createBLCondition(@RequestBody BLCondition bLCondition) throws URISyntaxException {
        log.debug("REST request to save BLCondition : {}", bLCondition);
        if (bLCondition.getId() != null) {
            throw new BadRequestAlertException("A new bLCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLCondition result = bLConditionRepository.save(bLCondition);
        return ResponseEntity
            .created(new URI("/api/bl-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-conditions/:id} : Updates an existing bLCondition.
     *
     * @param id the id of the bLCondition to save.
     * @param bLCondition the bLCondition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCondition,
     * or with status {@code 400 (Bad Request)} if the bLCondition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLCondition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-conditions/{id}")
    public ResponseEntity<BLCondition> updateBLCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCondition bLCondition
    ) throws URISyntaxException {
        log.debug("REST request to update BLCondition : {}, {}", id, bLCondition);
        if (bLCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCondition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLCondition result = bLConditionRepository.save(bLCondition);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCondition.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-conditions/:id} : Partial updates given fields of an existing bLCondition, field will ignore if it is null
     *
     * @param id the id of the bLCondition to save.
     * @param bLCondition the bLCondition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCondition,
     * or with status {@code 400 (Bad Request)} if the bLCondition is not valid,
     * or with status {@code 404 (Not Found)} if the bLCondition is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLCondition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-conditions/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLCondition> partialUpdateBLCondition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCondition bLCondition
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLCondition partially : {}, {}", id, bLCondition);
        if (bLCondition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCondition.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLConditionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLCondition> result = bLConditionRepository
            .findById(bLCondition.getId())
            .map(
                existingBLCondition -> {
                    if (bLCondition.getName() != null) {
                        existingBLCondition.setName(bLCondition.getName());
                    }
                    if (bLCondition.getDescription() != null) {
                        existingBLCondition.setDescription(bLCondition.getDescription());
                    }
                    if (bLCondition.getBlackListFlds() != null) {
                        existingBLCondition.setBlackListFlds(bLCondition.getBlackListFlds());
                    }
                    if (bLCondition.getCustomerFlds() != null) {
                        existingBLCondition.setCustomerFlds(bLCondition.getCustomerFlds());
                    }
                    if (bLCondition.getWeightPoint() != null) {
                        existingBLCondition.setWeightPoint(bLCondition.getWeightPoint());
                    }
                    if (bLCondition.getRuleId() != null) {
                        existingBLCondition.setRuleId(bLCondition.getRuleId());
                    }
                    if (bLCondition.getCreatedBy() != null) {
                        existingBLCondition.setCreatedBy(bLCondition.getCreatedBy());
                    }
                    if (bLCondition.getDateCreated() != null) {
                        existingBLCondition.setDateCreated(bLCondition.getDateCreated());
                    }
                    if (bLCondition.getAuthoriseBy() != null) {
                        existingBLCondition.setAuthoriseBy(bLCondition.getAuthoriseBy());
                    }
                    if (bLCondition.getDateAuthorise() != null) {
                        existingBLCondition.setDateAuthorise(bLCondition.getDateAuthorise());
                    }

                    return existingBLCondition;
                }
            )
            .map(bLConditionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCondition.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-conditions} : get all the bLConditions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLConditions in body.
     */
    @GetMapping("/bl-conditions")
    public List<BLCondition> getAllBLConditions() {
        log.debug("REST request to get all BLConditions");
        return bLConditionRepository.findAll();
    }

    /**
     * {@code GET  /bl-conditions/:id} : get the "id" bLCondition.
     *
     * @param id the id of the bLCondition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLCondition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-conditions/{id}")
    public ResponseEntity<BLCondition> getBLCondition(@PathVariable Long id) {
        log.debug("REST request to get BLCondition : {}", id);
        Optional<BLCondition> bLCondition = bLConditionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bLCondition);
    }

    /**
     * {@code DELETE  /bl-conditions/:id} : delete the "id" bLCondition.
     *
     * @param id the id of the bLCondition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-conditions/{id}")
    public ResponseEntity<Void> deleteBLCondition(@PathVariable Long id) {
        log.debug("REST request to delete BLCondition : {}", id);
        bLConditionRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
