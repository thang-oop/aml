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
import vn.com.pvcombank.domain.BLRule;
import vn.com.pvcombank.repository.BLRuleRepository;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLRule}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BLRuleResource {

    private final Logger log = LoggerFactory.getLogger(BLRuleResource.class);

    private static final String ENTITY_NAME = "bLRule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLRuleRepository bLRuleRepository;

    public BLRuleResource(BLRuleRepository bLRuleRepository) {
        this.bLRuleRepository = bLRuleRepository;
    }

    /**
     * {@code POST  /bl-rules} : Create a new bLRule.
     *
     * @param bLRule the bLRule to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLRule, or with status {@code 400 (Bad Request)} if the bLRule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-rules")
    public ResponseEntity<BLRule> createBLRule(@RequestBody BLRule bLRule) throws URISyntaxException {
        log.debug("REST request to save BLRule : {}", bLRule);
        if (bLRule.getId() != null) {
            throw new BadRequestAlertException("A new bLRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLRule result = bLRuleRepository.save(bLRule);
        return ResponseEntity
            .created(new URI("/api/bl-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-rules/:id} : Updates an existing bLRule.
     *
     * @param id the id of the bLRule to save.
     * @param bLRule the bLRule to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLRule,
     * or with status {@code 400 (Bad Request)} if the bLRule is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLRule couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-rules/{id}")
    public ResponseEntity<BLRule> updateBLRule(@PathVariable(value = "id", required = false) final Long id, @RequestBody BLRule bLRule)
        throws URISyntaxException {
        log.debug("REST request to update BLRule : {}, {}", id, bLRule);
        if (bLRule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLRule.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLRule result = bLRuleRepository.save(bLRule);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLRule.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-rules/:id} : Partial updates given fields of an existing bLRule, field will ignore if it is null
     *
     * @param id the id of the bLRule to save.
     * @param bLRule the bLRule to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLRule,
     * or with status {@code 400 (Bad Request)} if the bLRule is not valid,
     * or with status {@code 404 (Not Found)} if the bLRule is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLRule couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-rules/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLRule> partialUpdateBLRule(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLRule bLRule
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLRule partially : {}, {}", id, bLRule);
        if (bLRule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLRule.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLRule> result = bLRuleRepository
            .findById(bLRule.getId())
            .map(
                existingBLRule -> {
                    if (bLRule.getName() != null) {
                        existingBLRule.setName(bLRule.getName());
                    }
                    if (bLRule.getDescription() != null) {
                        existingBLRule.setDescription(bLRule.getDescription());
                    }
                    if (bLRule.getSourceIds() != null) {
                        existingBLRule.setSourceIds(bLRule.getSourceIds());
                    }
                    if (bLRule.getCustomerType() != null) {
                        existingBLRule.setCustomerType(bLRule.getCustomerType());
                    }
                    if (bLRule.getScoreMinimum() != null) {
                        existingBLRule.setScoreMinimum(bLRule.getScoreMinimum());
                    }
                    if (bLRule.getCreatedBy() != null) {
                        existingBLRule.setCreatedBy(bLRule.getCreatedBy());
                    }
                    if (bLRule.getDateCreated() != null) {
                        existingBLRule.setDateCreated(bLRule.getDateCreated());
                    }
                    if (bLRule.getAuthoriseBy() != null) {
                        existingBLRule.setAuthoriseBy(bLRule.getAuthoriseBy());
                    }
                    if (bLRule.getDateAuthorise() != null) {
                        existingBLRule.setDateAuthorise(bLRule.getDateAuthorise());
                    }

                    return existingBLRule;
                }
            )
            .map(bLRuleRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLRule.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-rules} : get all the bLRules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLRules in body.
     */
    @GetMapping("/bl-rules")
    public List<BLRule> getAllBLRules() {
        log.debug("REST request to get all BLRules");
        return bLRuleRepository.findAll();
    }

    /**
     * {@code GET  /bl-rules/:id} : get the "id" bLRule.
     *
     * @param id the id of the bLRule to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLRule, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-rules/{id}")
    public ResponseEntity<BLRule> getBLRule(@PathVariable Long id) {
        log.debug("REST request to get BLRule : {}", id);
        Optional<BLRule> bLRule = bLRuleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bLRule);
    }

    /**
     * {@code DELETE  /bl-rules/:id} : delete the "id" bLRule.
     *
     * @param id the id of the bLRule to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-rules/{id}")
    public ResponseEntity<Void> deleteBLRule(@PathVariable Long id) {
        log.debug("REST request to delete BLRule : {}", id);
        bLRuleRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
