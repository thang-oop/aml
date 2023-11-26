package vn.com.pvcombank.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.IntegrationTest;
import vn.com.pvcombank.domain.BLUploadFile;
import vn.com.pvcombank.repository.BLUploadFileRepository;
import vn.com.pvcombank.service.criteria.BLUploadFileCriteria;

/**
 * Integration tests for the {@link BLUploadFileResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLUploadFileResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAGET_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_TAGET_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDATE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDATE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_FILE_SIZE = 1L;
    private static final Long UPDATED_FILE_SIZE = 2L;
    private static final Long SMALLER_FILE_SIZE = 1L - 1L;

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_UPLOAD_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_UPLOAD = "";
    private static final String UPDATED_DATE_UPLOAD = "";

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISE_BY = "BBBBBBBBBB";

    private static final String UPDATED_DATE_AUTHORISE_BY = "";
    private static final String UPDATED_DATE_AUTHORISE = "";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-upload-files";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLUploadFileRepository bLUploadFileRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLUploadFileMockMvc;

    private BLUploadFile bLUploadFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLUploadFile createEntity(EntityManager em) {
        BLUploadFile bLUploadFile = new BLUploadFile()
            .sourceFileName(DEFAULT_FILE_NAME)
            .systemFileName(DEFAULT_SYSTEM_FILE_NAME)
            .tagetCompany(DEFAULT_TAGET_COMPANY)
            .validate(DEFAULT_VALIDATE)
            .serviceStatus(DEFAULT_SERVICE_STATUS)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .uploadBy(DEFAULT_UPLOAD_BY)
            .dateUpload(DEFAULT_DATE_UPLOAD)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE_BY);
        return bLUploadFile;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLUploadFile createUpdatedEntity(EntityManager em) {
        BLUploadFile bLUploadFile = new BLUploadFile()
            .sourceFileName(UPDATED_FILE_NAME)
            .systemFileName(UPDATED_SYSTEM_FILE_NAME)
            .tagetCompany(UPDATED_TAGET_COMPANY)
            .validate(UPDATED_VALIDATE)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadBy(UPDATED_UPLOAD_BY)
            .dateUpload(UPDATED_DATE_UPLOAD)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);
        return bLUploadFile;
    }

    @BeforeEach
    public void initTest() {
        bLUploadFile = createEntity(em);
    }

    @Test
    @Transactional
    void createBLUploadFile() throws Exception {
        int databaseSizeBeforeCreate = bLUploadFileRepository.findAll().size();
        // Create the BLUploadFile
        restBLUploadFileMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isCreated());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeCreate + 1);
        BLUploadFile testBLUploadFile = bLUploadFileList.get(bLUploadFileList.size() - 1);
        assertThat(testBLUploadFile.getSystemFileName()).isEqualTo(DEFAULT_SYSTEM_FILE_NAME);
        assertThat(testBLUploadFile.getTagetCompany()).isEqualTo(DEFAULT_TAGET_COMPANY);
        assertThat(testBLUploadFile.getValidate()).isEqualTo(DEFAULT_VALIDATE);
        assertThat(testBLUploadFile.getServiceStatus()).isEqualTo(DEFAULT_SERVICE_STATUS);
        assertThat(testBLUploadFile.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testBLUploadFile.getUploadBy()).isEqualTo(DEFAULT_UPLOAD_BY);
        assertThat(testBLUploadFile.getDateUpload()).isEqualTo(DEFAULT_DATE_UPLOAD);
        assertThat(testBLUploadFile.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLUploadFile.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void createBLUploadFileWithExistingId() throws Exception {
        // Create the BLUploadFile with an existing ID
        bLUploadFile.setId(1L);

        int databaseSizeBeforeCreate = bLUploadFileRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLUploadFileMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLUploadFiles() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLUploadFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].systemFileName").value(hasItem(DEFAULT_SYSTEM_FILE_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tagetCompany").value(hasItem(DEFAULT_TAGET_COMPANY)))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE)))
            .andExpect(jsonPath("$.[*].serviceStatus").value(hasItem(DEFAULT_SERVICE_STATUS)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadBy").value(hasItem(DEFAULT_UPLOAD_BY)))
            .andExpect(jsonPath("$.[*].dateUpload").value(hasItem(DEFAULT_DATE_UPLOAD.toString())))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE_BY.toString())));
    }

    @Test
    @Transactional
    void getBLUploadFile() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get the bLUploadFile
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL_ID, bLUploadFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLUploadFile.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.systemFileName").value(DEFAULT_SYSTEM_FILE_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tagetCompany").value(DEFAULT_TAGET_COMPANY))
            .andExpect(jsonPath("$.validate").value(DEFAULT_VALIDATE))
            .andExpect(jsonPath("$.serviceStatus").value(DEFAULT_SERVICE_STATUS))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE.intValue()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.uploadBy").value(DEFAULT_UPLOAD_BY))
            .andExpect(jsonPath("$.dateUpload").value(DEFAULT_DATE_UPLOAD.toString()))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateAuthorise").value(UPDATED_DATE_AUTHORISE_BY.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED));
    }

    @Test
    @Transactional
    void getBLUploadFilesByIdFiltering() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        Long id = bLUploadFile.getId();

        defaultBLUploadFileShouldBeFound("id.equals=" + id);
        defaultBLUploadFileShouldNotBeFound("id.notEquals=" + id);

        defaultBLUploadFileShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBLUploadFileShouldNotBeFound("id.greaterThan=" + id);

        defaultBLUploadFileShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBLUploadFileShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName equals to DEFAULT_FILE_NAME
        defaultBLUploadFileShouldBeFound("fileName.equals=" + DEFAULT_FILE_NAME);

        // Get all the bLUploadFileList where fileName equals to UPDATED_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("fileName.equals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName not equals to DEFAULT_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("fileName.notEquals=" + DEFAULT_FILE_NAME);

        // Get all the bLUploadFileList where fileName not equals to UPDATED_FILE_NAME
        defaultBLUploadFileShouldBeFound("fileName.notEquals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName in DEFAULT_FILE_NAME or UPDATED_FILE_NAME
        defaultBLUploadFileShouldBeFound("fileName.in=" + DEFAULT_FILE_NAME + "," + UPDATED_FILE_NAME);

        // Get all the bLUploadFileList where fileName equals to UPDATED_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("fileName.in=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName is not null
        defaultBLUploadFileShouldBeFound("fileName.specified=true");

        // Get all the bLUploadFileList where fileName is null
        defaultBLUploadFileShouldNotBeFound("fileName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName contains DEFAULT_FILE_NAME
        defaultBLUploadFileShouldBeFound("fileName.contains=" + DEFAULT_FILE_NAME);

        // Get all the bLUploadFileList where fileName contains UPDATED_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("fileName.contains=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileName does not contain DEFAULT_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("fileName.doesNotContain=" + DEFAULT_FILE_NAME);

        // Get all the bLUploadFileList where fileName does not contain UPDATED_FILE_NAME
        defaultBLUploadFileShouldBeFound("fileName.doesNotContain=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName equals to DEFAULT_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldBeFound("systemFileName.equals=" + DEFAULT_SYSTEM_FILE_NAME);

        // Get all the bLUploadFileList where systemFileName equals to UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("systemFileName.equals=" + UPDATED_SYSTEM_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName not equals to DEFAULT_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("systemFileName.notEquals=" + DEFAULT_SYSTEM_FILE_NAME);

        // Get all the bLUploadFileList where systemFileName not equals to UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldBeFound("systemFileName.notEquals=" + UPDATED_SYSTEM_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName in DEFAULT_SYSTEM_FILE_NAME or UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldBeFound("systemFileName.in=" + DEFAULT_SYSTEM_FILE_NAME + "," + UPDATED_SYSTEM_FILE_NAME);

        // Get all the bLUploadFileList where systemFileName equals to UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("systemFileName.in=" + UPDATED_SYSTEM_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName is not null
        defaultBLUploadFileShouldBeFound("systemFileName.specified=true");

        // Get all the bLUploadFileList where systemFileName is null
        defaultBLUploadFileShouldNotBeFound("systemFileName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName contains DEFAULT_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldBeFound("systemFileName.contains=" + DEFAULT_SYSTEM_FILE_NAME);

        // Get all the bLUploadFileList where systemFileName contains UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("systemFileName.contains=" + UPDATED_SYSTEM_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBySystemFileNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where systemFileName does not contain DEFAULT_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldNotBeFound("systemFileName.doesNotContain=" + DEFAULT_SYSTEM_FILE_NAME);

        // Get all the bLUploadFileList where systemFileName does not contain UPDATED_SYSTEM_FILE_NAME
        defaultBLUploadFileShouldBeFound("systemFileName.doesNotContain=" + UPDATED_SYSTEM_FILE_NAME);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description equals to DEFAULT_DESCRIPTION
        defaultBLUploadFileShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the bLUploadFileList where description equals to UPDATED_DESCRIPTION
        defaultBLUploadFileShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description not equals to DEFAULT_DESCRIPTION
        defaultBLUploadFileShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the bLUploadFileList where description not equals to UPDATED_DESCRIPTION
        defaultBLUploadFileShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultBLUploadFileShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the bLUploadFileList where description equals to UPDATED_DESCRIPTION
        defaultBLUploadFileShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description is not null
        defaultBLUploadFileShouldBeFound("description.specified=true");

        // Get all the bLUploadFileList where description is null
        defaultBLUploadFileShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description contains DEFAULT_DESCRIPTION
        defaultBLUploadFileShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the bLUploadFileList where description contains UPDATED_DESCRIPTION
        defaultBLUploadFileShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where description does not contain DEFAULT_DESCRIPTION
        defaultBLUploadFileShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the bLUploadFileList where description does not contain UPDATED_DESCRIPTION
        defaultBLUploadFileShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany equals to DEFAULT_TAGET_COMPANY
        defaultBLUploadFileShouldBeFound("tagetCompany.equals=" + DEFAULT_TAGET_COMPANY);

        // Get all the bLUploadFileList where tagetCompany equals to UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldNotBeFound("tagetCompany.equals=" + UPDATED_TAGET_COMPANY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany not equals to DEFAULT_TAGET_COMPANY
        defaultBLUploadFileShouldNotBeFound("tagetCompany.notEquals=" + DEFAULT_TAGET_COMPANY);

        // Get all the bLUploadFileList where tagetCompany not equals to UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldBeFound("tagetCompany.notEquals=" + UPDATED_TAGET_COMPANY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany in DEFAULT_TAGET_COMPANY or UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldBeFound("tagetCompany.in=" + DEFAULT_TAGET_COMPANY + "," + UPDATED_TAGET_COMPANY);

        // Get all the bLUploadFileList where tagetCompany equals to UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldNotBeFound("tagetCompany.in=" + UPDATED_TAGET_COMPANY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany is not null
        defaultBLUploadFileShouldBeFound("tagetCompany.specified=true");

        // Get all the bLUploadFileList where tagetCompany is null
        defaultBLUploadFileShouldNotBeFound("tagetCompany.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany contains DEFAULT_TAGET_COMPANY
        defaultBLUploadFileShouldBeFound("tagetCompany.contains=" + DEFAULT_TAGET_COMPANY);

        // Get all the bLUploadFileList where tagetCompany contains UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldNotBeFound("tagetCompany.contains=" + UPDATED_TAGET_COMPANY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByTagetCompanyNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where tagetCompany does not contain DEFAULT_TAGET_COMPANY
        defaultBLUploadFileShouldNotBeFound("tagetCompany.doesNotContain=" + DEFAULT_TAGET_COMPANY);

        // Get all the bLUploadFileList where tagetCompany does not contain UPDATED_TAGET_COMPANY
        defaultBLUploadFileShouldBeFound("tagetCompany.doesNotContain=" + UPDATED_TAGET_COMPANY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate equals to DEFAULT_VALIDATE
        defaultBLUploadFileShouldBeFound("validate.equals=" + DEFAULT_VALIDATE);

        // Get all the bLUploadFileList where validate equals to UPDATED_VALIDATE
        defaultBLUploadFileShouldNotBeFound("validate.equals=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate not equals to DEFAULT_VALIDATE
        defaultBLUploadFileShouldNotBeFound("validate.notEquals=" + DEFAULT_VALIDATE);

        // Get all the bLUploadFileList where validate not equals to UPDATED_VALIDATE
        defaultBLUploadFileShouldBeFound("validate.notEquals=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate in DEFAULT_VALIDATE or UPDATED_VALIDATE
        defaultBLUploadFileShouldBeFound("validate.in=" + DEFAULT_VALIDATE + "," + UPDATED_VALIDATE);

        // Get all the bLUploadFileList where validate equals to UPDATED_VALIDATE
        defaultBLUploadFileShouldNotBeFound("validate.in=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate is not null
        defaultBLUploadFileShouldBeFound("validate.specified=true");

        // Get all the bLUploadFileList where validate is null
        defaultBLUploadFileShouldNotBeFound("validate.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate contains DEFAULT_VALIDATE
        defaultBLUploadFileShouldBeFound("validate.contains=" + DEFAULT_VALIDATE);

        // Get all the bLUploadFileList where validate contains UPDATED_VALIDATE
        defaultBLUploadFileShouldNotBeFound("validate.contains=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByValidateNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where validate does not contain DEFAULT_VALIDATE
        defaultBLUploadFileShouldNotBeFound("validate.doesNotContain=" + DEFAULT_VALIDATE);

        // Get all the bLUploadFileList where validate does not contain UPDATED_VALIDATE
        defaultBLUploadFileShouldBeFound("validate.doesNotContain=" + UPDATED_VALIDATE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus equals to DEFAULT_SERVICE_STATUS
        defaultBLUploadFileShouldBeFound("serviceStatus.equals=" + DEFAULT_SERVICE_STATUS);

        // Get all the bLUploadFileList where serviceStatus equals to UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldNotBeFound("serviceStatus.equals=" + UPDATED_SERVICE_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus not equals to DEFAULT_SERVICE_STATUS
        defaultBLUploadFileShouldNotBeFound("serviceStatus.notEquals=" + DEFAULT_SERVICE_STATUS);

        // Get all the bLUploadFileList where serviceStatus not equals to UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldBeFound("serviceStatus.notEquals=" + UPDATED_SERVICE_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus in DEFAULT_SERVICE_STATUS or UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldBeFound("serviceStatus.in=" + DEFAULT_SERVICE_STATUS + "," + UPDATED_SERVICE_STATUS);

        // Get all the bLUploadFileList where serviceStatus equals to UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldNotBeFound("serviceStatus.in=" + UPDATED_SERVICE_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus is not null
        defaultBLUploadFileShouldBeFound("serviceStatus.specified=true");

        // Get all the bLUploadFileList where serviceStatus is null
        defaultBLUploadFileShouldNotBeFound("serviceStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus contains DEFAULT_SERVICE_STATUS
        defaultBLUploadFileShouldBeFound("serviceStatus.contains=" + DEFAULT_SERVICE_STATUS);

        // Get all the bLUploadFileList where serviceStatus contains UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldNotBeFound("serviceStatus.contains=" + UPDATED_SERVICE_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByServiceStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where serviceStatus does not contain DEFAULT_SERVICE_STATUS
        defaultBLUploadFileShouldNotBeFound("serviceStatus.doesNotContain=" + DEFAULT_SERVICE_STATUS);

        // Get all the bLUploadFileList where serviceStatus does not contain UPDATED_SERVICE_STATUS
        defaultBLUploadFileShouldBeFound("serviceStatus.doesNotContain=" + UPDATED_SERVICE_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize equals to DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.equals=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize equals to UPDATED_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.equals=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize not equals to DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.notEquals=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize not equals to UPDATED_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.notEquals=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize in DEFAULT_FILE_SIZE or UPDATED_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.in=" + DEFAULT_FILE_SIZE + "," + UPDATED_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize equals to UPDATED_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.in=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize is not null
        defaultBLUploadFileShouldBeFound("fileSize.specified=true");

        // Get all the bLUploadFileList where fileSize is null
        defaultBLUploadFileShouldNotBeFound("fileSize.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize is greater than or equal to DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.greaterThanOrEqual=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize is greater than or equal to UPDATED_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.greaterThanOrEqual=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize is less than or equal to DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.lessThanOrEqual=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize is less than or equal to SMALLER_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.lessThanOrEqual=" + SMALLER_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsLessThanSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize is less than DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.lessThan=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize is less than UPDATED_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.lessThan=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByFileSizeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where fileSize is greater than DEFAULT_FILE_SIZE
        defaultBLUploadFileShouldNotBeFound("fileSize.greaterThan=" + DEFAULT_FILE_SIZE);

        // Get all the bLUploadFileList where fileSize is greater than SMALLER_FILE_SIZE
        defaultBLUploadFileShouldBeFound("fileSize.greaterThan=" + SMALLER_FILE_SIZE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus equals to DEFAULT_RECORD_STATUS
        defaultBLUploadFileShouldBeFound("recordStatus.equals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLUploadFileList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldNotBeFound("recordStatus.equals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus not equals to DEFAULT_RECORD_STATUS
        defaultBLUploadFileShouldNotBeFound("recordStatus.notEquals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLUploadFileList where recordStatus not equals to UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldBeFound("recordStatus.notEquals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus in DEFAULT_RECORD_STATUS or UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldBeFound("recordStatus.in=" + DEFAULT_RECORD_STATUS + "," + UPDATED_RECORD_STATUS);

        // Get all the bLUploadFileList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldNotBeFound("recordStatus.in=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus is not null
        defaultBLUploadFileShouldBeFound("recordStatus.specified=true");

        // Get all the bLUploadFileList where recordStatus is null
        defaultBLUploadFileShouldNotBeFound("recordStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus contains DEFAULT_RECORD_STATUS
        defaultBLUploadFileShouldBeFound("recordStatus.contains=" + DEFAULT_RECORD_STATUS);

        // Get all the bLUploadFileList where recordStatus contains UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldNotBeFound("recordStatus.contains=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByRecordStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where recordStatus does not contain DEFAULT_RECORD_STATUS
        defaultBLUploadFileShouldNotBeFound("recordStatus.doesNotContain=" + DEFAULT_RECORD_STATUS);

        // Get all the bLUploadFileList where recordStatus does not contain UPDATED_RECORD_STATUS
        defaultBLUploadFileShouldBeFound("recordStatus.doesNotContain=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy equals to DEFAULT_UPLOAD_BY
        defaultBLUploadFileShouldBeFound("uploadBy.equals=" + DEFAULT_UPLOAD_BY);

        // Get all the bLUploadFileList where uploadBy equals to UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldNotBeFound("uploadBy.equals=" + UPDATED_UPLOAD_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy not equals to DEFAULT_UPLOAD_BY
        defaultBLUploadFileShouldNotBeFound("uploadBy.notEquals=" + DEFAULT_UPLOAD_BY);

        // Get all the bLUploadFileList where uploadBy not equals to UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldBeFound("uploadBy.notEquals=" + UPDATED_UPLOAD_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy in DEFAULT_UPLOAD_BY or UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldBeFound("uploadBy.in=" + DEFAULT_UPLOAD_BY + "," + UPDATED_UPLOAD_BY);

        // Get all the bLUploadFileList where uploadBy equals to UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldNotBeFound("uploadBy.in=" + UPDATED_UPLOAD_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy is not null
        defaultBLUploadFileShouldBeFound("uploadBy.specified=true");

        // Get all the bLUploadFileList where uploadBy is null
        defaultBLUploadFileShouldNotBeFound("uploadBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy contains DEFAULT_UPLOAD_BY
        defaultBLUploadFileShouldBeFound("uploadBy.contains=" + DEFAULT_UPLOAD_BY);

        // Get all the bLUploadFileList where uploadBy contains UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldNotBeFound("uploadBy.contains=" + UPDATED_UPLOAD_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByUploadByNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where uploadBy does not contain DEFAULT_UPLOAD_BY
        defaultBLUploadFileShouldNotBeFound("uploadBy.doesNotContain=" + DEFAULT_UPLOAD_BY);

        // Get all the bLUploadFileList where uploadBy does not contain UPDATED_UPLOAD_BY
        defaultBLUploadFileShouldBeFound("uploadBy.doesNotContain=" + UPDATED_UPLOAD_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateUploadIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateUpload equals to DEFAULT_DATE_UPLOAD
        defaultBLUploadFileShouldBeFound("dateUpload.equals=" + DEFAULT_DATE_UPLOAD);

        // Get all the bLUploadFileList where dateUpload equals to UPDATED_DATE_UPLOAD
        defaultBLUploadFileShouldNotBeFound("dateUpload.equals=" + UPDATED_DATE_UPLOAD);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateUploadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateUpload not equals to DEFAULT_DATE_UPLOAD
        defaultBLUploadFileShouldNotBeFound("dateUpload.notEquals=" + DEFAULT_DATE_UPLOAD);

        // Get all the bLUploadFileList where dateUpload not equals to UPDATED_DATE_UPLOAD
        defaultBLUploadFileShouldBeFound("dateUpload.notEquals=" + UPDATED_DATE_UPLOAD);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateUploadIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateUpload in DEFAULT_DATE_UPLOAD or UPDATED_DATE_UPLOAD
        defaultBLUploadFileShouldBeFound("dateUpload.in=" + DEFAULT_DATE_UPLOAD + "," + UPDATED_DATE_UPLOAD);

        // Get all the bLUploadFileList where dateUpload equals to UPDATED_DATE_UPLOAD
        defaultBLUploadFileShouldNotBeFound("dateUpload.in=" + UPDATED_DATE_UPLOAD);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateUploadIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateUpload is not null
        defaultBLUploadFileShouldBeFound("dateUpload.specified=true");

        // Get all the bLUploadFileList where dateUpload is null
        defaultBLUploadFileShouldNotBeFound("dateUpload.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy equals to DEFAULT_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("authoriseBy.equals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLUploadFileList where authoriseBy equals to UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("authoriseBy.equals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy not equals to DEFAULT_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("authoriseBy.notEquals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLUploadFileList where authoriseBy not equals to UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("authoriseBy.notEquals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy in DEFAULT_AUTHORISE_BY or UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("authoriseBy.in=" + DEFAULT_AUTHORISE_BY + "," + UPDATED_AUTHORISE_BY);

        // Get all the bLUploadFileList where authoriseBy equals to UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("authoriseBy.in=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy is not null
        defaultBLUploadFileShouldBeFound("authoriseBy.specified=true");

        // Get all the bLUploadFileList where authoriseBy is null
        defaultBLUploadFileShouldNotBeFound("authoriseBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy contains DEFAULT_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("authoriseBy.contains=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLUploadFileList where authoriseBy contains UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("authoriseBy.contains=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByAuthoriseByNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where authoriseBy does not contain DEFAULT_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("authoriseBy.doesNotContain=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLUploadFileList where authoriseBy does not contain UPDATED_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("authoriseBy.doesNotContain=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateAuthoriseIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateAuthorise equals to UPDATED_DATE_AUTHORISE_BY
        defaultBLUploadFileShouldBeFound("dateAuthorise.equals=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLUploadFileList where dateAuthorise equals to UPDATED_DATE_AUTHORISE
        defaultBLUploadFileShouldNotBeFound("dateAuthorise.equals=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateAuthoriseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateAuthorise not equals to UPDATED_DATE_AUTHORISE_BY
        defaultBLUploadFileShouldNotBeFound("dateAuthorise.notEquals=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLUploadFileList where dateAuthorise not equals to UPDATED_DATE_AUTHORISE
        defaultBLUploadFileShouldBeFound("dateAuthorise.notEquals=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateAuthoriseIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateAuthorise in UPDATED_DATE_AUTHORISE_BY or UPDATED_DATE_AUTHORISE
        defaultBLUploadFileShouldBeFound("dateAuthorise.in=" + UPDATED_DATE_AUTHORISE_BY + "," + UPDATED_DATE_AUTHORISE);

        // Get all the bLUploadFileList where dateAuthorise equals to UPDATED_DATE_AUTHORISE
        defaultBLUploadFileShouldNotBeFound("dateAuthorise.in=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesBydateAuthoriseIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateAuthorise is not null
        defaultBLUploadFileShouldBeFound("dateAuthorise.specified=true");

        // Get all the bLUploadFileList where dateAuthorise is null
        defaultBLUploadFileShouldNotBeFound("dateAuthorise.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy equals to DEFAULT_CREATED_BY
        defaultBLUploadFileShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the bLUploadFileList where createdBy equals to UPDATED_CREATED_BY
        defaultBLUploadFileShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy not equals to DEFAULT_CREATED_BY
        defaultBLUploadFileShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

        // Get all the bLUploadFileList where createdBy not equals to UPDATED_CREATED_BY
        defaultBLUploadFileShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultBLUploadFileShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the bLUploadFileList where createdBy equals to UPDATED_CREATED_BY
        defaultBLUploadFileShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy is not null
        defaultBLUploadFileShouldBeFound("createdBy.specified=true");

        // Get all the bLUploadFileList where createdBy is null
        defaultBLUploadFileShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy contains DEFAULT_CREATED_BY
        defaultBLUploadFileShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the bLUploadFileList where createdBy contains UPDATED_CREATED_BY
        defaultBLUploadFileShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where createdBy does not contain DEFAULT_CREATED_BY
        defaultBLUploadFileShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the bLUploadFileList where createdBy does not contain UPDATED_CREATED_BY
        defaultBLUploadFileShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated equals to DEFAULT_DATE_CREATED
        defaultBLUploadFileShouldBeFound("dateCreated.equals=" + DEFAULT_DATE_CREATED);

        // Get all the bLUploadFileList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLUploadFileShouldNotBeFound("dateCreated.equals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated not equals to DEFAULT_DATE_CREATED
        defaultBLUploadFileShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_DATE_CREATED);

        // Get all the bLUploadFileList where dateCreated not equals to UPDATED_DATE_CREATED
        defaultBLUploadFileShouldBeFound("dateCreated.notEquals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated in DEFAULT_DATE_CREATED or UPDATED_DATE_CREATED
        defaultBLUploadFileShouldBeFound("dateCreated.in=" + DEFAULT_DATE_CREATED + "," + UPDATED_DATE_CREATED);

        // Get all the bLUploadFileList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLUploadFileShouldNotBeFound("dateCreated.in=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated is not null
        defaultBLUploadFileShouldBeFound("dateCreated.specified=true");

        // Get all the bLUploadFileList where dateCreated is null
        defaultBLUploadFileShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated contains DEFAULT_DATE_CREATED
        defaultBLUploadFileShouldBeFound("dateCreated.contains=" + DEFAULT_DATE_CREATED);

        // Get all the bLUploadFileList where dateCreated contains UPDATED_DATE_CREATED
        defaultBLUploadFileShouldNotBeFound("dateCreated.contains=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLUploadFilesByDateCreatedNotContainsSomething() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        // Get all the bLUploadFileList where dateCreated does not contain DEFAULT_DATE_CREATED
        defaultBLUploadFileShouldNotBeFound("dateCreated.doesNotContain=" + DEFAULT_DATE_CREATED);

        // Get all the bLUploadFileList where dateCreated does not contain UPDATED_DATE_CREATED
        defaultBLUploadFileShouldBeFound("dateCreated.doesNotContain=" + UPDATED_DATE_CREATED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBLUploadFileShouldBeFound(String filter) throws Exception {
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLUploadFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].systemFileName").value(hasItem(DEFAULT_SYSTEM_FILE_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tagetCompany").value(hasItem(DEFAULT_TAGET_COMPANY)))
            .andExpect(jsonPath("$.[*].validate").value(hasItem(DEFAULT_VALIDATE)))
            .andExpect(jsonPath("$.[*].serviceStatus").value(hasItem(DEFAULT_SERVICE_STATUS)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadBy").value(hasItem(DEFAULT_UPLOAD_BY)))
            .andExpect(jsonPath("$.[*].dateUpload").value(hasItem(DEFAULT_DATE_UPLOAD.toString())))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE_BY.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)));

        // Check, that the count call also returns 1
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBLUploadFileShouldNotBeFound(String filter) throws Exception {
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBLUploadFileMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBLUploadFile() throws Exception {
        // Get the bLUploadFile
        restBLUploadFileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLUploadFile() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();

        // Update the bLUploadFile
        BLUploadFile updatedBLUploadFile = bLUploadFileRepository.findById(bLUploadFile.getId()).get();
        // Disconnect from session so that the updates on updatedBLUploadFile are not directly saved in db
        em.detach(updatedBLUploadFile);
        updatedBLUploadFile
            .systemFileName(UPDATED_SYSTEM_FILE_NAME)
            .tagetCompany(UPDATED_TAGET_COMPANY)
            .validate(UPDATED_VALIDATE)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadBy(UPDATED_UPLOAD_BY)
            .dateUpload(UPDATED_DATE_UPLOAD)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLUploadFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLUploadFile.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLUploadFile))
            )
            .andExpect(status().isOk());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
        BLUploadFile testBLUploadFile = bLUploadFileList.get(bLUploadFileList.size() - 1);
        assertThat(testBLUploadFile.getSystemFileName()).isEqualTo(UPDATED_SYSTEM_FILE_NAME);
        assertThat(testBLUploadFile.getTagetCompany()).isEqualTo(UPDATED_TAGET_COMPANY);
        assertThat(testBLUploadFile.getValidate()).isEqualTo(UPDATED_VALIDATE);
        assertThat(testBLUploadFile.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testBLUploadFile.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLUploadFile.getUploadBy()).isEqualTo(UPDATED_UPLOAD_BY);
        assertThat(testBLUploadFile.getDateUpload()).isEqualTo(UPDATED_DATE_UPLOAD);
        assertThat(testBLUploadFile.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLUploadFile.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void putNonExistingBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLUploadFile.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLUploadFileWithPatch() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();

        // Update the bLUploadFile using partial update
        BLUploadFile partialUpdatedBLUploadFile = new BLUploadFile();
        partialUpdatedBLUploadFile.setId(bLUploadFile.getId());

        partialUpdatedBLUploadFile
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLUploadFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLUploadFile.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLUploadFile))
            )
            .andExpect(status().isOk());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
        BLUploadFile testBLUploadFile = bLUploadFileList.get(bLUploadFileList.size() - 1);
        assertThat(testBLUploadFile.getSystemFileName()).isEqualTo(DEFAULT_SYSTEM_FILE_NAME);
        assertThat(testBLUploadFile.getTagetCompany()).isEqualTo(DEFAULT_TAGET_COMPANY);
        assertThat(testBLUploadFile.getValidate()).isEqualTo(DEFAULT_VALIDATE);
        assertThat(testBLUploadFile.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testBLUploadFile.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLUploadFile.getUploadBy()).isEqualTo(DEFAULT_UPLOAD_BY);
        assertThat(testBLUploadFile.getDateUpload()).isEqualTo(DEFAULT_DATE_UPLOAD);
        assertThat(testBLUploadFile.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLUploadFile.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void fullUpdateBLUploadFileWithPatch() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();

        // Update the bLUploadFile using partial update
        BLUploadFile partialUpdatedBLUploadFile = new BLUploadFile();
        partialUpdatedBLUploadFile.setId(bLUploadFile.getId());

        partialUpdatedBLUploadFile
            .sourceFileName(UPDATED_FILE_NAME)
            .systemFileName(UPDATED_SYSTEM_FILE_NAME)
            .tagetCompany(UPDATED_TAGET_COMPANY)
            .validate(UPDATED_VALIDATE)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadBy(UPDATED_UPLOAD_BY)
            .dateUpload(UPDATED_DATE_UPLOAD)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLUploadFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLUploadFile.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLUploadFile))
            )
            .andExpect(status().isOk());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
        BLUploadFile testBLUploadFile = bLUploadFileList.get(bLUploadFileList.size() - 1);
        assertThat(testBLUploadFile.getSystemFileName()).isEqualTo(UPDATED_SYSTEM_FILE_NAME);
        assertThat(testBLUploadFile.getTagetCompany()).isEqualTo(UPDATED_TAGET_COMPANY);
        assertThat(testBLUploadFile.getValidate()).isEqualTo(UPDATED_VALIDATE);
        assertThat(testBLUploadFile.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testBLUploadFile.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLUploadFile.getUploadBy()).isEqualTo(UPDATED_UPLOAD_BY);
        assertThat(testBLUploadFile.getDateUpload()).isEqualTo(UPDATED_DATE_UPLOAD);
        assertThat(testBLUploadFile.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLUploadFile.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void patchNonExistingBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLUploadFile.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLUploadFile() throws Exception {
        int databaseSizeBeforeUpdate = bLUploadFileRepository.findAll().size();
        bLUploadFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLUploadFileMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLUploadFile))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLUploadFile in the database
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLUploadFile() throws Exception {
        // Initialize the database
        bLUploadFileRepository.saveAndFlush(bLUploadFile);

        int databaseSizeBeforeDelete = bLUploadFileRepository.findAll().size();

        // Delete the bLUploadFile
        restBLUploadFileMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLUploadFile.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLUploadFile> bLUploadFileList = bLUploadFileRepository.findAll();
        assertThat(bLUploadFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
