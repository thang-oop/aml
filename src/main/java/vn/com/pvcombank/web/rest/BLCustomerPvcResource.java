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
import vn.com.pvcombank.domain.BLCustomerPvc;
import vn.com.pvcombank.repository.BLCustomerPvcRepository;
import vn.com.pvcombank.service.BLCustomerPvcQueryService;
import vn.com.pvcombank.service.BLCustomerPvcService;
import vn.com.pvcombank.service.criteria.BLCustomerPvcCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLCustomerPvc}.
 */
@RestController
@RequestMapping("/api")
public class BLCustomerPvcResource {

    private final Logger log = LoggerFactory.getLogger(BLCustomerPvcResource.class);

    private static final String ENTITY_NAME = "bLCustomerPvc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLCustomerPvcService bLCustomerPvcService;

    private final BLCustomerPvcRepository bLCustomerPvcRepository;

    private final BLCustomerPvcQueryService bLCustomerPvcQueryService;

    public BLCustomerPvcResource(
        BLCustomerPvcService bLCustomerPvcService,
        BLCustomerPvcRepository bLCustomerPvcRepository,
        BLCustomerPvcQueryService bLCustomerPvcQueryService
    ) {
        this.bLCustomerPvcService = bLCustomerPvcService;
        this.bLCustomerPvcRepository = bLCustomerPvcRepository;
        this.bLCustomerPvcQueryService = bLCustomerPvcQueryService;
    }

    /**
     * {@code POST  /bl-customer-pvcs} : Create a new bLCustomerPvc.
     *
     * @param bLCustomerPvc the bLCustomerPvc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLCustomerPvc, or with status {@code 400 (Bad Request)} if the bLCustomerPvc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-customer-pvcs")
    public ResponseEntity<BLCustomerPvc> createBLCustomerPvc(@RequestBody BLCustomerPvc bLCustomerPvc) throws URISyntaxException {
        log.debug("REST request to save BLCustomerPvc : {}", bLCustomerPvc);
        if (bLCustomerPvc.getId() != null) {
            throw new BadRequestAlertException("A new bLCustomerPvc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLCustomerPvc result = bLCustomerPvcService.save(bLCustomerPvc);
        return ResponseEntity
            .created(new URI("/api/bl-customer-pvcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-customer-pvcs/:id} : Updates an existing bLCustomerPvc.
     *
     * @param id the id of the bLCustomerPvc to save.
     * @param bLCustomerPvc the bLCustomerPvc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCustomerPvc,
     * or with status {@code 400 (Bad Request)} if the bLCustomerPvc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLCustomerPvc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-customer-pvcs/{id}")
    public ResponseEntity<BLCustomerPvc> updateBLCustomerPvc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCustomerPvc bLCustomerPvc
    ) throws URISyntaxException {
        log.debug("REST request to update BLCustomerPvc : {}, {}", id, bLCustomerPvc);
        if (bLCustomerPvc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCustomerPvc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLCustomerPvcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLCustomerPvc result = bLCustomerPvcService.save(bLCustomerPvc);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCustomerPvc.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-customer-pvcs/:id} : Partial updates given fields of an existing bLCustomerPvc, field will ignore if it is null
     *
     * @param id the id of the bLCustomerPvc to save.
     * @param bLCustomerPvc the bLCustomerPvc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCustomerPvc,
     * or with status {@code 400 (Bad Request)} if the bLCustomerPvc is not valid,
     * or with status {@code 404 (Not Found)} if the bLCustomerPvc is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLCustomerPvc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-customer-pvcs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLCustomerPvc> partialUpdateBLCustomerPvc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCustomerPvc bLCustomerPvc
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLCustomerPvc partially : {}, {}", id, bLCustomerPvc);
        if (bLCustomerPvc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCustomerPvc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLCustomerPvcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLCustomerPvc> result = bLCustomerPvcService.partialUpdate(bLCustomerPvc);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCustomerPvc.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-customer-pvcs} : get all the bLCustomerPvcs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLCustomerPvcs in body.
     */
    @GetMapping("/bl-customer-pvcs")
    public ResponseEntity<List<BLCustomerPvc>> getAllBLCustomerPvcs(BLCustomerPvcCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BLCustomerPvcs by criteria: {}", criteria);
        Page<BLCustomerPvc> page = bLCustomerPvcQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bl-customer-pvcs/count} : count all the bLCustomerPvcs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bl-customer-pvcs/count")
    public ResponseEntity<Long> countBLCustomerPvcs(BLCustomerPvcCriteria criteria) {
        log.debug("REST request to count BLCustomerPvcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(bLCustomerPvcQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bl-customer-pvcs/:id} : get the "id" bLCustomerPvc.
     *
     * @param id the id of the bLCustomerPvc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLCustomerPvc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-customer-pvcs/{id}")
    public ResponseEntity<BLCustomerPvc> getBLCustomerPvc(@PathVariable Long id) {
        log.debug("REST request to get BLCustomerPvc : {}", id);
        Optional<BLCustomerPvc> bLCustomerPvc = bLCustomerPvcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bLCustomerPvc);
    }

    /**
     * {@code DELETE  /bl-customer-pvcs/:id} : delete the "id" bLCustomerPvc.
     *
     * @param id the id of the bLCustomerPvc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-customer-pvcs/{id}")
    public ResponseEntity<Void> deleteBLCustomerPvc(@PathVariable Long id) {
        log.debug("REST request to delete BLCustomerPvc : {}", id);
        bLCustomerPvcService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
