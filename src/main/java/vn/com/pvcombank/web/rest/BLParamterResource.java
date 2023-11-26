package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.BLParamter;
import vn.com.pvcombank.repository.BLParamterRepository;
import vn.com.pvcombank.service.BLParamterQueryService;
import vn.com.pvcombank.service.BLParamterService;
import vn.com.pvcombank.service.criteria.BLParamterCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLParamter}.
 */
@RestController
@RequestMapping("/api")
public class BLParamterResource {

    private final Logger log = LoggerFactory.getLogger(BLParamterResource.class);

    private static final String ENTITY_NAME = "bLParamter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLParamterService bLParamterService;

    private final BLParamterRepository bLParamterRepository;

    private final BLParamterQueryService bLParamterQueryService;

    public BLParamterResource(
        BLParamterService bLParamterService,
        BLParamterRepository bLParamterRepository,
        BLParamterQueryService bLParamterQueryService
    ) {
        this.bLParamterService = bLParamterService;
        this.bLParamterRepository = bLParamterRepository;
        this.bLParamterQueryService = bLParamterQueryService;
    }

    /**
     * {@code POST  /bl-paramters} : Create a new bLParamter.
     *
     * @param bLParamter the bLParamter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLParamter, or with status {@code 400 (Bad Request)} if the bLParamter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-paramters")
    public ResponseEntity<BLParamter> createBLParamter(@RequestBody BLParamter bLParamter) throws URISyntaxException {
        log.debug("REST request to save BLParamter : {}", bLParamter);
        if (bLParamter.getId() != null) {
            throw new BadRequestAlertException("A new bLParamter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLParamter result = bLParamterService.save(bLParamter);
        return ResponseEntity
            .created(new URI("/api/bl-paramters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-paramters/:id} : Updates an existing bLParamter.
     *
     * @param id the id of the bLParamter to save.
     * @param bLParamter the bLParamter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLParamter,
     * or with status {@code 400 (Bad Request)} if the bLParamter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLParamter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-paramters/{id}")
    public ResponseEntity<BLParamter> updateBLParamter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLParamter bLParamter
    ) throws URISyntaxException {
        log.debug("REST request to update BLParamter : {}, {}", id, bLParamter);
        if (bLParamter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLParamter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLParamterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLParamter result = bLParamterService.save(bLParamter);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLParamter.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-paramters/:id} : Partial updates given fields of an existing bLParamter, field will ignore if it is null
     *
     * @param id the id of the bLParamter to save.
     * @param bLParamter the bLParamter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLParamter,
     * or with status {@code 400 (Bad Request)} if the bLParamter is not valid,
     * or with status {@code 404 (Not Found)} if the bLParamter is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLParamter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-paramters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLParamter> partialUpdateBLParamter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLParamter bLParamter
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLParamter partially : {}, {}", id, bLParamter);
        if (bLParamter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLParamter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLParamterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLParamter> result = bLParamterService.partialUpdate(bLParamter);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLParamter.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-paramters} : get all the bLParamters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLParamters in body.
     */
    @GetMapping("/bl-paramters")
    public ResponseEntity<List<BLParamter>> getAllBLParamters(BLParamterCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BLParamters by criteria: {}", criteria);
        Page<BLParamter> page = bLParamterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bl-paramters/count} : count all the bLParamters.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bl-paramters/count")
    public ResponseEntity<Long> countBLParamters(BLParamterCriteria criteria) {
        log.debug("REST request to count BLParamters by criteria: {}", criteria);
        return ResponseEntity.ok().body(bLParamterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bl-paramters/:id} : get the "id" bLParamter.
     *
     * @param id the id of the bLParamter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLParamter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-paramters/{id}")
    public ResponseEntity<BLParamter> getBLParamter(@PathVariable Long id) {
        log.debug("REST request to get BLParamter : {}", id);
        Optional<BLParamter> bLParamter = bLParamterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bLParamter);
    }

    /**
     * {@code DELETE  /bl-paramters/:id} : delete the "id" bLParamter.
     *
     * @param id the id of the bLParamter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-paramters/{id}")
    public ResponseEntity<Void> deleteBLParamter(@PathVariable Long id) {
        log.debug("REST request to delete BLParamter : {}", id);
        bLParamterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /bl-paramters/:id} : get the "id" bLParamter.
     *
     * @param id the id of the bLParamter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLParamter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-paramters/keyid/{keyId}")
    public ResponseEntity<BLParamter> getKeyValueParamter(@PathVariable String keyId) {
        log.debug("REST request to get BLParamter : {}", keyId);
        Optional<BLParamter> bLParamter = bLParamterService.findByKeyId(keyId);
        return ResponseUtil.wrapOrNotFound(bLParamter);
    }
}
