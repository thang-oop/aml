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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.BLCustomer;
import vn.com.pvcombank.repository.BLCustomerRepository;
import vn.com.pvcombank.service.BLCustomerQueryService;
import vn.com.pvcombank.service.BLCustomerService;
import vn.com.pvcombank.service.criteria.BLCustomerCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLCustomer}.
 */
@RestController
@RequestMapping("/api")
public class BLCustomerResource {

    private final Logger log = LoggerFactory.getLogger(BLCustomerResource.class);

    private static final String ENTITY_NAME = "bLCustomer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLCustomerService bLCustomerService;

    private final BLCustomerRepository bLCustomerRepository;

    private final BLCustomerQueryService bLCustomerQueryService;

    public BLCustomerResource(
        BLCustomerService bLCustomerService,
        BLCustomerRepository bLCustomerRepository,
        BLCustomerQueryService bLCustomerQueryService
    ) {
        this.bLCustomerService = bLCustomerService;
        this.bLCustomerRepository = bLCustomerRepository;
        this.bLCustomerQueryService = bLCustomerQueryService;
    }

    /**
     * {@code POST  /bl-customers} : Create a new bLCustomer.
     *
     * @param bLCustomer the bLCustomer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLCustomer, or with status {@code 400 (Bad Request)} if the bLCustomer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-customers")
    public ResponseEntity<BLCustomer> createBLCustomer(@RequestBody BLCustomer bLCustomer) throws URISyntaxException {
        log.debug("REST request to save BLCustomer : {}", bLCustomer);
        if (bLCustomer.getId() != null) {
            throw new BadRequestAlertException("A new bLCustomer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLCustomer result = bLCustomerService.save(bLCustomer);
        return ResponseEntity
            .created(new URI("/api/bl-customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-customers/:id} : Updates an existing bLCustomer.
     *
     * @param id the id of the bLCustomer to save.
     * @param bLCustomer the bLCustomer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCustomer,
     * or with status {@code 400 (Bad Request)} if the bLCustomer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLCustomer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-customers/{id}")
    public ResponseEntity<BLCustomer> updateBLCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCustomer bLCustomer
    ) throws URISyntaxException {
        log.debug("REST request to update BLCustomer : {}, {}", id, bLCustomer);
        if (bLCustomer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCustomer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLCustomerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLCustomer result = bLCustomerService.save(bLCustomer);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCustomer.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-customers/:id} : Partial updates given fields of an existing bLCustomer, field will ignore if it is null
     *
     * @param id the id of the bLCustomer to save.
     * @param bLCustomer the bLCustomer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLCustomer,
     * or with status {@code 400 (Bad Request)} if the bLCustomer is not valid,
     * or with status {@code 404 (Not Found)} if the bLCustomer is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLCustomer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-customers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLCustomer> partialUpdateBLCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLCustomer bLCustomer
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLCustomer partially : {}, {}", id, bLCustomer);
        if (bLCustomer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLCustomer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLCustomerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLCustomer> result = bLCustomerService.partialUpdate(bLCustomer);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLCustomer.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-customers} : get all the bLCustomers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLCustomers in body.
     */
    @GetMapping("/bl-customers")
    public ResponseEntity<List<BLCustomer>> getAllBLCustomers(BLCustomerCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BLCustomers by criteria: {}", criteria);
        Page<BLCustomer> page = bLCustomerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bl-customers/count} : count all the bLCustomers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bl-customers/count")
    public ResponseEntity<Long> countBLCustomers(BLCustomerCriteria criteria) {
        log.debug("REST request to count BLCustomers by criteria: {}", criteria);
        return ResponseEntity.ok().body(bLCustomerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bl-customers/:id} : get the "id" bLCustomer.
     *
     * @param id the id of the bLCustomer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLCustomer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-customers/{id}")
    public ResponseEntity<BLCustomer> getBLCustomer(@PathVariable Long id) {
        log.debug("REST request to get BLCustomer : {}", id);
        Optional<BLCustomer> bLCustomer = bLCustomerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bLCustomer);
    }

    /**
     * {@code DELETE  /bl-customers/:id} : delete the "id" bLCustomer.
     *
     * @param id the id of the bLCustomer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-customers/{id}")
    public ResponseEntity<Void> deleteBLCustomer(@PathVariable Long id) {
        log.debug("REST request to delete BLCustomer : {}", id);
        bLCustomerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
