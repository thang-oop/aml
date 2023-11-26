package vn.com.pvcombank.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.logstash.logback.encoder.com.lmax.disruptor.AlertException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.BLCustomer;
import vn.com.pvcombank.domain.BLUploadFile;
import vn.com.pvcombank.repository.BLUploadFileRepository;
import vn.com.pvcombank.service.BLUploadFileQueryService;
import vn.com.pvcombank.service.BLUploadFileService;
import vn.com.pvcombank.service.S3Service;
import vn.com.pvcombank.service.criteria.BLUploadFileCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.BLUploadFile}.
 */
@RestController
@RequestMapping("/api")
public class BLUploadFileResource {

    private DateTimeFormatter s3DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private final Logger log = LoggerFactory.getLogger(BLUploadFileResource.class);

    private static final String ENTITY_NAME = "bLUploadFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BLUploadFileService bLUploadFileService;

    private final BLUploadFileRepository bLUploadFileRepository;

    private final BLUploadFileQueryService bLUploadFileQueryService;

    public BLUploadFileResource(
        BLUploadFileService bLUploadFileService,
        BLUploadFileRepository bLUploadFileRepository,
        BLUploadFileQueryService bLUploadFileQueryService
    ) {
        this.bLUploadFileService = bLUploadFileService;
        this.bLUploadFileRepository = bLUploadFileRepository;
        this.bLUploadFileQueryService = bLUploadFileQueryService;
    }

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private S3Service s3Service;

    /**
     * {@code POST  /bl-upload-files} : Create a new bLUploadFile.
     *
     * @param bLUploadFile the bLUploadFile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new bLUploadFile, or with status {@code 400 (Bad Request)}
     *         if the bLUploadFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bl-upload-files")
    public ResponseEntity<BLUploadFile> createBLUploadFile(@RequestBody BLUploadFile bLUploadFile) throws URISyntaxException {
        log.debug("REST request to save BLUploadFile : {}", bLUploadFile);
        if (bLUploadFile.getId() != null) {
            throw new BadRequestAlertException("A new bLUploadFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BLUploadFile result = bLUploadFileService.save(bLUploadFile);
        return ResponseEntity
            .created(new URI("/api/bl-upload-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bl-upload-files/:id} : Updates an existing bLUploadFile.
     *
     * @param id           the id of the bLUploadFile to save.
     * @param bLUploadFile the bLUploadFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated bLUploadFile,
     *         or with status {@code 400 (Bad Request)} if the bLUploadFile is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         bLUploadFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bl-upload-files/{id}")
    public ResponseEntity<BLUploadFile> updateBLUploadFile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLUploadFile bLUploadFile
    ) throws URISyntaxException {
        log.debug("REST request to update BLUploadFile : {}, {}", id, bLUploadFile);
        if (bLUploadFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLUploadFile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLUploadFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BLUploadFile result = bLUploadFileService.save(bLUploadFile);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLUploadFile.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bl-upload-files/:id} : Partial updates given fields of an
     * existing bLUploadFile, field will ignore if it is null
     *
     * @param id           the id of the bLUploadFile to save.
     * @param bLUploadFile the bLUploadFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated bLUploadFile,
     *         or with status {@code 400 (Bad Request)} if the bLUploadFile is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the bLUploadFile is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         bLUploadFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bl-upload-files/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BLUploadFile> partialUpdateBLUploadFile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BLUploadFile bLUploadFile
    ) throws URISyntaxException {
        log.debug("REST request to partial update BLUploadFile partially : {}, {}", id, bLUploadFile);
        if (bLUploadFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bLUploadFile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bLUploadFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BLUploadFile> result = bLUploadFileService.partialUpdate(bLUploadFile);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bLUploadFile.getId().toString())
        );
    }

    /**
     * {@code GET  /bl-upload-files} : get all the bLUploadFiles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of bLUploadFiles in body.
     */
    @GetMapping("/bl-upload-files")
    public ResponseEntity<List<BLUploadFile>> getAllBLUploadFiles(BLUploadFileCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BLUploadFiles by criteria: {}", criteria);
        Page<BLUploadFile> page = bLUploadFileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bl-upload-files/count} : count all the bLUploadFiles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count
     *         in body.
     */
    @GetMapping("/bl-upload-files/count")
    public ResponseEntity<Long> countBLUploadFiles(BLUploadFileCriteria criteria) {
        log.debug("REST request to count BLUploadFiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(bLUploadFileQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bl-upload-files/:id} : get the "id" bLUploadFile.
     *
     * @param id the id of the bLUploadFile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the bLUploadFile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bl-upload-files/{id}")
    public ResponseEntity<BLUploadFile> getBLUploadFile(@PathVariable Long id) {
        log.debug("REST request to get BLUploadFile : {}", id);
        Optional<BLUploadFile> bLUploadFile = bLUploadFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bLUploadFile);
    }

    /**
     * {@code DELETE  /bl-upload-files/:id} : delete the "id" bLUploadFile.
     *
     * @param id the id of the bLUploadFile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bl-upload-files/{id}")
    public ResponseEntity<Void> deleteBLUploadFile(@PathVariable Long id) {
        log.debug("REST request to delete BLUploadFile : {}", id);
        bLUploadFileService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @RequestMapping(value = "/bl-upload/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String upload(
        @RequestPart("datafile") @Valid String blUploadFile,
        @RequestPart("file") @Valid @NotNull MultipartFile file,
        HttpServletRequest request
    ) throws URISyntaxException {
        log.info("REST request to update blUploadFile : {}", blUploadFile);
        log.info("File : {}", file);
        BLUploadFile result = null;
       
         return "";
    }

    @PostMapping("/comfirm-upload")
    public ResponseEntity<BLUploadFile> comfirmUploadFile(@PathVariable String fileName) throws URISyntaxException, IOException {
        log.debug("Admin already comfirmed : {}");

        if (fileName == null) {
            return ResponseEntity.badRequest().build();
        }

        Path filePath = Paths.get("D:\\project\\AML_System\\src\\main\\webapp\\content\\AML_blacklist", fileName);

        List<String> fileData = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(filePath.toFile())) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellvalue = "";
                    if (cell != null) {
                        cellvalue = cell.toString();
                    }
                    fileData.add(cellvalue);
                }
            }
        } catch (InvalidFormatException e) {
            return ResponseEntity.badRequest().build();
        }

        // for (String data : fileData) {
        //     BLCustomer bCustomer = new BLCustomer();
        //     bCustomer.se

        // }

        return ResponseEntity.ok().build();
    }
}
