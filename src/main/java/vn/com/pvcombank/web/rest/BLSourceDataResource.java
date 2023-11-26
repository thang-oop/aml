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
import vn.com.pvcombank.domain.BLSourceData;
import vn.com.pvcombank.repository.BLSourceDataRepository;
import vn.com.pvcombank.service.BLSourceDataQueryService;
import vn.com.pvcombank.service.BLSourceDataService;
import vn.com.pvcombank.service.criteria.BLSourceDataCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLSourceData}.
 */
@RestController
@RequestMapping("/api")
public class BLSourceDataResource {

    private final Logger log = LoggerFactory.getLogger(BLSourceDataResource.class);

    private static final String ENTITY_NAME = "bLMappingParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLSourceDataService bLMappingParamService;

    private final BLSourceDataRepository bLMappingParamRepository;

    private final BLSourceDataQueryService BLSourceDataQueryService;

    public BLSourceDataResource(
        BLSourceDataService bLMappingParamService,
        BLSourceDataRepository bLMappingParamRepository,
        BLSourceDataQueryService BLSourceDataQueryService
    ) {
        this.bLMappingParamService = bLMappingParamService;
        this.bLMappingParamRepository = bLMappingParamRepository;
        this.BLSourceDataQueryService = BLSourceDataQueryService;
    }

    /**
     * {@code POST  /bl-mapping-params} : Create a new bLMappingParam.
     *
     * @param bLMappingParam the bLMappingParam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bLMappingParam, or with status {@code 400 (Bad Request)} if the bLMappingParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-mapping-params")
    public ResponseEntity<BLSourceData> createBLMappingParam(@RequestBody BLSourceData bLMappingParam) throws URISyntaxException {
        log.debug("REST request to save BLMappingParam : {}", bLMappingParam);
        if (bLMappingParam.getId() != null) {
            throw new BadRequestAlertException("A new bLMappingParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLSourceData result = bLMappingParamService.save(bLMappingParam);
        return ResponseEntity
            .created(new URI("/api/bl-mapping-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-mapping-params/:id} : Updates an existing bLMappingParam.
     *
     * @param id the id of the bLMappingParam to save.
     * @param bLMappingParam the bLMappingParam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLMappingParam,
     * or with status {@code 400 (Bad Request)} if the bLMappingParam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bLMappingParam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-mapping-params/{id}")
    public ResponseEntity<BLSourceData> updateBLMappingParam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLSourceData bLMappingParam
    ) throws URISyntaxException {
        log.debug("REST request to update BLMappingParam : {}, {}", id, bLMappingParam);
        if (bLMappingParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLMappingParam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLMappingParamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLSourceData result = bLMappingParamService.save(bLMappingParam);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLMappingParam.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-mapping-params/:id} : Partial updates given fields of an existing bLMappingParam, field will ignore if it is null
     *
     * @param id the id of the bLMappingParam to save.
     * @param bLMappingParam the bLMappingParam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bLMappingParam,
     * or with status {@code 400 (Bad Request)} if the bLMappingParam is not valid,
     * or with status {@code 404 (Not Found)} if the bLMappingParam is not found,
     * or with status {@code 500 (Internal Server Error)} if the bLMappingParam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-mapping-params/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLSourceData> partialUpdateBLMappingParam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLSourceData bLMappingParam
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLMappingParam partially : {}, {}", id, bLMappingParam);
        if (bLMappingParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLMappingParam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLMappingParamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLSourceData> result = bLMappingParamService.partialUpdate(bLMappingParam);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLMappingParam.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-mapping-params} : get all the bLMappingParams.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bLMappingParams in body.
     */
    @GetMapping("/bl-mapping-params")
    public ResponseEntity<List<BLSourceData>> getAllBLMappingParams(BLSourceDataCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BLMappingParams by criteria: {}", criteria);
        Page<BLSourceData> page = BLSourceDataQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bl-mapping-params/count} : count all the bLMappingParams.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bl-mapping-params/count")
    public ResponseEntity<Long> countBLMappingParams(BLSourceDataCriteria criteria) {
        log.debug("REST request to count BLMappingParams by criteria: {}", criteria);
        return ResponseEntity.ok().body(BLSourceDataQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bl-mapping-params/:id} : get the "id" bLMappingParam.
     *
     * @param id the id of the bLMappingParam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bLMappingParam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-mapping-params/{id}")
    public ResponseEntity<BLSourceData> getBLMappingParam(@PathVariable Long id) {
        log.debug("REST request to get BLMappingParam : {}", id);
        Optional<BLSourceData> bLMappingParam = bLMappingParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bLMappingParam);
    }

    /**
     * {@code DELETE  /bl-mapping-params/:id} : delete the "id" bLMappingParam.
     *
     * @param id the id of the bLMappingParam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-mapping-params/{id}")
    public ResponseEntity<Void> deleteBLMappingParam(@PathVariable Long id) {
        log.debug("REST request to delete BLMappingParam : {}", id);
        bLMappingParamService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
