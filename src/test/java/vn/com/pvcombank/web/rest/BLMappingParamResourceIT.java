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
import vn.com.pvcombank.domain.BLSourceData;
import vn.com.pvcombank.repository.BLSourceDataRepository;
import vn.com.pvcombank.service.criteria.BLSourceDataCriteria;

/**
 * Integration tests for the {@link BLSourceDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLMappingParamResourceIT {

    private static final String DEFAULT_SOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_FILE_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_FILE_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_COLS = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_COLS = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_REF = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_COLS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_COLS = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_REF = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_createdBy = "AAAAAAAAAA";
    private static final String UPDATED_createdBy = "BBBBBBBBBB";

    private static final String DEFAULT_date_created = "AAAAAAAAAA";
    private static final String UPDATED_date_created = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_authoriseBy = "BBBBBBBBBB";

    private static final String UPDATED_DATE_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_date_authorise = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-mapping-params";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLSourceDataRepository bLMappingParamRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLMappingParamMockMvc;

    private BLSourceData bLMappingParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLSourceData createEntity(EntityManager em) {
        BLSourceData bLMappingParam = new BLSourceData()
            .sourceName(DEFAULT_SOURCE_NAME)
            .sourceFilePrefix(DEFAULT_SOURCE_FILE_PREFIX)
            .sourceCols(DEFAULT_SOURCE_COLS)
            .sourceRef(DEFAULT_SOURCE_REF)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .createdBy(DEFAULT_createdBy)
            .dateCreated(DEFAULT_date_created)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE_BY);
        return bLMappingParam;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLSourceData createUpdatedEntity(EntityManager em) {
        BLSourceData bLMappingParam = new BLSourceData()
            .sourceName(UPDATED_SOURCE_NAME)
            .sourceFilePrefix(UPDATED_SOURCE_FILE_PREFIX)
            .sourceCols(UPDATED_SOURCE_COLS)
            .sourceRef(UPDATED_SOURCE_REF)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdBy(UPDATED_createdBy)
            .dateCreated(UPDATED_date_created)
            .authoriseBy(UPDATED_authoriseBy)
            .dateAuthorise(UPDATED_date_authorise);
        return bLMappingParam;
    }

    @BeforeEach
    public void initTest() {
        bLMappingParam = createEntity(em);
    }

    @Test
    @Transactional
    void createBLMappingParam() throws Exception {
        int databaseSizeBeforeCreate = bLMappingParamRepository.findAll().size();
        // Create the BLMappingParam
        restBLMappingParamMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isCreated());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeCreate + 1);
        BLSourceData testBLMappingParam = bLMappingParamList.get(bLMappingParamList.size() - 1);
        assertThat(testBLMappingParam.getSourceName()).isEqualTo(DEFAULT_SOURCE_NAME);
        assertThat(testBLMappingParam.getSourceFilePrefix()).isEqualTo(DEFAULT_SOURCE_FILE_PREFIX);
        assertThat(testBLMappingParam.getSourceCols()).isEqualTo(DEFAULT_SOURCE_COLS);
        assertThat(testBLMappingParam.getSourceRef()).isEqualTo(DEFAULT_SOURCE_REF);
        assertThat(testBLMappingParam.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testBLMappingParam.getCreatedBy()).isEqualTo(DEFAULT_createdBy);
        assertThat(testBLMappingParam.getDateCreated()).isEqualTo(DEFAULT_date_created);
        assertThat(testBLMappingParam.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLMappingParam.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void createBLMappingParamWithExistingId() throws Exception {
        // Create the BLMappingParam with an existing ID
        bLMappingParam.setId(1L);

        int databaseSizeBeforeCreate = bLMappingParamRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLMappingParamMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLMappingParams() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLMappingParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceName").value(hasItem(DEFAULT_SOURCE_NAME)))
            .andExpect(jsonPath("$.[*].sourceFilePrefix").value(hasItem(DEFAULT_SOURCE_FILE_PREFIX)))
            .andExpect(jsonPath("$.[*].templateCols").value(hasItem(DEFAULT_TEMPLATE_COLS)))
            .andExpect(jsonPath("$.[*].templateRef").value(hasItem(DEFAULT_TEMPLATE_REF)))
            .andExpect(jsonPath("$.[*].sourceCols").value(hasItem(DEFAULT_SOURCE_COLS)))
            .andExpect(jsonPath("$.[*].sourceRef").value(hasItem(DEFAULT_SOURCE_REF)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_createdBy)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_date_created)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE_BY)));
    }

    @Test
    @Transactional
    void getBLMappingParam() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get the bLMappingParam
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL_ID, bLMappingParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLMappingParam.getId().intValue()))
            .andExpect(jsonPath("$.sourceName").value(DEFAULT_SOURCE_NAME))
            .andExpect(jsonPath("$.sourceFilePrefix").value(DEFAULT_SOURCE_FILE_PREFIX))
            .andExpect(jsonPath("$.templateCols").value(DEFAULT_TEMPLATE_COLS))
            .andExpect(jsonPath("$.templateRef").value(DEFAULT_TEMPLATE_REF))
            .andExpect(jsonPath("$.sourceCols").value(DEFAULT_SOURCE_COLS))
            .andExpect(jsonPath("$.sourceRef").value(DEFAULT_SOURCE_REF))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_createdBy))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_date_created))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateAuthorise").value(UPDATED_DATE_AUTHORISE_BY));
    }

    @Test
    @Transactional
    void getBLMappingParamsByIdFiltering() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        Long id = bLMappingParam.getId();

        defaultBLMappingParamShouldBeFound("id.equals=" + id);
        defaultBLMappingParamShouldNotBeFound("id.notEquals=" + id);

        defaultBLMappingParamShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBLMappingParamShouldNotBeFound("id.greaterThan=" + id);

        defaultBLMappingParamShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBLMappingParamShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName equals to DEFAULT_SOURCE_NAME
        defaultBLMappingParamShouldBeFound("sourceName.equals=" + DEFAULT_SOURCE_NAME);

        // Get all the bLMappingParamList where sourceName equals to UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldNotBeFound("sourceName.equals=" + UPDATED_SOURCE_NAME);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName not equals to DEFAULT_SOURCE_NAME
        defaultBLMappingParamShouldNotBeFound("sourceName.notEquals=" + DEFAULT_SOURCE_NAME);

        // Get all the bLMappingParamList where sourceName not equals to UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldBeFound("sourceName.notEquals=" + UPDATED_SOURCE_NAME);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName in DEFAULT_SOURCE_NAME or UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldBeFound("sourceName.in=" + DEFAULT_SOURCE_NAME + "," + UPDATED_SOURCE_NAME);

        // Get all the bLMappingParamList where sourceName equals to UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldNotBeFound("sourceName.in=" + UPDATED_SOURCE_NAME);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName is not null
        defaultBLMappingParamShouldBeFound("sourceName.specified=true");

        // Get all the bLMappingParamList where sourceName is null
        defaultBLMappingParamShouldNotBeFound("sourceName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName contains DEFAULT_SOURCE_NAME
        defaultBLMappingParamShouldBeFound("sourceName.contains=" + DEFAULT_SOURCE_NAME);

        // Get all the bLMappingParamList where sourceName contains UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldNotBeFound("sourceName.contains=" + UPDATED_SOURCE_NAME);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceName does not contain DEFAULT_SOURCE_NAME
        defaultBLMappingParamShouldNotBeFound("sourceName.doesNotContain=" + DEFAULT_SOURCE_NAME);

        // Get all the bLMappingParamList where sourceName does not contain UPDATED_SOURCE_NAME
        defaultBLMappingParamShouldBeFound("sourceName.doesNotContain=" + UPDATED_SOURCE_NAME);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix equals to DEFAULT_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.equals=" + DEFAULT_SOURCE_FILE_PREFIX);

        // Get all the bLMappingParamList where sourceFilePrefix equals to UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.equals=" + UPDATED_SOURCE_FILE_PREFIX);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix not equals to DEFAULT_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.notEquals=" + DEFAULT_SOURCE_FILE_PREFIX);

        // Get all the bLMappingParamList where sourceFilePrefix not equals to UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.notEquals=" + UPDATED_SOURCE_FILE_PREFIX);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix in DEFAULT_SOURCE_FILE_PREFIX or UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.in=" + DEFAULT_SOURCE_FILE_PREFIX + "," + UPDATED_SOURCE_FILE_PREFIX);

        // Get all the bLMappingParamList where sourceFilePrefix equals to UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.in=" + UPDATED_SOURCE_FILE_PREFIX);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix is not null
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.specified=true");

        // Get all the bLMappingParamList where sourceFilePrefix is null
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix contains DEFAULT_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.contains=" + DEFAULT_SOURCE_FILE_PREFIX);

        // Get all the bLMappingParamList where sourceFilePrefix contains UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.contains=" + UPDATED_SOURCE_FILE_PREFIX);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceFilePrefixNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceFilePrefix does not contain DEFAULT_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldNotBeFound("sourceFilePrefix.doesNotContain=" + DEFAULT_SOURCE_FILE_PREFIX);

        // Get all the bLMappingParamList where sourceFilePrefix does not contain UPDATED_SOURCE_FILE_PREFIX
        defaultBLMappingParamShouldBeFound("sourceFilePrefix.doesNotContain=" + UPDATED_SOURCE_FILE_PREFIX);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols equals to DEFAULT_TEMPLATE_COLS
        defaultBLMappingParamShouldBeFound("templateCols.equals=" + DEFAULT_TEMPLATE_COLS);

        // Get all the bLMappingParamList where templateCols equals to UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldNotBeFound("templateCols.equals=" + UPDATED_TEMPLATE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols not equals to DEFAULT_TEMPLATE_COLS
        defaultBLMappingParamShouldNotBeFound("templateCols.notEquals=" + DEFAULT_TEMPLATE_COLS);

        // Get all the bLMappingParamList where templateCols not equals to UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldBeFound("templateCols.notEquals=" + UPDATED_TEMPLATE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols in DEFAULT_TEMPLATE_COLS or UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldBeFound("templateCols.in=" + DEFAULT_TEMPLATE_COLS + "," + UPDATED_TEMPLATE_COLS);

        // Get all the bLMappingParamList where templateCols equals to UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldNotBeFound("templateCols.in=" + UPDATED_TEMPLATE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols is not null
        defaultBLMappingParamShouldBeFound("templateCols.specified=true");

        // Get all the bLMappingParamList where templateCols is null
        defaultBLMappingParamShouldNotBeFound("templateCols.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols contains DEFAULT_TEMPLATE_COLS
        defaultBLMappingParamShouldBeFound("templateCols.contains=" + DEFAULT_TEMPLATE_COLS);

        // Get all the bLMappingParamList where templateCols contains UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldNotBeFound("templateCols.contains=" + UPDATED_TEMPLATE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateColsNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateCols does not contain DEFAULT_TEMPLATE_COLS
        defaultBLMappingParamShouldNotBeFound("templateCols.doesNotContain=" + DEFAULT_TEMPLATE_COLS);

        // Get all the bLMappingParamList where templateCols does not contain UPDATED_TEMPLATE_COLS
        defaultBLMappingParamShouldBeFound("templateCols.doesNotContain=" + UPDATED_TEMPLATE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef equals to DEFAULT_TEMPLATE_REF
        defaultBLMappingParamShouldBeFound("templateRef.equals=" + DEFAULT_TEMPLATE_REF);

        // Get all the bLMappingParamList where templateRef equals to UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldNotBeFound("templateRef.equals=" + UPDATED_TEMPLATE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef not equals to DEFAULT_TEMPLATE_REF
        defaultBLMappingParamShouldNotBeFound("templateRef.notEquals=" + DEFAULT_TEMPLATE_REF);

        // Get all the bLMappingParamList where templateRef not equals to UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldBeFound("templateRef.notEquals=" + UPDATED_TEMPLATE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef in DEFAULT_TEMPLATE_REF or UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldBeFound("templateRef.in=" + DEFAULT_TEMPLATE_REF + "," + UPDATED_TEMPLATE_REF);

        // Get all the bLMappingParamList where templateRef equals to UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldNotBeFound("templateRef.in=" + UPDATED_TEMPLATE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef is not null
        defaultBLMappingParamShouldBeFound("templateRef.specified=true");

        // Get all the bLMappingParamList where templateRef is null
        defaultBLMappingParamShouldNotBeFound("templateRef.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef contains DEFAULT_TEMPLATE_REF
        defaultBLMappingParamShouldBeFound("templateRef.contains=" + DEFAULT_TEMPLATE_REF);

        // Get all the bLMappingParamList where templateRef contains UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldNotBeFound("templateRef.contains=" + UPDATED_TEMPLATE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByTemplateRefNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where templateRef does not contain DEFAULT_TEMPLATE_REF
        defaultBLMappingParamShouldNotBeFound("templateRef.doesNotContain=" + DEFAULT_TEMPLATE_REF);

        // Get all the bLMappingParamList where templateRef does not contain UPDATED_TEMPLATE_REF
        defaultBLMappingParamShouldBeFound("templateRef.doesNotContain=" + UPDATED_TEMPLATE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols equals to DEFAULT_SOURCE_COLS
        defaultBLMappingParamShouldBeFound("sourceCols.equals=" + DEFAULT_SOURCE_COLS);

        // Get all the bLMappingParamList where sourceCols equals to UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldNotBeFound("sourceCols.equals=" + UPDATED_SOURCE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols not equals to DEFAULT_SOURCE_COLS
        defaultBLMappingParamShouldNotBeFound("sourceCols.notEquals=" + DEFAULT_SOURCE_COLS);

        // Get all the bLMappingParamList where sourceCols not equals to UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldBeFound("sourceCols.notEquals=" + UPDATED_SOURCE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols in DEFAULT_SOURCE_COLS or UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldBeFound("sourceCols.in=" + DEFAULT_SOURCE_COLS + "," + UPDATED_SOURCE_COLS);

        // Get all the bLMappingParamList where sourceCols equals to UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldNotBeFound("sourceCols.in=" + UPDATED_SOURCE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols is not null
        defaultBLMappingParamShouldBeFound("sourceCols.specified=true");

        // Get all the bLMappingParamList where sourceCols is null
        defaultBLMappingParamShouldNotBeFound("sourceCols.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols contains DEFAULT_SOURCE_COLS
        defaultBLMappingParamShouldBeFound("sourceCols.contains=" + DEFAULT_SOURCE_COLS);

        // Get all the bLMappingParamList where sourceCols contains UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldNotBeFound("sourceCols.contains=" + UPDATED_SOURCE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceColsNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceCols does not contain DEFAULT_SOURCE_COLS
        defaultBLMappingParamShouldNotBeFound("sourceCols.doesNotContain=" + DEFAULT_SOURCE_COLS);

        // Get all the bLMappingParamList where sourceCols does not contain UPDATED_SOURCE_COLS
        defaultBLMappingParamShouldBeFound("sourceCols.doesNotContain=" + UPDATED_SOURCE_COLS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef equals to DEFAULT_SOURCE_REF
        defaultBLMappingParamShouldBeFound("sourceRef.equals=" + DEFAULT_SOURCE_REF);

        // Get all the bLMappingParamList where sourceRef equals to UPDATED_SOURCE_REF
        defaultBLMappingParamShouldNotBeFound("sourceRef.equals=" + UPDATED_SOURCE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef not equals to DEFAULT_SOURCE_REF
        defaultBLMappingParamShouldNotBeFound("sourceRef.notEquals=" + DEFAULT_SOURCE_REF);

        // Get all the bLMappingParamList where sourceRef not equals to UPDATED_SOURCE_REF
        defaultBLMappingParamShouldBeFound("sourceRef.notEquals=" + UPDATED_SOURCE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef in DEFAULT_SOURCE_REF or UPDATED_SOURCE_REF
        defaultBLMappingParamShouldBeFound("sourceRef.in=" + DEFAULT_SOURCE_REF + "," + UPDATED_SOURCE_REF);

        // Get all the bLMappingParamList where sourceRef equals to UPDATED_SOURCE_REF
        defaultBLMappingParamShouldNotBeFound("sourceRef.in=" + UPDATED_SOURCE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef is not null
        defaultBLMappingParamShouldBeFound("sourceRef.specified=true");

        // Get all the bLMappingParamList where sourceRef is null
        defaultBLMappingParamShouldNotBeFound("sourceRef.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef contains DEFAULT_SOURCE_REF
        defaultBLMappingParamShouldBeFound("sourceRef.contains=" + DEFAULT_SOURCE_REF);

        // Get all the bLMappingParamList where sourceRef contains UPDATED_SOURCE_REF
        defaultBLMappingParamShouldNotBeFound("sourceRef.contains=" + UPDATED_SOURCE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBySourceRefNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where sourceRef does not contain DEFAULT_SOURCE_REF
        defaultBLMappingParamShouldNotBeFound("sourceRef.doesNotContain=" + DEFAULT_SOURCE_REF);

        // Get all the bLMappingParamList where sourceRef does not contain UPDATED_SOURCE_REF
        defaultBLMappingParamShouldBeFound("sourceRef.doesNotContain=" + UPDATED_SOURCE_REF);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus equals to DEFAULT_RECORD_STATUS
        defaultBLMappingParamShouldBeFound("recordStatus.equals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLMappingParamList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldNotBeFound("recordStatus.equals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus not equals to DEFAULT_RECORD_STATUS
        defaultBLMappingParamShouldNotBeFound("recordStatus.notEquals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLMappingParamList where recordStatus not equals to UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldBeFound("recordStatus.notEquals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus in DEFAULT_RECORD_STATUS or UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldBeFound("recordStatus.in=" + DEFAULT_RECORD_STATUS + "," + UPDATED_RECORD_STATUS);

        // Get all the bLMappingParamList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldNotBeFound("recordStatus.in=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus is not null
        defaultBLMappingParamShouldBeFound("recordStatus.specified=true");

        // Get all the bLMappingParamList where recordStatus is null
        defaultBLMappingParamShouldNotBeFound("recordStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus contains DEFAULT_RECORD_STATUS
        defaultBLMappingParamShouldBeFound("recordStatus.contains=" + DEFAULT_RECORD_STATUS);

        // Get all the bLMappingParamList where recordStatus contains UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldNotBeFound("recordStatus.contains=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByRecordStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where recordStatus does not contain DEFAULT_RECORD_STATUS
        defaultBLMappingParamShouldNotBeFound("recordStatus.doesNotContain=" + DEFAULT_RECORD_STATUS);

        // Get all the bLMappingParamList where recordStatus does not contain UPDATED_RECORD_STATUS
        defaultBLMappingParamShouldBeFound("recordStatus.doesNotContain=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy equals to DEFAULT_createdBy
        defaultBLMappingParamShouldBeFound("createdBy.equals=" + DEFAULT_createdBy);

        // Get all the bLMappingParamList where createdBy equals to UPDATED_createdBy
        defaultBLMappingParamShouldNotBeFound("createdBy.equals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy not equals to DEFAULT_createdBy
        defaultBLMappingParamShouldNotBeFound("createdBy.notEquals=" + DEFAULT_createdBy);

        // Get all the bLMappingParamList where createdBy not equals to UPDATED_createdBy
        defaultBLMappingParamShouldBeFound("createdBy.notEquals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy in DEFAULT_createdBy or UPDATED_createdBy
        defaultBLMappingParamShouldBeFound("createdBy.in=" + DEFAULT_createdBy + "," + UPDATED_createdBy);

        // Get all the bLMappingParamList where createdBy equals to UPDATED_createdBy
        defaultBLMappingParamShouldNotBeFound("createdBy.in=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy is not null
        defaultBLMappingParamShouldBeFound("createdBy.specified=true");

        // Get all the bLMappingParamList where createdBy is null
        defaultBLMappingParamShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy contains DEFAULT_createdBy
        defaultBLMappingParamShouldBeFound("createdBy.contains=" + DEFAULT_createdBy);

        // Get all the bLMappingParamList where createdBy contains UPDATED_createdBy
        defaultBLMappingParamShouldNotBeFound("createdBy.contains=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBycreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where createdBy does not contain DEFAULT_createdBy
        defaultBLMappingParamShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_createdBy);

        // Get all the bLMappingParamList where createdBy does not contain UPDATED_createdBy
        defaultBLMappingParamShouldBeFound("createdBy.doesNotContain=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated equals to DEFAULT_date_created
        defaultBLMappingParamShouldBeFound("dateCreated.equals=" + DEFAULT_date_created);

        // Get all the bLMappingParamList where dateCreated equals to UPDATED_date_created
        defaultBLMappingParamShouldNotBeFound("dateCreated.equals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated not equals to DEFAULT_date_created
        defaultBLMappingParamShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_date_created);

        // Get all the bLMappingParamList where dateCreated not equals to UPDATED_date_created
        defaultBLMappingParamShouldBeFound("dateCreated.notEquals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated in DEFAULT_date_created or UPDATED_date_created
        defaultBLMappingParamShouldBeFound("dateCreated.in=" + DEFAULT_date_created + "," + UPDATED_date_created);

        // Get all the bLMappingParamList where dateCreated equals to UPDATED_date_created
        defaultBLMappingParamShouldNotBeFound("dateCreated.in=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated is not null
        defaultBLMappingParamShouldBeFound("dateCreated.specified=true");

        // Get all the bLMappingParamList where dateCreated is null
        defaultBLMappingParamShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated contains DEFAULT_date_created
        defaultBLMappingParamShouldBeFound("dateCreated.contains=" + DEFAULT_date_created);

        // Get all the bLMappingParamList where dateCreated contains UPDATED_date_created
        defaultBLMappingParamShouldNotBeFound("dateCreated.contains=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateCreatedNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateCreated does not contain DEFAULT_date_created
        defaultBLMappingParamShouldNotBeFound("dateCreated.doesNotContain=" + DEFAULT_date_created);

        // Get all the bLMappingParamList where dateCreated does not contain UPDATED_date_created
        defaultBLMappingParamShouldBeFound("dateCreated.doesNotContain=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy equals to DEFAULT_AUTHORISE_BY
        defaultBLMappingParamShouldBeFound("authoriseBy.equals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLMappingParamList where authoriseBy equals to UPDATED_authoriseBy
        defaultBLMappingParamShouldNotBeFound("authoriseBy.equals=" + UPDATED_authoriseBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy not equals to DEFAULT_AUTHORISE_BY
        defaultBLMappingParamShouldNotBeFound("authoriseBy.notEquals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLMappingParamList where authoriseBy not equals to UPDATED_authoriseBy
        defaultBLMappingParamShouldBeFound("authoriseBy.notEquals=" + UPDATED_authoriseBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy in DEFAULT_AUTHORISE_BY or UPDATED_authoriseBy
        defaultBLMappingParamShouldBeFound("authoriseBy.in=" + DEFAULT_AUTHORISE_BY + "," + UPDATED_authoriseBy);

        // Get all the bLMappingParamList where authoriseBy equals to UPDATED_authoriseBy
        defaultBLMappingParamShouldNotBeFound("authoriseBy.in=" + UPDATED_authoriseBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy is not null
        defaultBLMappingParamShouldBeFound("authoriseBy.specified=true");

        // Get all the bLMappingParamList where authoriseBy is null
        defaultBLMappingParamShouldNotBeFound("authoriseBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy contains DEFAULT_AUTHORISE_BY
        defaultBLMappingParamShouldBeFound("authoriseBy.contains=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLMappingParamList where authoriseBy contains UPDATED_authoriseBy
        defaultBLMappingParamShouldNotBeFound("authoriseBy.contains=" + UPDATED_authoriseBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsByauthoriseByNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where authoriseBy does not contain DEFAULT_AUTHORISE_BY
        defaultBLMappingParamShouldNotBeFound("authoriseBy.doesNotContain=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLMappingParamList where authoriseBy does not contain UPDATED_authoriseBy
        defaultBLMappingParamShouldBeFound("authoriseBy.doesNotContain=" + UPDATED_authoriseBy);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthorisequalToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise equals to UPDATED_DATE_AUTHORISE_BY
        defaultBLMappingParamShouldBeFound("dateAuthorise.equals=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLMappingParamList where dateAuthorise equals to UPDATED_date_authorise
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.equals=" + UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthoriseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise not equals to UPDATED_DATE_AUTHORISE_BY
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.notEquals=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLMappingParamList where dateAuthorise not equals to UPDATED_date_authorise
        defaultBLMappingParamShouldBeFound("dateAuthorise.notEquals=" + UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthoriseIsInShouldWork() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise in UPDATED_DATE_AUTHORISE_BY or UPDATED_date_authorise
        defaultBLMappingParamShouldBeFound("dateAuthorise.in=" + UPDATED_DATE_AUTHORISE_BY + "," + UPDATED_date_authorise);

        // Get all the bLMappingParamList where dateAuthorise equals to UPDATED_date_authorise
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.in=" + UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthoriseIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise is not null
        defaultBLMappingParamShouldBeFound("dateAuthorise.specified=true");

        // Get all the bLMappingParamList where dateAuthorise is null
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.specified=false");
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthoriseContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise contains UPDATED_DATE_AUTHORISE_BY
        defaultBLMappingParamShouldBeFound("dateAuthorise.contains=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLMappingParamList where dateAuthorise contains UPDATED_date_authorise
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.contains=" + UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void getAllBLMappingParamsBydateAuthoriseNotContainsSomething() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        // Get all the bLMappingParamList where dateAuthorise does not contain UPDATED_DATE_AUTHORISE_BY
        defaultBLMappingParamShouldNotBeFound("dateAuthorise.doesNotContain=" + UPDATED_DATE_AUTHORISE_BY);

        // Get all the bLMappingParamList where dateAuthorise does not contain UPDATED_date_authorise
        defaultBLMappingParamShouldBeFound("dateAuthorise.doesNotContain=" + UPDATED_date_authorise);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBLMappingParamShouldBeFound(String filter) throws Exception {
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLMappingParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceName").value(hasItem(DEFAULT_SOURCE_NAME)))
            .andExpect(jsonPath("$.[*].sourceFilePrefix").value(hasItem(DEFAULT_SOURCE_FILE_PREFIX)))
            .andExpect(jsonPath("$.[*].templateCols").value(hasItem(DEFAULT_TEMPLATE_COLS)))
            .andExpect(jsonPath("$.[*].templateRef").value(hasItem(DEFAULT_TEMPLATE_REF)))
            .andExpect(jsonPath("$.[*].sourceCols").value(hasItem(DEFAULT_SOURCE_COLS)))
            .andExpect(jsonPath("$.[*].sourceRef").value(hasItem(DEFAULT_SOURCE_REF)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_createdBy)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_date_created)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE_BY)));

        // Check, that the count call also returns 1
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBLMappingParamShouldNotBeFound(String filter) throws Exception {
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBLMappingParamMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBLMappingParam() throws Exception {
        // Get the bLMappingParam
        restBLMappingParamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLMappingParam() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();

        // Update the bLMappingParam
        BLSourceData updatedBLMappingParam = bLMappingParamRepository.findById(bLMappingParam.getId()).get();
        // Disconnect from session so that the updates on updatedBLMappingParam are not directly saved in db
        em.detach(updatedBLMappingParam);
        updatedBLMappingParam
            .sourceName(UPDATED_SOURCE_NAME)
            .sourceFilePrefix(UPDATED_SOURCE_FILE_PREFIX)
            .sourceCols(UPDATED_SOURCE_COLS)
            .sourceRef(UPDATED_SOURCE_REF)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdBy(UPDATED_createdBy)
            .dateCreated(UPDATED_date_created)
            .authoriseBy(UPDATED_authoriseBy)
            .dateAuthorise(UPDATED_date_authorise);

        restBLMappingParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLMappingParam.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLMappingParam))
            )
            .andExpect(status().isOk());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
        BLSourceData testBLMappingParam = bLMappingParamList.get(bLMappingParamList.size() - 1);
        assertThat(testBLMappingParam.getSourceName()).isEqualTo(UPDATED_SOURCE_NAME);
        assertThat(testBLMappingParam.getSourceFilePrefix()).isEqualTo(UPDATED_SOURCE_FILE_PREFIX);
        assertThat(testBLMappingParam.getSourceCols()).isEqualTo(UPDATED_SOURCE_COLS);
        assertThat(testBLMappingParam.getSourceRef()).isEqualTo(UPDATED_SOURCE_REF);
        assertThat(testBLMappingParam.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLMappingParam.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLMappingParam.getDateCreated()).isEqualTo(UPDATED_date_created);
        assertThat(testBLMappingParam.getAuthoriseBy()).isEqualTo(UPDATED_authoriseBy);
        assertThat(testBLMappingParam.getDateAuthorise()).isEqualTo(UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void putNonExistingBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLMappingParam.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLMappingParamWithPatch() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();

        // Update the bLMappingParam using partial update
        BLSourceData partialUpdatedBLMappingParam = new BLSourceData();
        partialUpdatedBLMappingParam.setId(bLMappingParam.getId());

        partialUpdatedBLMappingParam
            .sourceName(UPDATED_SOURCE_NAME)
            .sourceCols(UPDATED_SOURCE_COLS)
            .sourceRef(UPDATED_SOURCE_REF)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdBy(UPDATED_createdBy)
            .authoriseBy(UPDATED_authoriseBy);

        restBLMappingParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLMappingParam.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLMappingParam))
            )
            .andExpect(status().isOk());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
        BLSourceData testBLMappingParam = bLMappingParamList.get(bLMappingParamList.size() - 1);
        assertThat(testBLMappingParam.getSourceName()).isEqualTo(UPDATED_SOURCE_NAME);
        assertThat(testBLMappingParam.getSourceFilePrefix()).isEqualTo(DEFAULT_SOURCE_FILE_PREFIX);
        assertThat(testBLMappingParam.getSourceCols()).isEqualTo(UPDATED_SOURCE_COLS);
        assertThat(testBLMappingParam.getSourceRef()).isEqualTo(UPDATED_SOURCE_REF);
        assertThat(testBLMappingParam.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLMappingParam.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLMappingParam.getDateCreated()).isEqualTo(DEFAULT_date_created);
        assertThat(testBLMappingParam.getAuthoriseBy()).isEqualTo(UPDATED_authoriseBy);
        assertThat(testBLMappingParam.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void fullUpdateBLMappingParamWithPatch() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();

        // Update the bLMappingParam using partial update
        BLSourceData partialUpdatedBLMappingParam = new BLSourceData();
        partialUpdatedBLMappingParam.setId(bLMappingParam.getId());

        partialUpdatedBLMappingParam
            .sourceName(UPDATED_SOURCE_NAME)
            .sourceFilePrefix(UPDATED_SOURCE_FILE_PREFIX)
            .sourceCols(UPDATED_SOURCE_COLS)
            .sourceRef(UPDATED_SOURCE_REF)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdBy(UPDATED_createdBy)
            .dateCreated(UPDATED_date_created)
            .authoriseBy(UPDATED_authoriseBy)
            .dateAuthorise(UPDATED_date_authorise);

        restBLMappingParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLMappingParam.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLMappingParam))
            )
            .andExpect(status().isOk());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
        BLSourceData testBLMappingParam = bLMappingParamList.get(bLMappingParamList.size() - 1);
        assertThat(testBLMappingParam.getSourceName()).isEqualTo(UPDATED_SOURCE_NAME);
        assertThat(testBLMappingParam.getSourceFilePrefix()).isEqualTo(UPDATED_SOURCE_FILE_PREFIX);
        assertThat(testBLMappingParam.getSourceCols()).isEqualTo(UPDATED_SOURCE_COLS);
        assertThat(testBLMappingParam.getSourceRef()).isEqualTo(UPDATED_SOURCE_REF);
        assertThat(testBLMappingParam.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLMappingParam.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLMappingParam.getDateCreated()).isEqualTo(UPDATED_date_created);
        assertThat(testBLMappingParam.getAuthoriseBy()).isEqualTo(UPDATED_authoriseBy);
        assertThat(testBLMappingParam.getDateAuthorise()).isEqualTo(UPDATED_date_authorise);
    }

    @Test
    @Transactional
    void patchNonExistingBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLMappingParam.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLMappingParam() throws Exception {
        int databaseSizeBeforeUpdate = bLMappingParamRepository.findAll().size();
        bLMappingParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLMappingParamMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLMappingParam))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLMappingParam in the database
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLMappingParam() throws Exception {
        // Initialize the database
        bLMappingParamRepository.saveAndFlush(bLMappingParam);

        int databaseSizeBeforeDelete = bLMappingParamRepository.findAll().size();

        // Delete the bLMappingParam
        restBLMappingParamMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLMappingParam.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLSourceData> bLMappingParamList = bLMappingParamRepository.findAll();
        assertThat(bLMappingParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
