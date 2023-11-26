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
import vn.com.pvcombank.domain.JhiUserAuthority;
import vn.com.pvcombank.repository.JhiUserAuthorityRepository;
import vn.com.pvcombank.service.JhiUserAuthorityQueryService;
import vn.com.pvcombank.service.JhiUserAuthorityService;
import vn.com.pvcombank.service.criteria.JhiUserAuthorityCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.JhiUserAuthority}.
 */
@RestController
@RequestMapping("/api")
public class JhiUserAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(JhiUserAuthorityResource.class);

    private static final String ENTITY_NAME = "jhiUserAuthority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JhiUserAuthorityService jhiUserAuthorityService;

    private final JhiUserAuthorityRepository jhiUserAuthorityRepository;

    private final JhiUserAuthorityQueryService jhiUserAuthorityQueryService;

    public JhiUserAuthorityResource(
        JhiUserAuthorityService jhiUserAuthorityService,
        JhiUserAuthorityRepository jhiUserAuthorityRepository,
        JhiUserAuthorityQueryService jhiUserAuthorityQueryService
    ) {
        this.jhiUserAuthorityService = jhiUserAuthorityService;
        this.jhiUserAuthorityRepository = jhiUserAuthorityRepository;
        this.jhiUserAuthorityQueryService = jhiUserAuthorityQueryService;
    }

    /**
     * {@code POST  /jhi-user-authorities} : Create a new jhiUserAuthority.
     *
     * @param jhiUserAuthority the jhiUserAuthority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jhiUserAuthority, or with status {@code 400 (Bad Request)} if the jhiUserAuthority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jhi-user-authorities")
    public ResponseEntity<JhiUserAuthority> createJhiUserAuthority(@RequestBody JhiUserAuthority jhiUserAuthority)
        throws URISyntaxException {
        log.debug("REST request to save JhiUserAuthority : {}", jhiUserAuthority);
        if (jhiUserAuthority.getId() != null) {
            throw new BadRequestAlertException("A new jhiUserAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JhiUserAuthority result = jhiUserAuthorityService.save(jhiUserAuthority);
        return ResponseEntity
            .created(new URI("/api/jhi-user-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jhi-user-authorities/:id} : Updates an existing jhiUserAuthority.
     *
     * @param id the id of the jhiUserAuthority to save.
     * @param jhiUserAuthority the jhiUserAuthority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jhiUserAuthority,
     * or with status {@code 400 (Bad Request)} if the jhiUserAuthority is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jhiUserAuthority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jhi-user-authorities/{id}")
    public ResponseEntity<JhiUserAuthority> updateJhiUserAuthority(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JhiUserAuthority jhiUserAuthority
    ) throws URISyntaxException {
        log.debug("REST request to update JhiUserAuthority : {}, {}", id, jhiUserAuthority);
        if (jhiUserAuthority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jhiUserAuthority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jhiUserAuthorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JhiUserAuthority result = jhiUserAuthorityService.save(jhiUserAuthority);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jhiUserAuthority.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jhi-user-authorities/:id} : Partial updates given fields of an existing jhiUserAuthority, field will ignore if it is null
     *
     * @param id the id of the jhiUserAuthority to save.
     * @param jhiUserAuthority the jhiUserAuthority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jhiUserAuthority,
     * or with status {@code 400 (Bad Request)} if the jhiUserAuthority is not valid,
     * or with status {@code 404 (Not Found)} if the jhiUserAuthority is not found,
     * or with status {@code 500 (Internal Server Error)} if the jhiUserAuthority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jhi-user-authorities/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JhiUserAuthority> partialUpdateJhiUserAuthority(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JhiUserAuthority jhiUserAuthority
    ) throws URISyntaxException {
        log.debug("REST request to partial update JhiUserAuthority partially : {}, {}", id, jhiUserAuthority);
        if (jhiUserAuthority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jhiUserAuthority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jhiUserAuthorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JhiUserAuthority> result = jhiUserAuthorityService.partialUpdate(jhiUserAuthority);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jhiUserAuthority.getId().toString())
        );
    }

    /**
     * {@code GET  /jhi-user-authorities} : get all the jhiUserAuthorities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jhiUserAuthorities in body.
     */
    @GetMapping("/jhi-user-authorities")
    public ResponseEntity<List<JhiUserAuthority>> getAllJhiUserAuthorities(JhiUserAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JhiUserAuthorities by criteria: {}", criteria);
        Page<JhiUserAuthority> page = jhiUserAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jhi-user-authorities/count} : count all the jhiUserAuthorities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/jhi-user-authorities/count")
    public ResponseEntity<Long> countJhiUserAuthorities(JhiUserAuthorityCriteria criteria) {
        log.debug("REST request to count JhiUserAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(jhiUserAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /jhi-user-authorities/:id} : get the "id" jhiUserAuthority.
     *
     * @param id the id of the jhiUserAuthority to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jhiUserAuthority, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jhi-user-authorities/{id}")
    public ResponseEntity<JhiUserAuthority> getJhiUserAuthority(@PathVariable Long id) {
        log.debug("REST request to get JhiUserAuthority : {}", id);
        Optional<JhiUserAuthority> jhiUserAuthority = jhiUserAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jhiUserAuthority);
    }

    /**
     * {@code DELETE  /jhi-user-authorities/:id} : delete the "id" jhiUserAuthority.
     *
     * @param id the id of the jhiUserAuthority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jhi-user-authorities/{id}")
    public ResponseEntity<Void> deleteJhiUserAuthority(@PathVariable Long id) {
        log.debug("REST request to delete JhiUserAuthority : {}", id);
        jhiUserAuthorityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
